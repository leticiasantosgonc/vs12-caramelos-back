package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.entities.Produto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Service
public class PDFService {

   public void generatePDF(HttpServletResponse response, Pedido pedido) throws IOException, DocumentException {
       Document document = new Document(PageSize.A4);
       PdfWriter.getInstance(document, response.getOutputStream());
       document.open();
       Font font = FontFactory.getFont(FontFactory.HELVETICA, 12);
       Paragraph paragraph = new Paragraph("Detalhes do Pedido", font);
       paragraph.setAlignment(Paragraph.ALIGN_CENTER);
       document.add(paragraph);
       document.add(new Paragraph("CPF: " + pedido.getCpf(), font));
       document.add(new Paragraph("Observação: " + pedido.getObservacao(), font));

       for (Produto produto : pedido.getItens()) {
           document.add(new Paragraph(produto.getNome() + " - " + produto.getPreco(), font));
       }
       document.add(new Paragraph("Total: " + pedido.getPreco(), font));
       document.add(new Paragraph("Data do Pedido: " + pedido.getDataPedido(), font));

       document.close();
   }
}
