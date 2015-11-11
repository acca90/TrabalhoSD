/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author joaov
 */
@WebService(serviceName = "servidorSoap")
public class servidorSoap {
    
    //instacia do objeto
    ContatoService service = new ContatoService();
    
    //instancia do objeto erro;
    
    /**
     * Operação de Web service que retorna todos os contatos
     */
    @WebMethod(operationName = "getAll")
    public java.util.List<Contato> getAll() {
        return service.getAll();
    }
    
    /**
     * Operação de Web service que retorna os dados de um contato através do parametro id
     */
    @WebMethod(operationName = "getById")
    public Contato getById(@WebParam(name = "id") int id) {
       return service.getById(id);
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "delete")
    public classeErro delete(@WebParam(name = "id") Integer id) {
        Contato retorno = service.delete(id);
        
        classeErro e = new classeErro();
        
        if(retorno == null){
           e.setCodigo(1);
           e.setMsg("Erro ao apagar o contato, ou esse Código não existe!!!");
        }else{
           e.setCodigo(2);
           e.setMsg("Contato Apagado com Sucesso");
        }
        
        return e;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "insert")
    public classeErro insert(@WebParam(name = "c") Contato c) {
        Contato retorno =  service.add(c); 
        
        //instancia objeto erro
        classeErro e = new classeErro();
        
        if(retorno == null){
           e.setCodigo(1);
           e.setMsg("Erro ao Inserir");
        }else{
           e.setCodigo(2);
           e.setMsg("Contato Inserido com Sucesso");
        }
        
        return e;
    }
    
    
    

    
    
        
}
