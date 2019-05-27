package br.edu.ifpb.modelo;

import br.edu.ifpb.interfaces.ICartao;
import br.edu.ifpb.util.Bancos;
import br.edu.ifpb.util.Bandeiras;

public class Cartao implements ICartao {
    private Cliente cliente;
    private String numero;
    private Bancos banco;
    private Bandeiras bandeira;
    private String senhaHash;

    public Cartao(Cliente cliente, String numero, Bancos banco, Bandeiras bandeira) {
        this.cliente = cliente;
        this.numero = numero;
        this.banco = banco;
        this.bandeira = bandeira;
    }

    public Cartao(String numero,Bandeiras bandeira){
        this.numero = numero;
        this.bandeira = bandeira;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Bandeiras getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeiras bandeira) {
        this.bandeira = bandeira;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Bancos getBanco() {
        return banco;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "numero='" + numero + '\'' +
                ", bandeira=" + bandeira +
                '}';
    }
}
