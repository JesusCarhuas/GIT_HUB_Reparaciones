// Función para manejar el envío del formulario
document.getElementById('formCrearRegistro').addEventListener('submit', async (e) => {
    e.preventDefault();

    const idComputadora = document.getElementById('idComputadora').value.trim();
    const idTecnico = document.getElementById('idTecnico').value.trim();
    const adelanto = document.getElementById('adelanto').value.trim();
    const duracionEstimada = document.getElementById('duracionEstimada').value.trim();
    const problemaReportado = document.getElementById('problemaReportado').value.trim();

    const url = 'http://localhost:8080/registro/crearRegistro';

    const data = {
        idComputadora: idComputadora,
        idTecnico: idTecnico,
        adelanto: parseFloat(adelanto),
        duracionEstimada: parseInt(duracionEstimada, 10),
        problemaReportado: problemaReportado || null,
    };

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText); // Lanzar el error tal como lo devuelve el backend
        }

        const result = await response.json();
        alert('Registro creado exitosamente: ' + JSON.stringify(result));
    } catch (error) {
        console.error('Error al crear el registro:', error.message);
        alert('Error al crear el registro: ' + error.message); // Mostrar el error exacto del backend
    }
});

// Manejo de los botones para navegar a otras páginas
document.getElementById('btnVisualizarComputadoras').addEventListener('click', () => {
    window.location.href = 'VisualizarComputadora.html';
});

document.getElementById('btnVisualizarTecnicos').addEventListener('click', () => {
    window.location.href = 'VisualizarTecnicos.html';
});
