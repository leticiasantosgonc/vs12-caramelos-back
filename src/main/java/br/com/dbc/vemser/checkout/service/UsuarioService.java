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
            Optional<Role> role = roleRepository.findById(1);

            Role roleConvert = objectMapper.convertValue(role,Role.class);

            Usuario usuarioEntity = objectMapper.convertValue(usuario,Usuario.class);

            usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));

            usuarioEntity.addCargo(roleConvert);

            Usuario usuarioRetornado = usuarioRepository.save(usuarioEntity);

            return objectMapper.convertValue(usuarioRetornado,AdminOutDTO.class);
    }

}
