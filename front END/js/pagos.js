document.getElementById('formPagos').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Obtener el valor de la serie ingresada
    const serie = document.getElementById('serie').value.trim();
    const url = `http://localhost:8080/monto/${serie}`;

    console.log("Serie enviada:", serie);
    console.log("URL solicitada:", url);

    if (!serie) {
        alert("La serie no puede estar vacía.");
        return;
    }

    try {
        // Muestra un mensaje de carga mientras se realiza la solicitud
        const tabla = document.getElementById('tablaPagos');
        tabla.innerHTML = '<tr><td colspan="4" class="text-center">Cargando datos...</td></tr>';

        // Realiza la solicitud al backend
        const response = await fetch(url);

        console.log("Estado de la respuesta:", response.status);

        if (!response.ok) {
            if (response.status === 404) {
                alert("No se encontraron pagos para la serie proporcionada.");
            } else {
                alert(`Error al obtener datos: ${response.statusText}`);
            }
            tabla.innerHTML = ''; // Limpiar la tabla si hay un error
            return;
        }

        const pagos = await response.json();
        console.log("Datos recibidos:", pagos);

        // Limpiar la tabla antes de llenarla con los nuevos datos
        tabla.innerHTML = '';

        if (pagos.length > 0) {
            pagos.forEach((pago) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${pago.idPago}</td>
                    <td>${pago.serieRegistro}</td>
                    <td>${pago.montoPago}</td>
                    <td>${pago.tipo}</td>
                `;
                tabla.appendChild(row);
            });
        } else {
            tabla.innerHTML = '<tr><td colspan="4" class="text-center">No se encontraron pagos para la serie ingresada.</td></tr>';
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert(`Error de conexión: ${error.message}`);
    }
});
