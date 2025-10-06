package org.cadastro_funcionarios.service;

import org.cadastro_funcionarios.model.Funcionario;
import org.cadastro_funcionarios.repository.FuncionarioRepository;
import org.cadastro_funcionarios.util.Validador; // AGORA FUNCIONA
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Funcionario f) throws Exception {
        // Validação (agora usa o Validador e os Getters)
        if (!Validador.validarMatricula(f.getMatricula())) {
            throw new IllegalArgumentException("Matrícula inválida. Use 6 dígitos.");
        }
        if (!Validador.validarIdadeMinima(f.getDataNascimento(), 18)) {
            throw new IllegalArgumentException("Funcionário deve ter no mínimo 18 anos.");
        }
        // ...

        repository.adicionar(f);
    }

    // Método de Relatório Exemplo (CORRIGE o erro de Deprecated)
    public Map<String, Double> calcularMediaSalarialPorCargo() {
        return repository.listarTodos().stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getCargo,
                        Collectors.averagingDouble(f -> f.getSalario().doubleValue())
                ));
    }

    // Se você tiver um método agruparPorCidade() em ReportUtils, ele funcionará
    // porque f.getEndereco().getCidade() agora existe no Endereco.java
}