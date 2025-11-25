document.addEventListener("DOMContentLoaded", () => {
    carregarPacientes();

    const tabela = document.getElementById("tabelaContainer")
    const mensagemVazia = document.getElementById("mensagemVazia")
    const campoBusca = document.getElementById("campoBusca");

    campoBusca.addEventListener("input", () => {
        buscarPacientes(campoBusca.value.trim());
    });

    const form = document.getElementById("formPaciente");
    const btnAdicionarCampo = document.getElementById("btnAdicionarCampo");
    const novosCampos = document.getElementById("novosCampos");
    const modalAdicionarPaciente = new bootstrap.Modal(document.getElementById("modalAdicionarPaciente"));

    /* ---------------------- UTIL ------------------------------ */
    // formata tempo "08:00:00" -> "08:00", aceita também "08:00"
    function formatTimeForInput(t) {
        if (!t) return "";
        // se for ISO com fuso/extras, tenta extrair HH:mm
        // exemplos: "08:00:00", "08:00", "08:00:00.000"
        const m = t.match(/(\d{2}:\d{2})/);
        return m ? m[1] : "";
    }

    /* ---------------------- CARREGAR PACIENTES ---------------- */

    async function carregarPacientes() {
        try {
            const response = await fetch("/api/pacientes");
            const pacientes = await response.json();

            const tbody = document.getElementById("tabelaPacientes");
            tbody.innerHTML = "";

            if (!pacientes || pacientes.length === 0) {
                tabela.classList.add("d-none")
                mensagemVazia.classList.remove("d-none")
                return;
            }

            mensagemVazia.classList.add("d-none")
            tabela.classList.remove("d-none")

            pacientes.forEach(p => {
                tbody.appendChild(criarLinhaPaciente(p));
            });

        } catch (e) {
            console.error("Erro ao carregar pacientes:", e);
        }
    }

    /* ---------------------- CALCULAR IDADE -------------------- */

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

            const tbody = document.getElementById("tabelaPacientes");
            tbody.innerHTML = "";

            if (pacientes.length === 0) {
                tabela.classList.add("d-none")
                mensagemVazia.classList.remove("d-none")
                return;
            }

            mensagemVazia.classList.add("d-none")
            tabela.classList.remove("d-none")

            pacientes.forEach(p => {
                tbody.appendChild(criarLinhaPaciente(p));
            });

        } catch (e) {
            console.error("Erro ao buscar:", e);
        }
    }

    /* ---------------------- MONTAR LINHA ----------------------- */

    function criarLinhaPaciente(p) {
        const tr = document.createElement("tr");

        const idade = calcularIdade(p.dataNascimento);

        const diabetesIcon = p.possuiDiabetes ? `<i class="bi bi-star-fill text-primary"></i>` : '';

        tr.innerHTML = `
            <td>${diabetesIcon} ${p.nomeCompleto}</td>
            <td>${p.telefone || "—"}</td>
            <td>${p.endereco || "—"}</td>
            <td>${p.observacoesGerais || "—"}</td>
            <td>${idade}</td>
            <td>
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

        const search = document.getElementById("campoBusca")?.value || null;
        const tamanho = document.getElementById("selectTamanhoLista")?.value || null;

        const params = new URLSearchParams();

        if (genero) params.append("genero", genero);
        if (faixa) params.append("faixaEtaria", faixa);
        if (status) params.append("status", status);
        if (search) params.append("search", search);
        if (tamanho) params.append("tamanho", tamanho);

        fetch(`/api/pacientes/filter?` + params.toString())
            .then(r => r.json())
            .then(lista => atualizarTabela(lista));

            const offcanvas = bootstrap.Offcanvas.getInstance(
                document.getElementById("offcanvasFiltros")
            );
            offcanvas.hide();
    }

    function atualizarTabela(pacientes) {
        const tbody = document.getElementById("tabelaPacientes");
        tbody.innerHTML = "";

        if (pacientes.length === 0) {
            tabela.classList.add("d-none")
            mensagemVazia.classList.remove("d-none")
            return;
        }

        mensagemVazia.classList.add("d-none")
        tabela.classList.remove("d-none")

        pacientes.forEach(p => {
            tbody.appendChild(criarLinhaPaciente(p));
        });
    }

    /* ---------------------- EXCLUIR PACIENTE --------------------- */

    document.addEventListener("click", async (e) => {
        const btn = e.target.closest(".btn-excluir");
        if (!btn) return;

        if (!confirm("Deseja realmente excluir este paciente?")) return;

        const id = btn.dataset.id;
        await fetch(`/api/pacientes/${id}`, { method: "DELETE" });

        carregarPacientes();
    });

    /* ---------------------- CAMPOS DINÂMICOS --------------------- */

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

        const paciente = {
            nomeCompleto: document.getElementById("nomePaciente").value,
            dataNascimento: document.getElementById("dataNascimento").value,
            telefone: document.getElementById("telefonePaciente").value,
            telefoneSecundario: document.getElementById("telefone2Paciente").value,
            endereco: document.getElementById("enderecoPaciente").value,
            observacoesGerais: document.getElementById("observacoesPaciente").value,
            genero: document.getElementById("generoPaciente").value,
            possuiDiabetes: document.getElementById("diabetesPaciente").checked,
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

     /* ----------------- MODAL REGISTRO DE PACIENTES --------------- */
     document.getElementById("tabelaPacientes").addEventListener("click", function (e) {

       if (e.target.closest(".btn-excluir")) return;

       const linha = e.target.closest("tr");
       if (!linha) return;

       const modal = new bootstrap.Modal(
         document.getElementById("modalRegistroPaciente")
       );
       modal.show();
     });

     /* ----------------- FUNÇÕES DO MODAL DE REGISTRO --------------- */
     function habilitarEdicao() {
       // habilita todos os campos da medição atual
       document.querySelectorAll('[id^="atual_"]').forEach((input) => {
         input.disabled = false;
       });

       // troca os botões
       document.getElementById("btnEditar").classList.add("d-none");
       document.getElementById("btnSalvar").classList.remove("d-none");
     }
     function salvarMedicao() {
       const dados = {
         pa: document.getElementById("atual_pa").value,
         glicemia: document.getElementById("atual_glicemia").value,
         dor: document.getElementById("atual_dor").value,
         o2: document.getElementById("atual_o2").value,
         bpm: document.getElementById("atual_bpm").value,
         obs: document.getElementById("atual_obs").value,
       };

       console.log("Salvar no backend:", dados);

       // depois de salvar no backend:
       document.querySelectorAll('[id^="atual_"]').forEach((input) => {
         input.disabled = true;
       });

       document.getElementById("btnSalvar").classList.add("d-none");
       document.getElementById("btnEditar").classList.remove("d-none");
     }
     document.getElementById("btnEditar").addEventListener("click", habilitarEdicao);
     document.getElementById("btnSalvar").addEventListener("click", salvarMedicao);

     const btnProximo = document.querySelector("#modalAdicionarPaciente .btn-primary");

       btnProximo.addEventListener("click", function (e) {
         e.preventDefault(); // evita submit do form

         // Fecha o primeiro modal
         const modalAdicionar = bootstrap.Modal.getInstance(document.getElementById("modalAdicionarPaciente"));
         modalAdicionar.hide();

         // Abre o segundo modal
         const modalAddRegistro = new bootstrap.Modal(document.getElementById("modalAddRegistroPaciente"));
         modalAddRegistro.show();
       });
});
