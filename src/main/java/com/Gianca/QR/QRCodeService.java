package com.Gianca.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QRCodeService {
    /**
     * Genera una imagen de código QR a partir de una URL proporcionada y con dimensiones específicas.
     *
     * @param URL  La URL que se desea codificar en el QR. Debe ser una cadena válida.
     * @param ancho El ancho del código QR en píxeles. Debe ser un valor mayor que 0.
     * @param alto  La altura del código QR en píxeles. Debe ser un valor mayor que 0.
     * @return Un objeto {@link BufferedImage} que representa la imagen del código QR generado.
     * @throws Exception Captamos la excepcion en caso de que ocurra algun problema al generar el QR
     */
    public BufferedImage generateQRCode(String URL, int ancho, int alto) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(URL, BarcodeFormat.QR_CODE, ancho, alto);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
