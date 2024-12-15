// Función para cargar los clientes desde el backend
async function cargarClientes() {
    const url = 'http://localhost:8080/cliente/VisualizarClientes'; // Endpoint del backend

    try {
        // Realizar solicitud GET al backend
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`Error al obtener datos: ${response.statusText}`);
        }

        const clientes = await response.json();
        console.log("Datos recibidos:", clientes);

        // Llenar la tabla con los datos
        const tabla = document.getElementById('tablaClientes');
        tabla.innerHTML = ''; // Limpiar la tabla antes de llenarla

        clientes.forEach((cliente) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${cliente.IDCliente}</td>
                <td>${cliente.Nombre}</td>
                <td>${cliente.ApellidoPaternoC}</td>
                <td>${cliente.ApellidoMaternoC}</td>
                <td>${cliente.ClienteDni}</td>
            `;
            tabla.appendChild(row);
        });
    } catch (error) {
        console.error("Error al cargar los clientes:", error);
        alert("Error al cargar los datos. Por favor, inténtelo más tarde.");
    }
}

// Llamar a la función para cargar los datos cuando la página se carga
document.addEventListener('DOMContentLoaded', cargarClientes);
