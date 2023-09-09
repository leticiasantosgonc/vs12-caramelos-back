package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.entities.ImgTeste;
import br.com.dbc.vemser.checkout.repository.imgTesteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class imgService {

    private final imgTesteRepository imgRepository;

    public ImgTeste save(MultipartFile img) throws IOException {

        ImgTeste imgTeste = new ImgTeste();

        imgTeste.setData(img.getBytes());
        imgTeste.setNome(img.getName());
        imgTeste.setTipo(img.getContentType());

        return imgRepository.save(imgTeste);

    }

    public String getImg(Integer id) throws Exception{

        ImgTeste imgTeste = imgRepository.findById(id).orElseThrow(()->new  Exception("erro"));

        String imgDados = "data:"+imgTeste.getTipo()+";base64,";
        return imgDados+Base64Utils.encodeToString(imgTeste.getData());
    }


}
