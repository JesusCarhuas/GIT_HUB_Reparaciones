// Función para manejar el envío del formulario
document.getElementById('formLoginCliente').addEventListener('submit', async (e) => {
    e.preventDefault(); // Evita que el formulario se envíe de forma predeterminada

    const dni = document.getElementById('dni').value.trim();
    const url = 'http://localhost:8080/seguridad/loginC'; // Endpoint del backend

    if (!/^\d{8}$/.test(dni)) {
        alert("Por favor, ingrese un DNI válido de 8 dígitos.");
        return;
    }

    const data = { dni: dni }; // Solo enviamos el DNI

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
            throw new Error(`Error: ${response.status} - ${errorText}`);
        }

        const result = await response.json();

        if (result && result.nombre) {
            alert(`Bienvenido(a), ${result.nombre}`);
            // Redirigir a ClienteMenu.html
            window.location.href = 'ClienteMenu.html';
        } else {
            document.getElementById('errorMessage').style.display = 'block';
        }
    } catch (error) {
        console.error("Error al iniciar sesión:", error);
        alert("Error al iniciar sesión. Revisa la consola para más detalles.");
    }
});
