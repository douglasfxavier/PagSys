package br.edu.ifpb.util;

import br.edu.ifpb.modelo.Cartao;
import br.edu.ifpb.modelo.Cliente;
import br.edu.ifpb.modelo.ContaCorrente;
import br.edu.ifpb.util.Bandeiras;

import java.util.ArrayList;
import java.util.List;

public class CartoesCadastrados {

    private static List<Cartao> lista;


    public static List<Cartao> getLista(){
        if (lista == null){
            lista =  new ArrayList<Cartao>();


            Cliente cliente1 = new Cliente("Joao",new ContaCorrente("1010-0","12345-6"));
            Cartao cartao1 = new Cartao(cliente1,"1111" , Bandeiras.VISA);
            cartao1.setSenhaHash("123");
            lista.add(cartao1);

            Cliente cliente2 = new Cliente("Maria",new ContaCorrente("2020-1","23456-7"));
            Cartao cartao2 = new Cartao(cliente2,"2222" , Bandeiras.VISA);
            cartao2.setSenhaHash("456");
            lista.add(cartao2);

            Cliente cliente3 = new Cliente("Pedro",new ContaCorrente("3030-2","34567-8"));
            Cartao cartao3 = new Cartao(cliente3,"3333" , Bandeiras.VISA);
            cartao3.setSenhaHash("789");
            lista.add(cartao3);

        }
        return lista;
    }


    public static Cartao buscarCartao(String numero){
        for(Cartao c: lista){
            if (c.getNumero().equals(numero))  return c;
        }
        return null;
    }

}
