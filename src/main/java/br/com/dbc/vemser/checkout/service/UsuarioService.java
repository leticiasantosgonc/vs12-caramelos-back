package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.dtos.AdminOutDTO;
import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;
import br.com.dbc.vemser.checkout.entities.Role;
import br.com.dbc.vemser.checkout.entities.Usuario;
import br.com.dbc.vemser.checkout.repository.RoleRepository;
import br.com.dbc.vemser.checkout.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
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
        Usuario usuarioAtualizar = usuarioRepository.findById(idUsuario).get();
        usuarioAtualizar.setSenha(passwordEncoder.encode(senha));

        Usuario adminRetornar = usuarioRepository.save(usuarioAtualizar);
        AdminOutDTO adminDTO= objectMapper.convertValue(adminRetornar, AdminOutDTO.class);
        return adminDTO;
    }

    public void deleteAdmin(Integer idUsuario){
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()){
            usuarioRepository.delete(usuario.get());
        }
    }

}
