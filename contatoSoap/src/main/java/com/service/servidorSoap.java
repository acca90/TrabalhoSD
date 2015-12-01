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
    
    //instancia do objeto retorno
    retorno r = new retorno();

    
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
    public retorno getById(@WebParam(name = "id") int id) {       
        
        try {
            Contato retorno = service.getById(id);
            r.setCodigo(2);
            r.setMsg("Sucesso na Consulta");
            r.setContato(retorno);
        } catch (Exception ex) {
            System.out.println(ex);
            r.setCodigo(1);
            r.setMsg(ex.getMessage());
        } 
       
        
        return r;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "delete")
    public retorno delete(@WebParam(name = "id") Integer id) {        
        try {
            Contato retorno = service.delete(id);
            r.setCodigo(2);
            r.setMsg("Sucesso ao deletar o contato!");
            r.setContato(retorno);
        } catch (Exception ex) {
            System.out.println(ex);
            r.setCodigo(1);
            r.setMsg(ex.getMessage());
        } 
        
        return r;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "insert")
    public retorno insert(@WebParam(name = "c") Contato c) {
        
        
        try {
            Contato retorno = service.add(c);
            r.setCodigo(2);
            r.setMsg("Sucesso no Insert!");
            r.setContato(retorno);
        } catch (Exception ex) {
            System.out.println(ex);
            r.setCodigo(1);
            r.setMsg(ex.getMessage());
        } 
        
        return r;
    }  

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getByEmail")
    public retorno getByEmail(@WebParam(name = "email") String email) {
               
        try {
            r.setLista(service.getByEmail(email));
            r.setCodigo(2);
            r.setMsg("Sucesso na Consulta");
        } catch (Exception ex) {
            System.out.println(ex);
            r.setCodigo(1);
            r.setMsg(ex.getMessage());
        } 
            
        return r;
        
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getByCidade")
    public retorno getByCidade(@WebParam(name = "cidade") String cidade) {
       
        try {
            r.setLista(service.getByCidade(cidade));
            r.setCodigo(2);
            r.setMsg("Sucesso na Consulta");
        } catch (Exception ex) {
            System.out.println(ex);
            r.setCodigo(1);
            r.setMsg(ex.getMessage());
        } 
            
        return r;     
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "update")
    public retorno update(@WebParam(name = "contato") Contato contato) {
        r.setContato(service.getById(contato.getId()));
        
         try {
            Contato c = service.update(contato);
            r.setCodigo(2);
            r.setMsg("Sucesso na Consulta");
            r.setContato(c);
        } catch (Exception ex) {
            System.out.println(ex);
            r.setCodigo(1);
            r.setMsg(ex.getMessage());
        }
        
        return r;
    }

    
        
}


