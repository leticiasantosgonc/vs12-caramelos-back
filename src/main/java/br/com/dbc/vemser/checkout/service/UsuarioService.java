package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.dtos.AdminOutDTO;
import br.com.dbc.vemser.checkout.dtos.RecuperacaoInDTO;
import br.com.dbc.vemser.checkout.entities.Role;
import br.com.dbc.vemser.checkout.entities.Usuario;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.repository.RoleRepository;
import br.com.dbc.vemser.checkout.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
    private final JavaMailSender javaMailSender;

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
    public AdminOutDTO createAdmin(AdminInDTO usuario) throws RegraDeNegocioException {
            Role role = roleRepository.findById(1)
                    .orElseThrow(() -> new RegraDeNegocioException("A role não existe"));
            Usuario novoAdmin = objectMapper.convertValue(usuario,Usuario.class);
            novoAdmin.setSenha(passwordEncoder.encode(usuario.getSenha()));
            novoAdmin.setRole(role);
            Usuario adminRetornado = usuarioRepository.save(novoAdmin);
            return objectMapper.convertValue(adminRetornado,AdminOutDTO.class);
    }

    public AdminOutDTO updateSenha(RecuperacaoInDTO usuarioAtualizado) throws RegraDeNegocioException{
        Integer id = usuarioAtualizado.getIdUsuario();
        Usuario usuarioAtualizar = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não existe"));
        String login = usuarioAtualizado.getLogin();
        if (login.equals(usuarioAtualizar.getLogin())){
            usuarioAtualizar.setSenha(passwordEncoder.encode(usuarioAtualizado.getNovaSenha()));
            usuarioAtualizar.setLogin(usuarioAtualizado.getLogin());

            Usuario adminRetornar = usuarioRepository.save(usuarioAtualizar);
            AdminOutDTO adminDTO= new AdminOutDTO();
            adminDTO.setIdUsuario(adminRetornar.getIdUsuario());
            adminDTO.setLogin(adminRetornar.getLogin());
            adminDTO.setRole(adminRetornar.getRole());
            return adminDTO;
        }else{
            throw new RegraDeNegocioException("Operação inválida");
        }
    }

    public void deleteAdmin(Integer idUsuario) throws RegraDeNegocioException{
        Integer tamanho = findAll().size();

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(()->new RegraDeNegocioException("Usuario não encontrado"));

        if(tamanho <= 1){
            throw new RegraDeNegocioException("Impossivel realizar operação");
        }
        usuarioRepository.delete(usuario);
    }
    public void enviarEmailAlterarSenha(String email, String linkRedefinirSenha) throws MessagingException, RegraDeNegocioException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(email);

        if (usuario.isPresent()) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Redefinir Senha");
            helper.setText("Olá\nClique no link abaixo para redefinir sua senha:\n" + linkRedefinirSenha + "\nEquipe Caramelos\n");

            javaMailSender.send(message);
        } else {
            throw new RegraDeNegocioException("Operação inválida");
        }
    }
}
