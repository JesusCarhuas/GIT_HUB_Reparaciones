// Manejador del formulario para registrar pago total
document.getElementById('formPagarTotal').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Capturar los datos del formulario
    const pago = {
        serieRegistro: document.getElementById('serieRegistro').value.trim(),
        montoPago: parseFloat(document.getElementById('montoPago').value.trim())
    };

    console.log("Datos enviados:", pago);

    try {
        // URL del endpoint en el backend
        const url = 'http://localhost:8080/pago/pago-final';

        // Realizar la solicitud POST al backend
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pago)
        });

        if (response.ok) {
            const result = await response.json();
            alert("Pago total registrado con éxito.");
            console.log("Respuesta del servidor:", result);
            document.getElementById('formPagarTotal').reset(); // Limpiar el formulario
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al registrar el pago: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});
