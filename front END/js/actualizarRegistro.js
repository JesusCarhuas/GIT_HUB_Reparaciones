// Manejador del formulario para insertar especificaciones
document.getElementById('formInsertarEspecificaciones').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Capturar los datos del formulario
    const registro = {
        serieRegistro: document.getElementById('serieRegistro').value.trim(),
        fechaRealEntrega: document.getElementById('fechaRealEntrega').value.trim(),
        problema2: document.getElementById('problema2').value.trim() || null
    };

    console.log("Datos enviados (Insertar Especificaciones):", registro);

    try {
        // URL del endpoint en el backend para insertar especificaciones
        const url = 'http://localhost:8080/actualizacion_registro/proceso_1';

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
            alert("Especificaciones insertadas con éxito.");
            console.log("Respuesta del servidor:", result);
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al insertar las especificaciones: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});

// Manejador del botón para concluir proceso
document.getElementById('btnConcluirProceso').addEventListener('click', async () => {
    // Obtener la serie del registro previamente ingresada
    const serieRegistro = document.getElementById('serieRegistro').value.trim();

    if (!serieRegistro) {
        alert("Por favor, ingrese una serie de registro antes de concluir el proceso.");
        return;
    }

    console.log("Datos enviados (Concluir Proceso):", { serieRegistro });

    try {
        // URL del endpoint en el backend para concluir proceso
        const url = 'http://localhost:8080/actualizacion_registro/proceso_2';

        // Realizar la solicitud PUT al backend
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ serieRegistro })
        });

        if (response.ok) {
            const result = await response.json();
            alert("Proceso concluido con éxito.");
            console.log("Respuesta del servidor:", result);
        } else {
            const error = await response.text();
            console.error("Error del servidor:", error);
            alert(`Error al concluir el proceso: ${error}`);
        }
    } catch (error) {
        console.error("Error en la conexión:", error);
        alert("Error de conexión. Por favor, inténtelo más tarde.");
    }
});
