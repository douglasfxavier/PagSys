package br.edu.ifpb.modelo;

public class Estabelecimento {
    private String cnpj;
    private String nome;
    private String endereco;

    public Estabelecimento(String cnpj, String none, String endereco) {
        this.cnpj = cnpj;
        this.nome = none;
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
