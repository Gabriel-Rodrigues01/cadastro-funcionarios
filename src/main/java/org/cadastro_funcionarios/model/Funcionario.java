package org.cadastro_funcionarios.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario {
    private String matricula;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String cargo;
    private BigDecimal salario;
    private LocalDate dataContratacao;
    private Endereco endereco;

    public Funcionario(String matricula, String nome, String cpf, LocalDate dataNascimento, String cargo, BigDecimal salario, LocalDate dataContratacao, Endereco endereco) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.endereco = endereco;
    }

    // --- GETTERS (RESOLVE getMatricula(), getCargo(), getSalario(), etc.) ---
    public String getMatricula() { return matricula; } // CRUCIAL para Validação
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; } // CRUCIAL para Validação
    public String getCargo() { return cargo; } // CRUCIAL para Relatórios
    public BigDecimal getSalario() { return salario; } // CRUCIAL para Relatórios
    public LocalDate getDataContratacao() { return dataContratacao; }
    public Endereco getEndereco() { return endereco; } // CRUCIAL para Relatórios

    public String toCSV() {
        return matricula + ";" + nome + ";" + cpf + ";" + dataNascimento.toString() + ";" +
                cargo + ";" + salario.toString() + ";" + dataContratacao.toString() + ";" +
                endereco.toString();
    }
}