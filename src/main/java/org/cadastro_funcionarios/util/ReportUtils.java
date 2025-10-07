package org.cadastro_funcionarios.util;

import org.cadastro_funcionarios.model.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportUtils {

    // CALCULO DA MEDIA SALARIA POR CARGO
    public static Map<String, BigDecimal> agruparMediaSalarialPorCargo(List<Funcionario> lista) {
        return lista.stream()




                .filter(f -> f.getCargo() != null && f.getSalario() != null)
                .collect(Collectors.groupingBy(
                        Funcionario::getCargo,



                // RESPONSAVEL POR COLETAR A RESPOSTA DA MEDIA
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Funcionario::getSalario,
                                (soma, salario) -> soma.add(salario)
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            long count = lista.stream()
                                    .filter(f -> entry.getKey().equals(f.getCargo()))
                                    .count();




                            return entry.getValue().divide(
                                    new BigDecimal(count),
                                    2, // 2 casas decimais
                                    RoundingMode.HALF_UP
                            );
                        }
                ));
    }

    // AGRUPA FUNCIONARIO POR REGIONAL
    public static Map<String, List<Funcionario>> agruparPorCidade(List<Funcionario> lista) {
        return lista.stream()


                .filter(f -> f.getEndereco() != null && f.getEndereco().getCidade() != null)
                .collect(Collectors.groupingBy(
                        f -> f.getEndereco().getCidade()
                ));
    }
}
