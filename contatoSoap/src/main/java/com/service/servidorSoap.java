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
       Contato retorno = service.getById(id);
       
       retorno r = new retorno();
       
        if(retorno == null){
           r.setCodigo(1);
           r.setMsg("Erro na Consulta");
        }else{
           r.setCodigo(2);
           r.setMsg("Sucesso na Consulta");
           r.setContato(retorno);
        }
        
        return r;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "delete")
    public retorno delete(@WebParam(name = "id") Integer id) {
        Contato retorno = service.delete(id);
        
        if(retorno == null){
           r.setCodigo(1);
           r.setMsg("Erro ao apagar o contato, ou esse Código não existe!!!");
        }else{
           r.setCodigo(2);
           r.setMsg("Contato Apagado com Sucesso");
           r.setContato(retorno);
        }
        
        return r;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "insert")
    public retorno insert(@WebParam(name = "c") Contato c) {
        Contato retorno =  service.add(c); 
        
        if(retorno == null){
           r.setCodigo(1);
           r.setMsg("Erro ao Inserir");
        }else{
           r.setCodigo(2);
           r.setMsg("Contato Inserido com Sucesso");
           r.setContato(retorno);
        }
        
        return r;
    }  

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getByEmail")
    public retorno getByEmail(@WebParam(name = "email") String email) {
            r.setLista(service.getByEmail(email));           
            
            if(r.getLista().isEmpty()){
                r.setCodigo(3);
                r.setMsg("Nenhum Contato está vinculado a esse e-mail");
            }else{
                r.setCodigo(1);
                r.setMsg("Ok");
            }
            return r;
        
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getByCidade")
    public retorno getByCidade(@WebParam(name = "cidade") String cidade) {
        r.setLista(service.getByCidade(cidade));
        
        if(r.getLista().isEmpty()){
                r.setCodigo(3);
                r.setMsg("Nenhum Contato está vinculado a essa cidade!!!");
                r.getLista().clear();
            }else{
                r.setCodigo(1);
                r.setMsg("Ok");                
            }
        
        return r;     
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "update")
    public retorno update(@WebParam(name = "contato") Contato contato) {
        r.setContato(service.update(contato));
        
        if (r.getContato() == null){
            r.setCodigo(3);
            r.setMsg("Erro ao atualizar o contato!!!");
        }else{
            r.setCodigo(1);
            r.setMsg("Ok!!!");
        }
        
        return r;
    }

    
        
}


