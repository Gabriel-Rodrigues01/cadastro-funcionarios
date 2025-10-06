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

    // --- GETTERS (RESOLVE getCidade() e getEstado()) ---
    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; } // CRUCIAL para Relatórios
    public String getEstado() { return estado; } // CRUCIAL para Relatórios
    public String getCep() { return cep; }

    @Override
    public String toString() {
        return logradouro + ";" + numero + ";" + complemento + ";" + bairro + ";" + cidade + ";" + estado + ";" + cep;
    }
}