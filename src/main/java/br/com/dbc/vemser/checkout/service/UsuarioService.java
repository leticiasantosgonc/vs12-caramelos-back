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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        return usuarioRepository.findByUsuario(login);
    }
    public AdminOutDTO createAdmin(AdminInDTO usuario) throws Exception{
//        try {
            Usuario novoAdmin = new Usuario();
            Role role = roleRepository.findById(1).get();
            novoAdmin.setUsuario(usuario.getLogin());
            novoAdmin.setSenha(passwordEncoder.encode(usuario.getSenha()));
            novoAdmin.setRoles(new HashSet<>());
            novoAdmin.getRoles().add(role);
            Usuario adminRetornar = usuarioRepository.save(novoAdmin);
            AdminOutDTO adminDTO= objectMapper.convertValue(adminRetornar, AdminOutDTO.class);
            return adminDTO;
//        } catch (Exception e){
//            throw new Exception("Não foi possível cadastrar administrador");
//        }
    }

}
