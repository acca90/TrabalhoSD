package com.mycompany.websocket;

/**
 *
 * @author Camila
 */
import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
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
@ServerEndpoint (
    value = "/echo", decoders = {contatoDecoder.class}, encoders = {contatoEncoder.class})
public class Server {
    
    //sincronizar
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    
    ContatoService service = new ContatoService();
    Contato c = new Contato();
    /**
     * @param session
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
       
        System.out.println(session.getId() + " has opened a connection"); 
        try {
            peers.add(session);
            session.getBasicRemote().sendText("Conexão Estabelecida");
        } catch (IOException ex) {
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     * @param message
     * @param session
     * @param json
     * @throws javax.websocket.EncodeException
     * @throws java.lang.CloneNotSupportedException
     */
    @OnMessage
    public void onMessage(contato aux, Session session) throws EncodeException, CloneNotSupportedException, IOException, DecodeException{
        Contato c = new Contato();
        String retorno;
        
        //adicionar
        if(aux.getOperacao() == 1){
            c = service.add(aux.getContato());        
            contato asd = new contato();
            if(c != null){
                retorno = "[{\n" +
                                    "\"log\":\""+session.getRequestURI().getHost()+"\",\n" +
                                    "\"erro\":\"0\",\n" +
                                    "\"codigo\":\"" +c.getId()+ "\",\n" +
                                    "\"nome\":\""+c.getNome()+"\",\n" +
                                    "\"email\":\""+c.getEmail()+"\",\n" +
                                    "\"emailAlter\":\""+c.getEmailAlternativo()+"\",\n" +
                                    "\"cep\":\""+c.getCep()+"\",\n" +
                                    "\"estado\":\""+c.getEstado()+"\",\n" +
                                    "\"cidade\":\""+c.getCidade()+"\",\n" +
                                    "\"endereco\":\""+c.getEndereco()+"\",\n" +
                                    "\"complemento\":\""+c.getComplemento()+"\"\n" +
                                    "}]";               
            }else{
                retorno = "[{\n" +
                                    "\"erro\":\"1\",\n" +                                    
                                    "}]";
                
            }
            
            for(Session s : peers){
                   s.getBasicRemote().sendText(retorno);
            }
        }
        
        //consultar por cidade
        if(aux.getOperacao() == 2){
            List<Contato> list = service.getByCidade(aux.getMsg());
            
            //se a lista não estiver vazia
            if(!list.isEmpty()){
                int i = 0;
                
                //percorro a lista
                for(Contato x : list){
                
                    String col = "";
                    String vir = ",";
                    if(i == 0){
                        col = "[";
                        
                    }
                    if(i == list.size()-1){
                        vir = "]";
                    }
                    retorno = ""+col+"{\n" +
                            "\"log\":\""+session.getId()+"\",\n" +
                            "\"codigo\":\"" +x.getId()+ "\",\n" +
                            "\"nome\":\""+x.getNome()+"\",\n" +
                            "\"email\":\""+x.getEmail()+"\",\n" +
                            "\"emailAlter\":\""+x.getEmailAlternativo()+"\",\n" +
                            "\"cep\":\""+x.getCep()+"\",\n" +
                            "\"estado\":\""+x.getEstado()+"\",\n" +
                            "\"cidade\":\""+x.getCidade()+"\",\n" +
                            "\"endereco\":\""+x.getEndereco()+"\",\n" +
                            "\"complemento\":\""+x.getComplemento()+"\"\n" +
                            "}"+vir+"";
                    //retorna somete para o cliente que solicitou
                    session.getBasicRemote().sendObject(retorno);
                    i++;
             }  
                
                
                
            }else{
                
            }
        }
    
    
    //consultar por ID
        if(aux.getOperacao() == 3){
            c = service.getById(Integer.parseInt(aux.getMsg()));
            
            if(c == null){
               retorno = "[{\n" +
                                    "\"erro\":\"1\",\n" +                                    
                                    "}]";
            }else{
                retorno = "[{\n" +
                                    "\"log\":\""+session.getRequestURI().getHost()+"\",\n" +
                                    "\"erro\":\"0\",\n" +
                                    "\"codigo\":\"" +c.getId()+ "\",\n" +
                                    "\"nome\":\""+c.getNome()+"\",\n" +
                                    "\"email\":\""+c.getEmail()+"\",\n" +
                                    "\"emailAlter\":\""+c.getEmailAlternativo()+"\",\n" +
                                    "\"cep\":\""+c.getCep()+"\",\n" +
                                    "\"estado\":\""+c.getEstado()+"\",\n" +
                                    "\"cidade\":\""+c.getCidade()+"\",\n" +
                                    "\"endereco\":\""+c.getEndereco()+"\",\n" +
                                    "\"complemento\":\""+c.getComplemento()+"\"\n" +
                                    "}]";  
                        
                //retorna somete para o cliente que solicitou
                session.getBasicRemote().sendText(retorno);
                
            }
    }
        
        
        //getALL
        if(aux.getOperacao() == 4){
            getALL();
        }
        
        
        if(aux.getOperacao() == 6){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Contato ct = service.delete(Integer.parseInt(aux.getMsg()));
            
            if(ct != null){
                getALL();
            }
            
        }
        
}

    @OnClose
    public void onClose(Session session){
        peers.remove(session);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    
    public void getALL(){
        String retorno;
         List<Contato> list = service.getAll();
            int i =0;
            for(Contato x : list){
                String col = "";
                    String vir = ",";
                    if(i == 0){
                        col = "[";
                        
                    }
                    if(i == list.size()-1){
                        vir = "]";
                    }
                    retorno = ""+col+"{\n" +                            
                            "\"codigo\":\"" +x.getId()+ "\",\n" +
                            "\"nome\":\""+x.getNome()+"\",\n" +
                            "\"email\":\""+x.getEmail()+"\",\n" +
                            "\"emailAlter\":\""+x.getEmailAlternativo()+"\",\n" +
                            "\"cep\":\""+x.getCep()+"\",\n" +
                            "\"estado\":\""+x.getEstado()+"\",\n" +
                            "\"cidade\":\""+x.getCidade()+"\",\n" +
                            "\"endereco\":\""+x.getEndereco()+"\",\n" +
                            "\"complemento\":\""+x.getComplemento()+"\"\n" +
                            "}"+vir+"";
                    //retorna somete para o cliente que solicitou
                    
                    i++;
                 
                for(Session s : peers){
                    try {
                        s.getBasicRemote().sendText(retorno);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
            }
    }
    
}