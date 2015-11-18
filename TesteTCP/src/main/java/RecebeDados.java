/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 * @author Matheus Sobucki
 */
public class RecebeDados {
     public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        // Cria o Socket que vai Se conectar com um ServerSocket (Servidor)    
        Socket cliente = new Socket("localhost", 1236);
        // Aguarda resposta do Servidor e joga no object quando receber algo
        ObjectInputStream vem = new ObjectInputStream(cliente.getInputStream());
       // Contato novo = new Contato();
        
        Contato novo = (Contato) vem.readObject();
       
       ContatoService servico = new ContatoService();
       servico.add(novo);
       
      
   }
}
