function generarCodigoQR() {
    const texto = document.getElementById("text").value;
    const ancho = parseInt(document.getElementById("width").value);
    const alto = parseInt(document.getElementById("height").value);

    // Verificamos que el texto no esté vacío
    if (texto.trim() === "") {
        alert("Por favor, introduce el texto para generar el QR.");
        return;
    }

    if (ancho <= 0 || ancho > 500 || alto <= 0 || alto > 500) {
        alert("Las dimensiones deben estar entre 1 y 500.");
        return;
    }

    // Construye la URL del backend para obtener la imagen del QR
    const url = `/QR?URL=${encodeURIComponent(texto)}&ancho=${ancho}&alto=${alto}`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al generar el QR. Código de estado: " + response.status);
            }
            return response.blob(); // Obtener la respuesta como imagen
        })
        .then(imageBlob => {
            const imageUrl = URL.createObjectURL(imageBlob);
            const qrImage = document.getElementById("imagenQR");

            qrImage.src = imageUrl;
            qrImage.style.display = "block"; // Mostramos la imagen

            // Hacer visible el botón de descarga
            const downloadBtn = document.getElementById("downloadBtn");
            downloadBtn.style.display = "inline-block";

            downloadBtn.onclick = function () {
                const a = document.createElement('a');
                a.href = imageUrl;
                a.download = 'codigo-qr.png'; // Nombre del archivo de descarga
                a.click();
            };
        })
        .catch(error => {
            alert("Hubo un problema al generar el código QR: " + error.message);
        });
}
