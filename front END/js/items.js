// Función para consultar y mostrar los items por IDTipo
async function consultarItemsPorTipo() {
    // Capturamos el valor del IDTipo ingresado por el usuario
    const idTipo = document.getElementById('idTipo').value.trim();
    const url = `http://localhost:8080/items/TipoItems/${idTipo}`; // Endpoint del backend

    if (!idTipo) {
        alert("Por favor, ingrese un IDTipo válido.");
        return;
    }

    try {
        // Realizar la solicitud al backend
        const response = await fetch(url);

        if (!response.ok) {
            if (response.status === 404) {
                alert("No se encontraron items para el tipo especificado.");
            } else {
                throw new Error(`Error al obtener datos: ${response.statusText}`);
            }
            return;
        }

        const items = await response.json();
        console.log("Datos recibidos:", items);

        // Llenar la tabla con los datos
        const tabla = document.getElementById('tablaItems');
        tabla.innerHTML = ''; // Limpiar la tabla antes de llenarla

        if (items.length > 0) {
            items.forEach((item) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${item.IDItem}</td>
                    <td>${item.IDTipo}</td>
                    <td>${item.Nombre}</td>
                    <td>${item.Marca}</td>
                    <td>${item.CostoUnitario}</td>
                    <td>${item.Stock}</td>
                `;
                tabla.appendChild(row);
            });
        } else {
            tabla.innerHTML = '<tr><td colspan="6" class="text-center">No se encontraron items para este tipo.</td></tr>';
        }
    } catch (error) {
        console.error("Error al cargar los datos:", error);
        alert("Error al cargar los datos. Por favor, inténtelo más tarde.");
    }
}

// Asignar el evento al botón de consulta
document.getElementById('btnConsultar').addEventListener('click', consultarItemsPorTipo);
