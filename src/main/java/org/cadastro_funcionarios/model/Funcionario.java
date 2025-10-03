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