package br.edu.ifpb.modelo;

import br.edu.ifpb.util.CartoesCadastrados;
import br.edu.ifpb.interfaces.IBanco;

import java.util.List;

public class Banco implements IBanco {
    List<Cartao> cartoesCadastrados;

    public Banco() {

        this.cartoesCadastrados = cartoesCadastrados = CartoesCadastrados.getLista();
    }

    @Override
    public boolean validarTransacao(Transacao transacao) throws Exception {
        String numeroCartao = transacao.getNumeroCartao();
        Cartao cartao = CartoesCadastrados.buscarCartao(numeroCartao);

        if (cartao == null){
            throw new NullPointerException("Cartao inexistente");
        }

        if (!transacao.getSenhaHash().equals(cartao.getSenhaHash())){
            return false;
        }

        return true;

    }
}
