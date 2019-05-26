package br.edu.ifpb.modelo;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
    private String agencia;
    private String numeroConta;
    private List<Cartao> cartoes;

    public ContaCorrente(String agencia, String numeroConta) {
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.cartoes = new ArrayList<Cartao>();
    }

    public void adicionarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }

    public void  removerCartao(Cartao cartao){
        this.cartoes.remove(cartao);
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
}
