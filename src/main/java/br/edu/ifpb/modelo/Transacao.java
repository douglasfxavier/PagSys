package br.edu.ifpb.modelo;

public class Transacao {
    private Estabelecimento estabelecimento;
    private double valor;
    private String numeroCartao;
    private String senhaHash;

    public Transacao(Estabelecimento estabelecimento, String numeroCartao,  double valor, String senhaHash) {
        this.estabelecimento = estabelecimento;
        this.numeroCartao = numeroCartao;
        this.valor = valor;
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
