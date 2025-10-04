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
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        String digits = cpf.replaceAll("\\D", ""); // remove não-dígitos
        if (digits.length() != 11) return false;

        if (digits.matches("(\\d)\\1{10}")) return false;

        try {
            int[] d = new int[11];
            for (int i = 0; i < 11; i++) d[i] = digits.charAt(i) - '0';

            int sum = 0;
            for (int i = 0; i < 9; i++) sum += d[i] * (10 - i);
            int r = sum % 11;
            int dv1 = (r < 2) ? 0 : 11 - r;
            if (dv1 != d[9]) return false;


            sum = 0;
            for (int i = 0; i < 10; i++) sum += d[i] * (11 - i);
            r = sum % 11;
            int dv2 = (r < 2) ? 0 : 11 - r;
            return dv2 == d[10];
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validarIdadeMinima(LocalDate nascimento) {
        if (nascimento == null) return false;
        return Period.between(nascimento, LocalDate.now()).getYears() >= 18;
    }

    public static boolean validarSalario(BigDecimal salario) {
        return salario != null && salario.compareTo(BigDecimal.ZERO) > 0;
    }


    public static boolean validarCEP(String cep) {
        if (cep == null) return false;
        return cep.matches("\\d{5}-?\\d{3}");
    }
}

