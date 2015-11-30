/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.rest.integracao.webservicerestconect;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonas
 */
public class Mediador {
    private ContatoService service = new ContatoService();
    
    public List<Contato> getContatos(){
        try{
            return service.getAll();
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Contato getContato(Integer id){
        try{
            return service.getById(id);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public List<Contato> getByCidade(String cidade){
        try{
            return service.getByCidade(cidade);
        }catch(RuntimeException e){
        }
        return null;
    }
    
    public List<Contato> postContatos(List<Contato> contatos){
        List<Contato> contatosNaoInseridos = new ArrayList<>();
        contatosNaoInseridos.addAll(contatos);
        for(Contato c:contatos){
            try{
                contatosNaoInseridos.remove(service.add(c));
            }catch(RuntimeException e){
            }
        }
        return contatosNaoInseridos;
    }
    
    public boolean postContato(Contato contato){
        try{
            return service.add(contato).equals(contato);
        }catch(RuntimeException e){
        }
        return false;
    }
    
    public boolean putContato(Contato contato){
        try{
            return service.update(contato).equals(contato);
        }catch(RuntimeException e){
        }
        return false;
    }
    
    public boolean deleteContato(Integer id){
        try{
            return service.delete(id).getId().equals(id);
        }catch(RuntimeException e){
        }
        return false;
    }
    
    public boolean deleteContato(Contato contato){
        try{
            return service.delete(contato).equals(contato);
        }catch(RuntimeException e){
        }
        return false;
    }
}
