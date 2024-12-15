// Manejador del formulario para insertar items
document.getElementById('formInsertarItems').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Capturar los datos del formulario
    const item = {
        idItem: document.getElementById('idItem').value.trim(),
        serieRegistro: document.getElementById('serieRegistro').value.trim(),
        cantidad: document.getElementById('cantidad').value.trim()
    };

    console.log("Datos enviados:", item);

    try {
        // URL del endpoint en el backend
        const url = 'http://localhost:8080/items/ingreso-uso-items';

        // Realizar la solicitud POST al backend
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        });

        if (response.ok) {
            const result = await response.json();
            alert("Item insertado con éxito.");
            console.log("Respuesta del servidor:", result);
            document.getElementById('formInsertarItems').reset(); // Limpiar el formulario
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al insertar el item: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});

// Redirección al Mostrar Tipo de Items
document.getElementById('btnMostrarTipoItems').addEventListener('click', () => {
    window.location.href = 'MostrarTipoItem.html';
});

// Redirección a Items
document.getElementById('btnItems').addEventListener('click', () => {
    window.location.href = 'Items.html';
});
