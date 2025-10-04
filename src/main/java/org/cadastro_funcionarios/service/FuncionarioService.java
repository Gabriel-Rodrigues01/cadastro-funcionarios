package org.cadastro_funcionarios.service;

import org.cadastro_funcionarios.model.Funcionario;
import org.cadastro_funcionarios.repository.FuncionarioRepository;
import org.cadastro_funcionarios.util.ValidationUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class FuncionarioService {
    private final FuncionarioRepository repo;

    public FuncionarioService(FuncionarioRepository repo) {
        this.repo = repo;
    }

    public void load() throws IOException {
        repo.load();
    }

    public List<Funcionario> listarTodos() {
        return repo.findAll();
    }

    public Optional<Funcionario> consultarPorMatricula(String matricula) {
        return repo.findByMatricula(matricula);
    }

    public void cadastrar(Funcionario f) throws IOException {
        if (!ValidationUtils.validarMatricula(f.getMatricula())) {
            throw new IllegalArgumentException("Matrícula inválida: deve conter 6 dígitos numéricos.");
        }
        if (!ValidationUtils.validarNome(f.getNome())) {
            throw new IllegalArgumentException("Nome inválido: mínimo 2 caracteres.");
        }
        if (!ValidationUtils.validarCPF(f.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        if (!ValidationUtils.validarIdadeMinima(f.getDataNascimento())) {
            throw new IllegalArgumentException("Funcionário deve ter ao menos 18 anos.");
        }
        if (!ValidationUtils.validarSalario(f.getSalario())) {
            throw new IllegalArgumentException("Salário deve ser positivo.");
        }
        if (!ValidationUtils.validarCEP(f.getEndereco().getCep())) {
            throw new IllegalArgumentException("CEP inválido.");
        }

        repo.add(f);
        repo.saveAll(); // persistir após adicionar
    }

    public void excluir(String matricula) throws IOException {
        repo.remove(matricula);
        repo.saveAll(); // persistir após remover
    }
}
