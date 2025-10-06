package org.cadastro_funcionarios.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Validador {

    public static boolean validarMatricula(String matricula) {
        return matricula != null && matricula.matches("^\\d{6}$");
    }

    public static boolean validarIdadeMinima(LocalDate dataNascimento, int idadeMinima) {
        if (dataNascimento == null) return false;
        return Period.between(dataNascimento, LocalDate.now()).getYears() >= idadeMinima;
    }

    // Adicione validações de CPF, Salário, etc.
}