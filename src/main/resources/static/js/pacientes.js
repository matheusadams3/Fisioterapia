document.addEventListener("DOMContentLoaded", () => {
    carregarPacientes();

    const campoBusca = document.getElementById("campoBusca");

    campoBusca.addEventListener("input", () => {
        buscarPacientes(campoBusca.value.trim());
    });

    const form = document.getElementById("formPaciente");
    const btnAdicionarCampo = document.getElementById("btnAdicionarCampo");
    const novosCampos = document.getElementById("novosCampos");
    const modalAdicionarPaciente = new bootstrap.Modal(document.getElementById("modalAdicionarPaciente"));

    /* ---------------------- UTIL ---------------------- */
    // formata tempo "08:00:00" -> "08:00", aceita também "08:00"
    function formatTimeForInput(t) {
        if (!t) return "";
        // se for ISO com fuso/extras, tenta extrair HH:mm
        // exemplos: "08:00:00", "08:00", "08:00:00.000"
        const m = t.match(/(\d{2}:\d{2})/);
        return m ? m[1] : "";
    }

    /* ---------------------- CARREGAR PACIENTES ---------------------- */

    async function carregarPacientes() {
        try {
            const response = await fetch("/api/pacientes");
            const pacientes = await response.json();

            const tbody = document.querySelector(".tabela-pacientes tbody");
            tbody.innerHTML = "";

            if (!pacientes || pacientes.length === 0) {
                document.getElementById("mensagemVazia").style.display = "block";
                document.getElementById("tabelaContainer").style.display = "none";
                return;
            }

            document.getElementById("mensagemVazia").style.display = "none";
            document.getElementById("tabelaContainer").style.display = "block";

            pacientes.forEach(p => {
                tbody.appendChild(criarLinhaPaciente(p));
            });

        } catch (e) {
            console.error("Erro ao carregar pacientes:", e);
        }
    }

    /* ---------------------- CALCULAR IDADE ---------------------- */

    function calcularIdade(dataNasc) {
        if (!dataNasc) return "—";
        const hoje = new Date();
        const nasc = new Date(dataNasc);
        if (isNaN(nasc)) return "—";
        let idade = hoje.getFullYear() - nasc.getFullYear();
        const m = hoje.getMonth() - nasc.getMonth();
        if (m < 0 || (m === 0 && hoje.getDate() < nasc.getDate())) idade--;
        return idade;
    }

    async function buscarPacientes(termo) {
        const endpoint = termo ? `/api/pacientes/search?termo=${encodeURIComponent(termo)}` : `/api/pacientes`;

        try {
            const response = await fetch(endpoint);
            const pacientes = await response.json();

            const tbody = document.querySelector(".tabela-pacientes tbody");
            tbody.innerHTML = "";

            if (pacientes.length === 0) {
                document.getElementById("mensagemVazia").style.display = "block";
                document.getElementById("tabelaContainer").style.display = "none";
                return;
            }

            document.getElementById("mensagemVazia").style.display = "none";
            document.getElementById("tabelaContainer").style.display = "block";

            pacientes.forEach(p => {
                tbody.appendChild(criarLinhaPaciente(p));
            });

        } catch (e) {
            console.error("Erro ao buscar:", e);
        }
    }

    /* ---------------------- MONTAR LINHA ---------------------- */

    function criarLinhaPaciente(p) {
        const tr = document.createElement("tr");

        // Formatar horário
        let horarioFormatado = "—";
        const inicioStr = formatTimeForInput(p.horarioInicio);
        const fimStr = formatTimeForInput(p.horarioFim);
        if (inicioStr && fimStr) {
            horarioFormatado = `${inicioStr} às ${fimStr}`;
        }

        const idade = calcularIdade(p.dataNascimento);

        const diabetesIcon = p.possuiDiabetes ? `<i class="bi bi-star-fill text-primary"></i>` : '';

        tr.innerHTML = `
            <td>${diabetesIcon} ${p.nomeCompleto}</td>
            <td>${p.telefone || "—"}</td>
            <td>${p.endereco || "—"}</td>
            <td>${p.observacoesGerais || "—"}</td>
            <td>${idade}</td>
            <td>${horarioFormatado}</td>
            <td>
                <button class="btn btn-sm btn-outline-primary btn-editar" data-id="${p.id}">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger btn-excluir ms-1" data-id="${p.id}">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        `;

        return tr;
    }

    document.querySelector("#offcanvasFiltros form").addEventListener("submit", function(e) {
        e.preventDefault();
        aplicarFiltros();
    });

    function aplicarFiltros() {
        const genero = document.getElementById("filtroGenero").value || null;
        const faixa = document.getElementById("filtroIdade").value || null;
        const status = document.getElementById("filtroStatus").value || null;

        const params = new URLSearchParams();
        if (genero) params.append("genero", genero);
        if (faixa) params.append("faixaEtaria", faixa);
        if (status) params.append("status", status);

        fetch(`/api/pacientes/filter?` + params.toString())
            .then(r => r.json())
            .then(lista => atualizarTabela(lista));
    }


    function atualizarTabela(pacientes) {
        const tbody = document.querySelector(".tabela-pacientes tbody");
        tbody.innerHTML = "";

        if (pacientes.length === 0) {
            document.getElementById("mensagemVazia").style.display = "block";
            document.getElementById("tabelaContainer").style.display = "none";
            return;
        }

        document.getElementById("mensagemVazia").style.display = "none";
        document.getElementById("tabelaContainer").style.display = "block";

        pacientes.forEach(p => {
            tbody.appendChild(criarLinhaPaciente(p));
        });
    }




    /* ---------------------- EDITAR PACIENTE ---------------------- */

    document.addEventListener("click", async (e) => {
        const btn = e.target.closest(".btn-editar");
        if (!btn) return;

        const id = btn.dataset.id;

        try {
            const response = await fetch(`/api/pacientes/${id}`);
            if (!response.ok) {
                console.error("Erro ao buscar paciente:", response.status, await response.text());
                return;
            }
            const p = await response.json();

            // Preenche campos básicos
            document.getElementById("idPaciente").value = p.id || "";
            document.getElementById("nomePaciente").value = p.nomeCompleto || "";
            document.getElementById("dataNascimento").value = p.dataNascimento || "";
            document.getElementById("telefonePaciente").value = p.telefone || "";
            document.getElementById("telefone2Paciente").value = p.telefoneSecundario || "";
            document.getElementById("enderecoPaciente").value = p.endereco || "";
            document.getElementById("observacoesPaciente").value = p.observacoesGerais || "";
            document.getElementById("generoPaciente").value = p.genero || "Selecione";
            document.getElementById("ativoPaciente").checked = p.ativo;



            document.getElementById("horarioInicio").value = formatTimeForInput(p.horarioInicio);
            document.getElementById("horarioFim").value = formatTimeForInput(p.horarioFim);
            document.getElementById("diabetesPaciente").checked = p.possuiDiabetes;

            modalAdicionarPaciente.show();
        } catch (err) {
            console.error("Erro ao editar paciente:", err);
        }
    });

    /* ---------------------- EXCLUIR PACIENTE ---------------------- */

    document.addEventListener("click", async (e) => {
        const btn = e.target.closest(".btn-excluir");
        if (!btn) return;

        if (!confirm("Deseja realmente excluir este paciente?")) return;

        const id = btn.dataset.id;
        await fetch(`/api/pacientes/${id}`, { method: "DELETE" });

        carregarPacientes();
    });

    /* ---------------------- CAMPOS DINÂMICOS ---------------------- */

    btnAdicionarCampo.addEventListener("click", () => {
        const div = document.createElement("div");
        div.classList.add("col-md-6", "mt-2");
        div.innerHTML = `<input type="text" class="form-control" placeholder="Novo campo personalizado">`;
        novosCampos.appendChild(div);
    });

    /* ---------------------- SALVAR PACIENTE ---------------------- */

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const id = document.getElementById("idPaciente").value;
        const inicio = document.getElementById("horarioInicio").value || null; // "HH:MM"
        const fim = document.getElementById("horarioFim").value || null;     // "HH:MM"

        const paciente = {
            nomeCompleto: document.getElementById("nomePaciente").value,
            dataNascimento: document.getElementById("dataNascimento").value,
            telefone: document.getElementById("telefonePaciente").value,
            telefoneSecundario: document.getElementById("telefone2Paciente").value,
            endereco: document.getElementById("enderecoPaciente").value,
            observacoesGerais: document.getElementById("observacoesPaciente").value,
            genero: document.getElementById("generoPaciente").value,
            possuiDiabetes: document.getElementById("diabetesPaciente").checked,
            ativo: document.getElementById("ativoPaciente").checked,
            inativo: !document.getElementById("ativoPaciente").checked,

            // enviar como o backend espera (LocalTime aceita "HH:mm")
            horarioInicio: inicio,
            horarioFim: fim
        };

        const metodo = id ? "PUT" : "POST";
        const url = id ? `/api/pacientes/${id}` : `/api/pacientes`;

        try {
            const resp = await fetch(url, {
                method: metodo,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(paciente)
            });

            if (!resp.ok) {
                const text = await resp.text();
                console.error("Erro ao salvar paciente:", resp.status, text);
                alert("Erro ao salvar paciente. Veja console para detalhes.");
                return;
            }

            modalAdicionarPaciente.hide();
            form.reset();
            carregarPacientes();
        } catch (err) {
            console.error("Erro na requisição:", err);
            alert("Erro de rede ao salvar paciente. Veja console.");
        }
    });
});
