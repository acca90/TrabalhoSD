/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import com.service.Contato;
import com.service.Retorno;

/**
 *
 * @author joaov
 */
public class service {

    public java.util.List<com.service.Contato> getAll() {
        com.service.ServidorSoap_Service service = new com.service.ServidorSoap_Service();
        com.service.ServidorSoap port = service.getServidorSoapPort();
        return port.getAll();
    }

    public Retorno insert(com.service.Contato c) {
        com.service.ServidorSoap_Service service = new com.service.ServidorSoap_Service();
        com.service.ServidorSoap port = service.getServidorSoapPort();
        return port.insert(c);
    }

    public Retorno delete(java.lang.Integer id) {
        com.service.ServidorSoap_Service service = new com.service.ServidorSoap_Service();
        com.service.ServidorSoap port = service.getServidorSoapPort();
        return port.delete(id);
    }

  

   

   
    
    
}