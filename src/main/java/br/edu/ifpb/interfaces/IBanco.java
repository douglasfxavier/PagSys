package br.edu.ifpb.interfaces;

import br.edu.ifpb.modelo.Transacao;

public interface IBanco {
    boolean validarTransacao(Transacao transacao) throws Exception;
}
