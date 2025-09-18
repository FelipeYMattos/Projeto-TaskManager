// ------------------- USUÁRIOS -------------------
const botaoListar = document.getElementById("botaoListar");
const botaoEnviarUser = document.getElementById("botaoEnviarUser");
const newUserContainer = document.getElementById("newUserContainer");

// Listar usuários
function listarUsers() {
    const tabelaUsers = document.getElementById("userTable");
    tabelaUsers.innerHTML = '';

    fetch("http://localhost:8080/usuario/all")
        .then(res => {
            if (!res.ok) throw new Error("Erro ao listar usuários");
            return res.json();
        })
        .then(usuarios => {
            usuarios.forEach(usuario => {
                tabelaUsers.innerHTML += `
                <tr>
                    <td>${usuario.id}</td>
                    <td>${usuario.nome}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.cargo}</td>
                    <td>${usuario.cpf}</td>
                    <td><button onclick="abrirAbaNovoUsuario(${usuario.id}, '${usuario.nome}', '${usuario.email}', '${usuario.senha}', '${usuario.cargo}', '${usuario.cpf}')">Editar</button></td>
                    <td><button onclick="deletarUsuario(${usuario.id})">Deletar</button></td>
                </tr>`;
            });
        })
        .catch(err => alert(err.message));
}


// Mostrar/ocultar formulário criar usuário
document.getElementById("showFormBtn").addEventListener("click", () => {
    newUserContainer.style.display = newUserContainer.style.display === "none" ? "block" : "none";
});

// Criar usuário via formulário principal
botaoEnviarUser.addEventListener("click", () => {
    const nome = document.getElementById("newName").value;
    const email = document.getElementById("newEmail").value;
    const senha = document.getElementById("newSenha").value;
    const cpf = document.getElementById("newCPF").value;
    const cargo = document.getElementById("newCargo").value;

    fetch("http://localhost:8080/usuario", {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({nome, email, senha, cargo, cpf})
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao criar usuário");
        newUserContainer.style.display = "none";
        document.getElementById("newUserForm").reset();
        listarUsers();
    })
});

// Abrir aba editar usuário
function abrirAbaNovoUsuario(id, nome, email, senha, cargo, cpf) {
    const aba = document.createElement("div");
    aba.style.position = "fixed";
    aba.style.top = "50%";
    aba.style.left = "50%";
    aba.style.transform = "translate(-50%, -50%)";
    aba.style.backgroundColor = "#2a2a2aff";
    aba.style.padding = "20px";
    aba.style.border = "1px solid #ccc";
    aba.style.boxShadow = "0 2px 10px rgba(0,0,0,0.3)";
    aba.style.zIndex = "1000";

    aba.innerHTML = `
        <h3>Editar Usuário</h3>
        <input type="text" id="novoNome" value="${nome}" placeholder="Nome"><br><br>
        <input type="email" id="novoEmail" value="${email}" placeholder="Email"><br><br>
        <input type="password" id="novoSenha" value="${senha}" placeholder="Senha"><br><br>
        <input type="text" id="novoCargo" value="${cargo}" placeholder="Cargo"><br><br>
        <input type="text" id="novoCpf" value="${cpf}" placeholder="CPF"><br><br>
        <button id="btnSalvarUsuario">Salvar</button>
        <button id="btnFecharAba">Fechar</button>
    `;
    document.body.appendChild(aba);

    document.getElementById("btnFecharAba").addEventListener("click", () => aba.remove());

    document.getElementById("btnSalvarUsuario").addEventListener("click", () => {
        const usuarioAtualizado = {
            nome: document.getElementById("novoNome").value,
            email: document.getElementById("novoEmail").value,
            senha: document.getElementById("novoSenha").value,
            cargo: document.getElementById("novoCargo").value,
            cpf: document.getElementById("novoCpf").value
        };

        fetch(`http://localhost:8080/usuario/${id}`, {
            method: "PUT",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify(usuarioAtualizado)
        })
        .then(res => {
            if(!res.ok) throw new Error("Erro ao atualizar usuário");
            aba.remove();
            listarUsers();
        })
        .catch(err => alert(err.message));
    });
}

