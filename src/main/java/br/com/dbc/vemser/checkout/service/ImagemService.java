package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.entities.Imagem;
import br.com.dbc.vemser.checkout.repository.ImagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImagemService {

    private final ImagemRepository imagemRepository;

    public Imagem save(MultipartFile img) throws IOException {

        Imagem imgTeste = new Imagem();

        imgTeste.setData(img.getBytes());
        imgTeste.setNome(img.getName());
        imgTeste.setTipo(img.getContentType());

        return imagemRepository.save(imgTeste);

    }

    public String getImg(Integer id) throws Exception{

        Imagem imgTeste = imagemRepository.findById(id).orElseThrow(()->new  Exception("erro"));

        String imgDados = "data:"+imgTeste.getTipo()+";base64,";
        return imgDados+Base64Utils.encodeToString(imgTeste.getData());
    }


}
