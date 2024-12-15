// Función para manejar el envío del formulario
document.getElementById('formLoginTecnico').addEventListener('submit', async (e) => {
    e.preventDefault();

    const tecnicoDni = document.getElementById('tecnicoDni').value.trim();
    const password = document.getElementById('password').value.trim();

    const url = 'http://localhost:8080/seguridad/loginT';
    const data = {
        dni: tecnicoDni,
        contraseña: password,
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
            throw new Error(`Error: ${response.status} - ${errorText}`);
        }

        const result = await response.json();

        // Mostrar mensaje de bienvenida con el nombre del técnico
        alert(`Bienvenido, ${result.nombre}`);
        window.location.href = 'MenuTecnico.html'; // Redirigir al menú del técnico
    } catch (error) {
        console.error('Error al iniciar sesión:', error);
        const errorMensaje = document.getElementById('errorMensaje');
        errorMensaje.classList.remove('d-none');
        errorMensaje.textContent = 'Credenciales incorrectas. Por favor, intente nuevamente.';
    }
});

