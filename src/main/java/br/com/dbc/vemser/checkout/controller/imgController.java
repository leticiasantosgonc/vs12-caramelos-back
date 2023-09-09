package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.entities.ImgTeste;
import br.com.dbc.vemser.checkout.service.imgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;

@RequestMapping("/img")
@RequiredArgsConstructor
@RestController
public class imgController {
    private final imgService imgService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ImgTeste save(@RequestParam("img")MultipartFile img) throws IOException {

        return imgService.save(img);
    }
    @GetMapping
    public String getImg(@RequestParam("idImg") Integer idImg) throws Exception{
        return imgService.getImg(idImg);
    }
}
