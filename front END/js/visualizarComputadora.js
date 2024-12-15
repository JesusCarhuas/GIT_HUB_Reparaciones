// Función para cargar las computadoras desde el backend
async function cargarComputadoras() {
    const url = 'http://localhost:8080/computadora/VisualizarComputadora'; // URL del backend

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

        const computadoras = await response.json();
        console.log("Datos recibidos:", computadoras);

        const tabla = document.getElementById('tablaComputadoras');
        tabla.innerHTML = ''; // Limpiar la tabla

        computadoras.forEach((computadora) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${computadora.IDComputadora}</td>
                <td>${computadora.IDCliente}</td>
                <td>${computadora.Marca}</td>
                <td>${computadora.NumeroSerie}</td>
            `;
            tabla.appendChild(row);
        });
    } catch (error) {
        console.error("Error al cargar las computadoras:", error);
        alert("Error al cargar los datos. Revisa la consola para más información.");
    }
}

// Llamar a la función al cargar la página
document.addEventListener('DOMContentLoaded', cargarComputadoras);
