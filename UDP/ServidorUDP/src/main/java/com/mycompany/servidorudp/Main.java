/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servidorudp;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void maiggn(String args[]) { 
        ContatoService service = new ContatoService();
        List<Contato> contatos = service.getAll();
        
        for(Contato c: contatos) {
            System.out.println("Contato:" + c.getNome());
        }
        
        Contato novo = new Contato();
        novo.setNome("Felipe");
        novo.setEmail("felipe@hotmail.com");
        Contato outro = service.add(novo);
        
        System.out.println("Contato id:" + outro.getId());
        System.out.println("Contato nome:" + outro.getNome());
    }
    
}
