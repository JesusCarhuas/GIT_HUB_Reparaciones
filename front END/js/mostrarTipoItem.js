// Función para cargar los datos desde el backend
async function cargarTiposItems() {
    const url = 'http://localhost:8080/items/TipoItems'; // Endpoint del backend

    try {
        // Solicitar los datos al backend
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`Error al obtener datos: ${response.statusText}`);
        }

        const tiposItems = await response.json();
        console.log("Datos recibidos:", tiposItems);

        // Llenar la tabla con los datos
        const tabla = document.getElementById('tablaTipoItem');
        tabla.innerHTML = ''; // Limpiar la tabla antes de llenarla

        tiposItems.forEach((item) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${item.IDTipo}</td>
                <td>${item.NombreTipo}</td>
            `;
            tabla.appendChild(row);
        });
    } catch (error) {
        console.error("Error al cargar los datos:", error);
        alert("Error al cargar los datos. Por favor, inténtelo más tarde.");
    }
}

// Llamar a la función para cargar los datos cuando la página se carga
document.addEventListener('DOMContentLoaded', cargarTiposItems);
