// Manejador del formulario para crear computadora
document.getElementById('formCrearComputadora').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Capturar los datos del formulario
    const computadora = {
        idCliente: document.getElementById('idCliente').value.trim(),
        marca: document.getElementById('marca').value.trim(),
        numeroSerie: document.getElementById('numeroSerie').value.trim(),
        anioFabricacion: document.getElementById('anioFabricacion').value.trim()
    };

    console.log("Datos enviados:", computadora);

    try {
        // URL del endpoint en el backend
        const url = 'http://localhost:8080/computadora/crearComputadora';

        // Realizar la solicitud POST al backend
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(computadora)
        });

        if (response.ok) {
            const result = await response.json();
            alert("Computadora creada con éxito.");
            console.log("Respuesta del servidor:", result);
            document.getElementById('formCrearComputadora').reset(); // Limpiar el formulario
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al crear la computadora: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});

// Redirección a VisualizarClientes.html
document.getElementById('btnVisualizarClientes').addEventListener('click', () => {
    window.location.href = 'VisualizarClientes.html';
});
