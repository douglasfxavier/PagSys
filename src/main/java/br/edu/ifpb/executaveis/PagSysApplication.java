package br.edu.ifpb.executaveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PagSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(PagSysApplication.class,args);
    }


    @RequestMapping("/greet/{nome}")
    public String helloGreeting(@PathVariable("nome") String nome){
        return  "Hello REST " + nome;
    }
}
