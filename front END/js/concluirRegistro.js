// Manejador del formulario para concluir registro
document.getElementById('formConcluirRegistro').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Capturar los datos del formulario
    const registro = {
        serieRegistro: document.getElementById('serieRegistro').value.trim()
    };

    console.log("Datos enviados:", registro);

    try {
        // URL del endpoint en el backend para concluir registro
        const url = 'http://localhost:8080/actualizacion_registro/proceso_3';

        // Realizar la solicitud PUT al backend
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registro)
        });

        if (response.ok) {
            const result = await response.json();
            alert("Registro concluido con éxito.");
            console.log("Respuesta del servidor:", result);
            document.getElementById('formConcluirRegistro').reset(); // Limpiar el formulario
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al concluir el registro: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});
