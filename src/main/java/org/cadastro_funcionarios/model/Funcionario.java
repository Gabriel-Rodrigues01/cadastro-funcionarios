package org.cadastro_funcionarios.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;



public class Funcionario {
    private String matricula;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String cargo;
    private BigDecimal salario;
    private LocalDate dataContratacao;
    private Endereco endereco;
    public Funcionario() { }

    public Funcionario(String matricula, String nome, String cpf, LocalDate dataNascimento,
                       String cargo, BigDecimal salario, LocalDate dataContratacao, Endereco endereco) {
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
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public LocalDate getDataContratacao() { return dataContratacao; }
    public void setDataContratacao(LocalDate dataContratacao) { this.dataContratacao = dataContratacao; }
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }


    public String toCsvLine() {
        return escape(matricula) + ";" +
                escape(nome) + ";" +
                escape(cpf) + ";" +
                (dataNascimento != null ? dataNascimento.toString() : "") + ";" +
                escape(cargo) + ";" +
                (salario != null ? salario.toPlainString() : "") + ";" +
                (dataContratacao != null ? dataContratacao.toString() : "") + ";" +
                escape(endereco != null ? endereco.getLogradouro() : "") + ";" +
                escape(endereco != null ? endereco.getNumero() : "") + ";" +
                escape(endereco != null ? endereco.getComplemento() : "") + ";" +
                escape(endereco != null ? endereco.getBairro() : "") + ";" +
                escape(endereco != null ? endereco.getCidade() : "") + ";" +
                escape(endereco != null ? endereco.getEstado() : "") + ";" +
                escape(endereco != null ? endereco.getCep() : "");
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace(";", ","); // simples escape substituindo ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Funcionario that = (Funcionario) o;
        return Objects.equals(matricula, that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + " - " + cargo + " - R$ " + (salario != null ? salario.toPlainString() : "0.00");
    }
}