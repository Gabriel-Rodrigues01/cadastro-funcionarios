package org.cadastro_funcionarios.model;

public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    // --- GETTERS (Requisitados por FuncionarioRepository e ReportUtils) ---
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    // O ReportUtils precisava deste:
    public String getCidade() { return cidade; }
    // O ReportUtils pode precisar deste:
    public String getEstado() { return estado; }
    public String getCep() { return cep; }

    // Método de persistência CSV (separado por ponto-e-vírgula)
    public String toCSV() {
        return String.join(";", logradouro, numero, complemento, bairro, cidade, estado, cep);
    }
}