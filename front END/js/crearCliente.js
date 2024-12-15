// Manejador del formulario para crear cliente
document.getElementById('formCrearCliente').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Capturar los datos del formulario
    const cliente = {
        nombre: document.getElementById('nombre').value.trim(),
        apellidoPaterno: document.getElementById('apellidoPaterno').value.trim(),
        apellidoMaterno: document.getElementById('apellidoMaterno').value.trim(),
        dni: document.getElementById('dni').value.trim(),
        direccion: document.getElementById('direccion').value.trim(),
        telefono: document.getElementById('telefono').value.trim() || null,
        email: document.getElementById('email').value.trim()
    };

    console.log("Datos enviados:", cliente);

    try {
        // URL del endpoint en el backend
        const url = 'http://localhost:8080/cliente/crearcliente';

        // Realizar la solicitud POST al backend
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente)
        });

        if (response.ok) {
            const result = await response.json();
            alert("Cliente creado con éxito.");
            console.log("Respuesta del servidor:", result);
            document.getElementById('formCrearCliente').reset(); // Limpiar el formulario
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al crear el cliente: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});
