package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.dtos.AdminOutDTO;
import br.com.dbc.vemser.checkout.entities.Role;
import br.com.dbc.vemser.checkout.entities.Usuario;
import br.com.dbc.vemser.checkout.repository.RoleRepository;
import br.com.dbc.vemser.checkout.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public List<AdminOutDTO> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<AdminOutDTO> admins= new ArrayList<>();

        for (Usuario usuario : usuarios) {
            AdminOutDTO adminsDTO = new AdminOutDTO();
            adminsDTO.setIdUsuario(usuario.getIdUsuario());
            adminsDTO.setLogin(usuario.getLogin());
            adminsDTO.setRole(usuario.getRole());

            admins.add(adminsDTO);
        }
        return admins;
    }

    public Optional<Usuario> findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }
    public AdminOutDTO createAdmin(AdminInDTO usuario) throws Exception{
            Role role = roleRepository.findById(1)
                    .orElseThrow(() -> new Exception("A role não existe"));
            Usuario novoAdmin = objectMapper.convertValue(usuario,Usuario.class);
            novoAdmin.setSenha(passwordEncoder.encode(usuario.getSenha()));
            novoAdmin.setRole(role);
            Usuario adminRetornado = usuarioRepository.save(novoAdmin);
            return objectMapper.convertValue(adminRetornado,AdminOutDTO.class);
    }

    public AdminOutDTO updateSenha(Integer idUsuario, String senha) throws Exception{
        Usuario usuarioAtualizar = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario não existe"));
        usuarioAtualizar.setSenha(passwordEncoder.encode(senha));

        Usuario adminRetornar = usuarioRepository.save(usuarioAtualizar);
        AdminOutDTO adminDTO= new AdminOutDTO();
        adminDTO.setIdUsuario(adminRetornar.getIdUsuario());
        adminDTO.setLogin(adminRetornar.getLogin());
        return adminDTO;
    }

    public void deleteAdmin(Integer idUsuario){
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()){
            usuarioRepository.delete(usuario.get());
        }
    }

}
