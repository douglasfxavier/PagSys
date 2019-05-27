package br.edu.ifpb.executaveis;

import br.edu.ifpb.modelo.Banco;
import br.edu.ifpb.modelo.Cartao;
import br.edu.ifpb.modelo.Transacao;
import br.edu.ifpb.util.Bancos;
import br.edu.ifpb.util.CartoesCadastrados;
import com.google.gson.Gson;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BancoDoBrasil {
    private static final Banco banco = new Banco();
    private static final List<Cartao> cartoes = CartoesCadastrados.getLista();
    private static final String NOME_FILA_ORIGEM = "filaBanco";
    private static final String NOME_FILA_DESTINO = "filaOperadora";
    private static String pagamentoJSON;

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");


        Connection connection = connectionFactory.newConnection();
        Channel canal = connection.createChannel();

        canal.exchangeDeclare(NOME_FILA_ORIGEM,"direct");
        String filaRandomica = canal.queueDeclare().getQueue();
        canal.queueBind(filaRandomica, NOME_FILA_ORIGEM, Bancos.BRASIL.name());

        DeliverCallback callback = (consumerTag, delivery)->{
            Gson gson = new Gson();
            pagamentoJSON = new String (delivery.getBody(), "UTF-8");

            Transacao transacao = gson.fromJson(pagamentoJSON,Transacao.class);

            Cartao cartao = CartoesCadastrados.buscarCartao(transacao.getNumeroCartao());

            try {
                if (banco.validarTransacao(transacao) == true){
                    System.out.println("Dados validados com sucesso!");
                    canal.basicPublish(NOME_FILA_DESTINO,cartao.getBandeira().name(), MessageProperties.PERSISTENT_TEXT_PLAIN, pagamentoJSON.getBytes("UTF-8"));
                } else{
                    System.out.println("Senha incorreta!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        canal.basicConsume(filaRandomica,true,callback,consumerTag->{});

    }
}
