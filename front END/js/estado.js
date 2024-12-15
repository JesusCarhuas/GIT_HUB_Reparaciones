// Referencia a los elementos HTML
const formSerie = document.getElementById('formSerie');
const formDni = document.getElementById('formDni');
const tablaProceso = document.getElementById('tablaProceso');

// Función para limpiar la tabla
function limpiarTabla() {
    tablaProceso.innerHTML = '';
}

// Función para agregar filas a la tabla
function agregarFila(proceso) {
    const row = document.createElement('tr');
    row.innerHTML = `
        <td>${proceso.serieRegistro}</td>
        <td>${proceso.idComputadora}</td>
        <td>${proceso.idTecnico}</td>
        <td>${proceso.descripcionEstado}</td>
        <td>${proceso.adelanto}</td>
        <td>${proceso.total}</td>
        <td>${proceso.fechaEstimadaDeEntrega || 'No disponible'}</td>
        <td>${proceso.fechaRealDeEntrega || 'No disponible'}</td>
        <td>${proceso.problemaReportado || 'No especificado'}</td>
    `;
    tablaProceso.appendChild(row);
}

// Manejador del formulario por serie
formSerie.addEventListener('submit', async (e) => {
    e.preventDefault();
    const serie = document.getElementById('serie').value.trim();
    const url = `http://localhost:8080/registro/${serie}`;

    limpiarTabla(); // Limpiar tabla antes de nueva búsqueda

    try {
        const response = await fetch(url);

        if (!response.ok) {
            if (response.status === 404) {
                alert(`No se encontró el proceso con la serie: ${serie}`);
            } else {
                alert(`Error al buscar el proceso: ${response.statusText}`);
            }
            return;
        }

        const proceso = await response.json();
        agregarFila(proceso);
    } catch (error) {
        console.error('Error al conectar con el servidor:', error);
        alert('Error al conectar con el servidor.');
    }
});

// Manejador del formulario por DNI
formDni.addEventListener('submit', async (e) => {
    e.preventDefault();
    const dni = document.getElementById('dni').value.trim();
    const url = `http://localhost:8080/registro/cliente/${dni}`;

    limpiarTabla(); // Limpiar tabla antes de nueva búsqueda

    try {
        const response = await fetch(url);

        if (!response.ok) {
            if (response.status === 404) {
                alert(`No se encontraron registros para el cliente con DNI: ${dni}`);
            } else {
                alert(`Error al buscar registros: ${response.statusText}`);
            }
            return;
        }

        const registros = await response.json();

        if (registros.length === 0) {
            alert(`No se encontraron registros para el cliente con DNI: ${dni}`);
        } else {
            registros.forEach((proceso) => agregarFila(proceso));
        }
    } catch (error) {
        console.error('Error al conectar con el servidor:', error);
        alert('Error al conectar con el servidor.');
    }
});
