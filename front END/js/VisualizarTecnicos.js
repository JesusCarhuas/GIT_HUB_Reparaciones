// Función para cargar los técnicos desde el backend
async function cargarTecnicos() {
    const url = 'http://localhost:8080/tecnico/VisualizarTecnicos'; // URL del backend

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error(`Error: ${response.statusText} (${response.status})`);
        }

        const tecnicos = await response.json();
        console.log("Datos recibidos:", tecnicos);

        const tabla = document.getElementById('tablaTecnicos');
        tabla.innerHTML = ''; // Limpiar la tabla

        tecnicos.forEach((tecnico) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${tecnico.IDTecnico}</td>
                <td>${tecnico.Nombre}</td>
                <td>${tecnico.Estado}</td>
            `;
            tabla.appendChild(row);
        });
    } catch (error) {
        console.error("Error al cargar los técnicos:", error);
        alert("Error al cargar los datos. Revisa la consola para más información.");
    }
}

// Llamar a la función al cargar la página
document.addEventListener('DOMContentLoaded', cargarTecnicos);
