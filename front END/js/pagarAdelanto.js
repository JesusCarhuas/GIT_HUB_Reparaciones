// Manejador del formulario para registrar adelanto
document.getElementById('formPagarAdelanto').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Capturar los datos del formulario
    const adelanto = {
        serieRegistro: document.getElementById('serieRegistro').value.trim(),
        montoPago: parseFloat(document.getElementById('montoPago').value.trim())
    };

    console.log("Datos enviados:", adelanto);

    try {
        // URL del endpoint en el backend
        const url = 'http://localhost:8080/pago/adelanto';

        // Realizar la solicitud POST al backend
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(adelanto)
        });

        if (response.ok) {
            const result = await response.json();
            alert("Adelanto registrado con éxito.");
            console.log("Respuesta del servidor:", result);
            document.getElementById('formPagarAdelanto').reset(); // Limpiar el formulario
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al registrar el adelanto: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});
