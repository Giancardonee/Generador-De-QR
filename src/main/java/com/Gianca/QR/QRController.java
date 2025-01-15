package com.Gianca.QR;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/QR")
public class QRController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping
    public void generarQR(HttpServletResponse response,
                          @RequestParam String URL,
                          @RequestParam(defaultValue = "350") int ancho,
                          @RequestParam(defaultValue = "350") int alto) throws Exception {

        // Verificamos que los parámetros recibidos sean correctos
        System.out.println("URL: " + URL);
        System.out.println("Ancho: " + ancho);
        System.out.println("Alto: " + alto);

        // Establecemos el tipo de contenido de la respuesta
        response.setContentType("image/png");

        // Generamos la imagen QR
        BufferedImage image = qrCodeService.generateQRCode(URL, ancho, alto);

        // obtenemos y retornamos la imagen.
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "png", outputStream);
            outputStream.flush();
        }
    }
}
