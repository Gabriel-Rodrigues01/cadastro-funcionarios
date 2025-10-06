package org.cadastro_funcionarios.util;

import org.cadastro_funcionarios.model.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportUtils {

    // 1. Calcular média salarial por cargo (Requisito 3)
    public static Map<String, BigDecimal> agruparMediaSalarialPorCargo(List<Funcionario> lista) {
        return lista.stream()
                // Filtra para garantir que cargo e salário não sejam nulos
                .filter(f -> f.getCargo() != null && f.getSalario() != null)
                .collect(Collectors.groupingBy(
                        Funcionario::getCargo,
                        // Coletor que calcula a média do salário
                        Collectors.reducing(
                                BigDecimal.ZERO, // Valor inicial
                                Funcionario::getSalario, // Mapeamento
                                (soma, salario) -> soma.add(salario) // Acumulador (soma)
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            long count = lista.stream()
                                    .filter(f -> entry.getKey().equals(f.getCargo()))
                                    .count();
                            // Garante que a divisão é feita corretamente com o count
                            return entry.getValue().divide(
                                    new BigDecimal(count),
                                    2, // 2 casas decimais
                                    RoundingMode.HALF_UP
                            );
                        }
                ));
    }

    // 2. Listar funcionários agrupados por cidade (Requisito 3)
    public static Map<String, List<Funcionario>> agruparPorCidade(List<Funcionario> lista) {
        return lista.stream()
                // Filtra nulos no Endereço e na Cidade, resolvendo o erro 'getCidade'
                .filter(f -> f.getEndereco() != null && f.getEndereco().getCidade() != null)
                .collect(Collectors.groupingBy(
                        f -> f.getEndereco().getCidade()
                ));
    }
}