// Deletar usuário
function deletarUsuario(id) {
    if(!confirm("Deseja realmente deletar este usuário?")) return;

    fetch(`http://localhost:8080/usuario/${id}`, { method: "DELETE" })
        .then(res => {
            if(!res.ok) throw new Error("Erro ao deletar usuário");
            listarUsers();
        })
        .catch(err => alert(err.message));
}

// ------------------- TAREFAS -------------------
const taskTable = document.getElementById("taskTable");
const botaoCriarTarefa = document.getElementById("botaoCriarTarefa");
document.querySelector("section:nth-of-type(2)").prepend(botaoCriarTarefa);

// Abrir formulário criar tarefa
function abrirFormularioNovaTarefa() {
    const containerNovaTarefa = document.createElement("div");
    containerNovaTarefa.style.position = "fixed";
    containerNovaTarefa.style.top = "50%";
    containerNovaTarefa.style.left = "50%";
    containerNovaTarefa.style.transform = "translate(-50%, -50%)";
    containerNovaTarefa.style.backgroundColor = "#2a2a2aff";
    containerNovaTarefa.style.padding = "20px";
    containerNovaTarefa.style.border = "1px solid #ccc";
    containerNovaTarefa.style.boxShadow = "0 2px 10px rgba(0,0,0,0.3)";
    containerNovaTarefa.style.zIndex = "1000";

    containerNovaTarefa.innerHTML = `
        <h3>Criar Nova Tarefa</h3>
        <input type="text" id="tarefaTitulo" placeholder="Título" required><br><br>
        <textarea id="tarefaDescricao" placeholder="Descrição" required></textarea><br><br>
        <input type="number" id="tarefaUsuarioId" placeholder="ID do Usuário" required><br><br>
        <input type="date" id="tarefaData" required><br><br>
        <input type="number" id="tarefaPrioridade" placeholder="Prioridade" required><br><br>
        <button id="btnSalvarTarefa">Salvar</button>
        <button id="btnFecharTarefa">Fechar</button>
    `;

    document.body.appendChild(containerNovaTarefa);

    document.getElementById("btnFecharTarefa").addEventListener("click", () => containerNovaTarefa.remove());

    document.getElementById("btnSalvarTarefa").addEventListener("click", () => {
        const novaTarefa = {
            idUsuario: parseInt(document.getElementById("tarefaUsuarioId").value),
            titulo: document.getElementById("tarefaTitulo").value,
            descricao: document.getElementById("tarefaDescricao").value,
            dataCriacao: document.getElementById("tarefaData").value,
            prioridade: parseInt(document.getElementById("tarefaPrioridade").value)
        };

        fetch("http://localhost:8080/tarefa", {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify(novaTarefa)
        })
        .then(res => {
            if(!res.ok) throw new Error("Erro ao criar tarefa");
            containerNovaTarefa.remove();
            listarTarefas();
        })
        .catch(err => alert(err.message));
    });
}

botaoCriarTarefa.addEventListener("click", abrirFormularioNovaTarefa);

// Listar tarefas
function listarTarefas() {
    taskTable.innerHTML = '';
    fetch("http://localhost:8080/tarefa/all")
        .then(res => res.json())
        .then(tarefas => {
            tarefas.forEach(t => {
                taskTable.innerHTML += `
                <tr>
                    <td>${t.id}</td>
                    <td>${t.titulo}</td>
                    <td>${t.descricao}</td>
                    <td>${t.idUsuario}</td>
                    <td>${t.status || 'Pendente'}</td>
                    <td>
                        <button onclick="deletarTarefa(${t.id})">Deletar</button>
                    </td>
                </tr>`;
            });
        });
}

// Deletar tarefa
function deletarTarefa(id) {
    if(!confirm("Deseja realmente deletar esta tarefa?")) return;

    fetch(`http://localhost:8080/tarefa/${id}`, { method: "DELETE" })
        .then(res => {
            if(!res.ok) throw new Error("Erro ao deletar tarefa");
            listarTarefas();
        })
        .catch(err => alert(err.message));
}

listarUsers();
listarTarefas();
