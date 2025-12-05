const formulario = document.getElementById("formCadastro");

formulario.addEventListener("submit", function (event) {
    event.preventDefault(); // Não deixa a página recarregar

    // 1. Pegar os dados dos inputs
    const dadosUsuario = {
        nome: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        cargo: document.getElementById("cargo").value
    };

    // 2. Enviar para o Java (Controller)
    fetch("http://localhost:8080/usuarios", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dadosUsuario) // Transforma o objeto JS em texto JSON
    })
    .then(resposta => {
        if (resposta.ok) {
            mostrarMensagem("Usuário cadastrado com sucesso!", "sucesso");
            formulario.reset(); // Limpa os campos
        } else {
            mostrarMensagem("Erro ao cadastrar. Verifique os dados.", "erro");
        }
    })
    .catch(erro => {
        console.error("Erro:", erro);
        mostrarMensagem("Erro de conexão com o servidor.", "erro");
    });
});

function mostrarMensagem(texto, tipo) {
    const msgDiv = document.getElementById("mensagem");
    msgDiv.innerText = texto;
    msgDiv.className = "msg " + tipo;

    // Apaga a mensagem depois de 3 segundos
    setTimeout(() => {
        msgDiv.innerText = "";
    }, 3000);
}