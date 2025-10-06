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

    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getCargo() { return cargo; } // Requisitado pelo ReportUtils
    public BigDecimal getSalario() { return salario; } // Requisitado pelo ReportUtils
    public LocalDate getDataContratacao() { return dataContratacao; }
    public Endereco getEndereco() { return endereco; } // Requisitado pelo ReportUtils



    //METODO QUE ORGANIZA O CSV PRA NÃO APAGAR (PRA NÃO FICAR JOGADO AOS CARALHOS E SUMIR)
    public String toCSV() {
        return String.join(";",
                matricula, nome, cpf, dataNascimento.toString(), cargo, salario.toString(),
                dataContratacao.toString(), endereco.toCSV()
        );
    }
}