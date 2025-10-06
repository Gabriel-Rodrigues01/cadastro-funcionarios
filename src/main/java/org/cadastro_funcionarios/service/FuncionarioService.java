package org.cadastro_funcionarios.service;

import org.cadastro_funcionarios.model.Funcionario;
import org.cadastro_funcionarios.repository.FuncionarioRepository;
import org.cadastro_funcionarios.util.Validador;
import java.util.List;
import java.util.Optional;

public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    // --- Requisito 1: Cadastrar (com Validação) ---
    public void cadastrar(Funcionario funcionario) throws IllegalArgumentException {

        // 1. Validação de Regras de Negócio
        if (!Validador.validarMatricula(funcionario.getMatricula())) {
            throw new IllegalArgumentException("Matrícula Inválida. Use 6 dígitos numéricos.");
        }
        if (!Validador.validarNome(funcionario.getNome())) {
            throw new IllegalArgumentException("Nome Inválido. Deve ter no mínimo 3 caracteres.");
        }
        if (!Validador.validarIdadeMinima(funcionario.getDataNascimento(), 18)) {
            throw new IllegalArgumentException("Funcionário deve ter no mínimo 18 anos.");
        }
        if (!Validador.validarSalario(funcionario.getSalario())) {
            throw new IllegalArgumentException("Salário deve ser um valor positivo.");
        }
        if (!Validador.validarCEP(funcionario.getEndereco().getCep())) {
            throw new IllegalArgumentException("CEP Inválido. Use o formato XXXXX-XXX.");
        }
        // Adicione outras validações (CPF, Matrícula Duplicada, etc.)

        // 2. Persistência
        repository.adicionar(funcionario);
    }

    // --- Requisito 1: Excluir ---
    public boolean excluir(String matricula) {
        return repository.excluir(matricula);
    }

    // --- Requisito 1: Consultar ---
    public Optional<Funcionario> consultarPorMatricula(String matricula) {
        return repository.buscarPorMatricula(matricula);
    }

    // --- Requisito 1: Listar Todos ---
    public List<Funcionario> listarTodos() {
        return repository.listarTodos();
    }

    // NOTA: Os métodos de Relatório (Stream API) seriam implementados aqui ou chamados da classe ReportUtils.
}