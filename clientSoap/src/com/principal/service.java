/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.principal;

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

   
    
    
}
