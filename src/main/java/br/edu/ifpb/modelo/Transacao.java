package br.edu.ifpb.modelo;

import br.edu.ifpb.util.Bancos;

public class Transacao {
    private Estabelecimento estabelecimento;
    private double valor;
    private Bancos banco;
    private String numeroCartao;
    private String senhaHash;

    public Transacao(Estabelecimento estabelecimento, double valor, Bancos banco, String numeroCartao, String senhaHash) {
        this.estabelecimento = estabelecimento;
        this.valor = valor;
        this.banco = banco;
        this.numeroCartao = numeroCartao;
        this.senhaHash = senhaHash;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public double getValor() {
        return valor;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public Bancos getBanco() {
        return banco;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "estabelecimento=" + estabelecimento +
                ", valor=" + valor +
                ", cartao=" + numeroCartao +
                ", senhaHash='" + senhaHash + '\'' +
                '}';
    }
}
