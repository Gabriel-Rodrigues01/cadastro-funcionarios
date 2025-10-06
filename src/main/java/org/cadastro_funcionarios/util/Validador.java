package org.cadastro_funcionarios.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class Validador {

    //MATRICULA DE 6 POSIÇOES
    private static final Pattern MATRICULA_PATTERN = Pattern.compile("^\\d{6}$");
    public static boolean validarMatricula(String matricula) {
        return matricula != null && MATRICULA_PATTERN.matcher(matricula).matches();
    }

    // NOME DO FUNCIONARIO
    public static boolean validarNome(String nome) {
        return nome != null && nome.trim().length() >= 3;
    }

    // CPF DO FUNCIONARIO
    // NOTA: Para validação completa de dígitos, use uma biblioteca externa ou uma implementação mais complexa.
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    public static boolean validarCPF(String cpf) {
        return cpf != null && CPF_PATTERN.matcher(cpf).matches();
    }


    public static boolean validarIdadeMinima(LocalDate dataNascimento, int idadeMinima) {
        if (dataNascimento == null) return false;

        // CALCULA IDADE PARA ATINGIMENTO MINIMO
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        return idade >= idadeMinima;
    }

    // SALARIO DO AUCILIAR DE ESCRAVO
    public static boolean validarSalario(BigDecimal salario) {
        // Verifica se é positivo (maior que zero)
        return salario != null && salario.compareTo(BigDecimal.ZERO) > 0;
    }

    // CEP

    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{5}-\\d{3}$");
    public static boolean validarCEP(String cep) {
        return cep != null && CEP_PATTERN.matcher(cep).matches();
    }
}