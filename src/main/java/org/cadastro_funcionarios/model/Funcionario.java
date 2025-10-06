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

    // Construtor Completo
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

    // --- GETTERS (CRUCIAIS) ---
    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getCargo() { return cargo; }
    public BigDecimal getSalario() { return salario; }
    public LocalDate getDataContratacao() { return dataContratacao; }
    public Endereco getEndereco() { return endereco; }

    // Método para formatar para o CSV
    public String toCSV() {
        return matricula + ";" + nome + ";" + cpf + ";" + dataNascimento.toString() + ";" +
                cargo + ";" + salario.toString() + ";" + dataContratacao.toString() + ";" +
                endereco.toString();
    }
}