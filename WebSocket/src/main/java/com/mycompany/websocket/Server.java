package com.mycompany.websocket;

/**
 *
 * @author Camila
 */
import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
 
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/EchoChamber/echo
 * Where "localhost" is the address of the host,
 * "EchoChamber" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/echo") 
public class Server {
    
    ContatoService service = new ContatoService();
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
       
        System.out.println(session.getId() + " has opened a connection"); 
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session) throws EncodeException{
        String contato;
        //buscar todos os contatos
        if(message.contains("1")){
            
            List<Contato> list = service.getAll();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(list.size()); 
            
            int i = 0;            
            for(Contato c : list){
                
                String col = "";
                String vir = ",";
                try {
                    
                    if(i == 0){
                        col = "[";
                        
                    }
                    
                    if(i == list.size()-1){
                        vir = "]";
                    }
                    
                    contato = ""+col+"{\n" +
                                "\"codigo\":\"" +c.getId()+ "\",\n" +
                                "\"nome\":\""+c.getNome()+"\",\n" +
                                "\"email\":\""+c.getEmail()+"\",\n" +
                                "\"emailAlter\":\""+c.getEmailAlternativo()+"\",\n" +
                                "\"cep\":\""+c.getCep()+"\",\n" +
                                "\"estado\":\""+c.getEstado()+"\",\n" +
                                "\"cidade\":\""+c.getCidade()+"\",\n" +
                                "\"endereco\":\""+c.getEndereco()+"\",\n" +
                                "\"complemento\":\""+c.getComplemento()+"\"\n" +
                                "}"+vir+"";
                    session.getBasicRemote().sendText(contato);
                 } catch (IOException ex) {
                     Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                 }
                i++;
             }
        }      
        System.out.println("Message from " + session.getRequestURI()+ ": " + message);
        
        
    }
 
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
    }
    
}