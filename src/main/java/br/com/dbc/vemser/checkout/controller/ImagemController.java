package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.entities.Imagem;
import br.com.dbc.vemser.checkout.service.ImagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/img")
@RequiredArgsConstructor
@RestController
public class ImagemController {
    private final ImagemService imagemService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Imagem save(@RequestParam("img")MultipartFile img) throws IOException {

        return imagemService.save(img);
    }
    @GetMapping
    public String getImg(@RequestParam("idImg") Integer idImg) throws Exception{
        return imagemService.getImg(idImg);
    }
}
