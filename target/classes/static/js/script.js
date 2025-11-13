function openModal(modalId) {
    document.getElementById(modalId).style.display = "block";
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = "none";
}

// Fechar o modal se o usuário clicar fora dele
window.onclick = function(event) {
    const modal = document.getElementById('adicionarPacienteModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
