package br.edu.ifpb.executaveis;

import br.edu.ifpb.modelo.Cartao;
import br.edu.ifpb.modelo.Transacao;
import br.edu.ifpb.util.CartoesCadastrados;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Visa {
    private static final String NOME_FILA = "filaPagamentos2";
    private static final List<Transacao> transacoes = new ArrayList<Transacao>();
    private static final List<Cartao> cartoes = CartoesCadastrados.getLista();
    private static String pagamentoJSON;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");


        Connection connection = connectionFactory.newConnection();
        Channel canal = connection.createChannel();

        canal.exchangeDeclare(NOME_FILA,"fanout");
        String filaRandomica = canal.queueDeclare().getQueue();
        canal.queueBind(filaRandomica, NOME_FILA,"");

        DeliverCallback callback = (consumerTag, delivery)->{
            Gson gson = new Gson();
            pagamentoJSON = new String (delivery.getBody(), "UTF-8");
            Transacao transacao = gson.fromJson(pagamentoJSON,Transacao.class);

            Cartao cartao = CartoesCadastrados.buscarCartao(transacao.getNumeroCartao());

            System.out.println("Nome do cliente: " + cartao.getCliente().getNome());
            System.out.println("Dados bancários: " +
                               "\n  Agência: " + cartao.getCliente().getConta().getAgencia() +
                               "\n  Conta corrente: "  + cartao.getCliente().getConta().getNumeroConta());
            System.out.println("Número do cartão: " + transacao.getNumeroCartao());
            System.out.println("Valor da compra: "+ transacao.getValor());
            System.out.println("Estabelecimento: "+ transacao.getEstabelecimento().getNome()
                            + " CNPJ "
                            + transacao.getEstabelecimento().getCnpj());

//            System.out.println(String.format("Cartão: %s\n" +
//                            "Valor da compra: %s\n" +
//                            "Estabelecimento: %s CNPJ %s",
//                    transacao.getNumeroCartao(),
//                    transacao.getEstabelecimento().getNome(),
//                    transacao.getEstabelecimento().getCnpj()));

            System.out.println("Transação processada com sucesso!\n");

        };

        canal.basicConsume(filaRandomica,true,callback,consumerTag->{});

    }
}
