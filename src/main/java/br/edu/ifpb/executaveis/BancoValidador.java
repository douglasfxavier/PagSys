package br.edu.ifpb.executaveis;

import br.edu.ifpb.modelo.Banco;
import br.edu.ifpb.modelo.Transacao;
import com.google.gson.Gson;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BancoValidador {
    private static final Banco banco = new Banco();
    private static final String NOME_FILA_ORIGEM = "filaPagamentos1";
    private static final String NOME_FILA_DESTINO = "filaPagamentos2";
    private static String pagamentoJSON;

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");


        Connection connection = connectionFactory.newConnection();
        Channel canal = connection.createChannel();

        canal.exchangeDeclare(NOME_FILA_ORIGEM,"fanout");
        String filaRandomica = canal.queueDeclare().getQueue();
        canal.queueBind(filaRandomica, NOME_FILA_ORIGEM,"");

        DeliverCallback callback = (consumerTag, delivery)->{
            Gson gson = new Gson();
            pagamentoJSON = new String (delivery.getBody(), "UTF-8");
            Transacao transacao = gson.fromJson(pagamentoJSON,Transacao.class);
            try {
                if (banco.validarTransacao(transacao) == true){
                    System.out.println("Dados validados com sucesso!");
                    canal.basicPublish(NOME_FILA_DESTINO,"", MessageProperties.PERSISTENT_TEXT_PLAIN, pagamentoJSON.getBytes("UTF-8"));
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
