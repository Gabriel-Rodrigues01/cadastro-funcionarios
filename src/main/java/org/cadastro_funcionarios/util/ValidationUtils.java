package org.cadastro_funcionarios.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public final class ValidationUtils {

    private ValidationUtils() { }


    public static boolean validarMatricula(String matricula) {
        return matricula != null && matricula.matches("\\d{6}");
    }

    public static boolean validarNome(String nome) {
        return nome != null && nome.trim().length() >= 2;
    }

}

