package br.edu.ifpb.modelo;

public class Cliente {
    private String nome;
    private ContaCorrente conta;

    public Cliente(String nome, ContaCorrente conta) {
        this.nome = nome;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }
}
