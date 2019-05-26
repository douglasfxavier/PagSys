package br.edu.ifpb.executaveis;

import br.edu.ifpb.modelo.Cartao;
import br.edu.ifpb.modelo.Estabelecimento;
import br.edu.ifpb.modelo.Transacao;
import br.edu.ifpb.util.CartoesCadastrados;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Maquineta {
    private static final Estabelecimento estabelecimento = new Estabelecimento("10.123.456/0001-99","Nova loja","Rua A");
    private static final String NOME_FILA = "filaPagamentos1";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        String pagamentoJSON = pagamento();

        try( Connection connection = connectionFactory.newConnection();
             Channel canal = connection.createChannel()){

            canal.exchangeDeclare(NOME_FILA,"fanout");
            canal.basicPublish(NOME_FILA,"", MessageProperties.PERSISTENT_TEXT_PLAIN, pagamentoJSON.getBytes("UTF-8"));
            System.out.println("Enviei mensagem: " + pagamentoJSON);
        }
    }


    private static String pagamento() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Numero do cartao VISA: ");
        String numeroCartao = scanner.next();

        System.out.print("Valor: ");
        double valor = scanner.nextDouble();

        System.out.print("Senha: ");
        String senha = scanner.next();

        Transacao transacao = new Transacao(estabelecimento,numeroCartao,valor,senha);
        Gson gson = new Gson();
        String transacaoJSON = gson.toJson(transacao);

        return  transacaoJSON;
    }

}
