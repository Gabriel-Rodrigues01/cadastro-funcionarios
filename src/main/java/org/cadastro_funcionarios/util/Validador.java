package org.cadastro_funcionarios.util;

import java.time.LocalDate;
import java.time.Period;

public class Validador {

    // Método necessário pelo FuncionarioService
    public static boolean validarMatricula(String matricula) {
        // Assume 6 dígitos numéricos
        return matricula != null && matricula.matches("^\\d{6}$");
    }

    // Exemplo de outro método que você precisará
    public static boolean validarIdadeMinima(LocalDate dataNascimento, int idadeMinima) {
        if (dataNascimento == null) return false;
        return Period.between(dataNascimento, LocalDate.now()).getYears() >= idadeMinima;
    }
}