package br.upf.contato.testetcp;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.List;

public class TesteMain {
    //testes de como utilizar as dependencias com o CONTATOS DAL
    public static void maiggn(String args[]) { 
        ContatoService service = new ContatoService();
        List<Contato> contatos = service.getAll();
        
        for(Contato c: contatos) {
            System.out.println("Contato:" + c.getNome());
        }
        
        Contato novo = new Contato();
        novo.setNome("Camila");
        novo.setEmail("camila@gmail.com");
        Contato outro = service.add(novo);
        
        System.out.println("Contato id:" + outro.getId());
        System.out.println("Contato nome:" + outro.getNome());
    
}
}