

package org.cadastro_funcionarios.util;

import org.cadastro_funcionarios.model.Funcionario;

import java.math.BigDecimal;
import java.util.*;
        import java.util.stream.Collectors;




public final class ReportUtils {
    private ReportUtils() { }

        public static List<Funcionario> filtrarPorCargo(List<Funcionario> lista, String cargo) {
        return lista.stream()
                .filter(f -> f.getCargo() != null && f.getCargo().equalsIgnoreCase(cargo))
                .collect(Collectors.toList());
    }
        public static List<Funcionario> filtrarPorFaixaSalarial(List<Funcionario> lista, BigDecimal min, BigDecimal max) {
        return lista.stream()
                .filter(f -> f.getSalario() != null && f.getSalario().compareTo(min) >= 0 && f.getSalario().compareTo(max) <= 0)
                .collect(Collectors.toList());
    }




        public static Map<String, BigDecimal> mediaSalarialPorCargo(List<Funcionario> lista) {
               Map<String, List<Funcionario>> grouped = lista.stream()
                .filter(f -> f.getCargo() != null && f.getSalario() != null)
                .collect(Collectors.groupingBy(f -> f.getCargo()));
               Map<String, BigDecimal> result = new LinkedHashMap<>();
        grouped.forEach((cargo, funcs) -> {
            BigDecimal sum = funcs.stream()
                    .map(Funcionario::getSalario)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal avg = sum.divide(new BigDecimal(funcs.size()), 2, BigDecimal.ROUND_HALF_UP);
            result.put(cargo, avg);
        });
        return result;
    }

      public static Map<String, List<Funcionario>> agruparPorCidade(List<Funcionario> lista) {
        return lista.stream()
                .filter(f -> f.getEndereco() != null && f.getEndereco().getCidade() != null)
                .collect(Collectors.groupingBy(f -> f.getEndereco().getCidade()));
    }

      public static Map<String, List<Funcionario>> agruparPorEstado(List<Funcionario> lista) {
        return lista.stream()
                .filter(f -> f.getEndereco() != null && f.getEndereco().getEstado() != null)
                .collect(Collectors.groupingBy(f -> f.getEndereco().getEstado()));
    }
}
