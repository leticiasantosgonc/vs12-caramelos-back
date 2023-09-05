package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.entities.Usuario;
import br.com.dbc.vemser.checkout.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

}
