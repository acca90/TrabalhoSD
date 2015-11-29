
import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Matheus Sobucki
 */
public class teste {
    public static void main(String[] args) throws IOException {
       
        
        String operacao;
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Escolha sua operação [listar, incluir, editar, deletar]: ");
        operacao = entrada.nextLine();        
       
       switch(operacao) {        
            case "listar":        
                ContatoService service1 = new ContatoService();
                List<Contato> contatos = service1.getAll();
                for(Contato c: contatos) {
                    System.out.println("ID:" + c.getId());
                    System.out.println("Nome:" + c.getNome());
                    System.out.println("Email:" + c.getEmail());
                    System.out.println("Endereco:" + c.getEndereco());
                    System.out.println("Comp:" + c.getComplemento());
                    System.out.println("CEP:" + c.getCep());
                    System.out.println("Cidade:" + c.getCidade());
                    System.out.println("Estado:" + c.getEstado());
                    System.out.println("Email Alt.:" + c.getEmailAlternativo());
                    System.out.println("------------");
                }      
            break;
              
            case "incluir":
            String id;
            String nome;
            String email;
            String end;
            String comp;
            String cep;
            String cid;
            String est;
        
        
                                        
            Scanner sler = new Scanner (System.in); 
            Contato novo = new Contato();
            System.out.printf("Digite o id:");
            id = sler.next();
            System.out.printf("Digite o nome:");
            nome = sler.next();
            System.out.printf("Digite o email:");
            email = sler.next();
            System.out.printf("Digite o endereco:");
            end = sler.next();
            System.out.printf("Digite o complemento:");
            comp = sler.next();
            System.out.printf("Digite o cep:");
            cep = sler.next();
            System.out.printf("Digite a cidade:");
            cid = sler.next();
            System.out.printf("Digite o estado:");
            est = sler.next();
        
 
            int numCep = Integer.parseInt(cep);
            int numId = Integer.parseInt(id);
            novo.setId(numId);
            novo.setNome(nome);
            novo.setEmail(email);
            novo.setEndereco(end);
            novo.setComplemento(comp);
            novo.setCep(numCep);
            novo.setCidade(cid);
            novo.setEstado(est);
            
            try ( // Cria um ServerSocket que vai se conectar com sockets
                    
                ServerSocket servidor = new ServerSocket(1236)) {
                System.out.println("Aguardando conexÃ£o");
                // Cria um Socket que Ã© definido como a abertura de conexÃ£o com o Server
                Socket cliente = servidor.accept();
                //TratamentoClass tratamento = new TratamentoClass(cliente);
                    // cria a thread em cima deste objeto
                // Thread t = new Thread(tratamento);
                // inicia a thread
                //  t.start();
                System.out.println("Conectou");
                //Escreve a casca da saida, Ã© aqui que vocÃª define onde o que sera enviado vai ficar
                ObjectOutputStream sai = new ObjectOutputStream(cliente.getOutputStream());
                ObjectOutputStream sai2 = new ObjectOutputStream(cliente.getOutputStream());
                // String que vai ser colocada na casca
                
                // Envio da string encapsulada
                String op = "incluir";
                
                sai.writeObject(novo);
                sai2.writeObject(op);
                System.out.println("Enviou");
                // Limpa o object
                sai.flush();
                sai.close();
                }
       
            break;
                
            case "editar":
                 Scanner scann = new Scanner (System.in);
                 System.out.println("Informe o id do contato à alterar: "); 
                 String idUpd = scann.next();
                 int numIdUpd = Integer.parseInt(idUpd);
                 ContatoService service2 = new ContatoService();
                 Contato contatos2 = new Contato();
                
            
            String nome2;
            String email2;
            String end2;
            String comp2;
            String cep2;
            String cid2;
            String est2;
        
 
            System.out.printf("Digite o nome:");
            nome2 = scann.next();
            System.out.printf("Digite o email:");
            email2 = scann.next();
            System.out.printf("Digite o endereco:");
            end2 = scann.next();
            System.out.printf("Digite o complemento:");
            comp2 = scann.next();
            System.out.printf("Digite o cep:");
            cep2 = scann.next();
            System.out.printf("Digite a cidade:");
            cid2 = scann.next();
            System.out.printf("Digite o estado:");
            est2 = scann.next();
        
 
            int numCep2 = Integer.parseInt(cep2);
            contatos2.setId(numIdUpd);
            contatos2.setNome(nome2);
            contatos2.setEmail(email2);
            contatos2.setEndereco(end2);
            contatos2.setComplemento(comp2);
            contatos2.setCep(numCep2);
            contatos2.setCidade(cid2);
            contatos2.setEstado(est2);
                
             try ( // Cria um ServerSocket que vai se conectar com sockets
                    
                ServerSocket servidor = new ServerSocket(1236)) {
                System.out.println("Aguardando conexão");
                // Cria um Socket que Ã© definido como a abertura de conexÃ£o com o Server
                Socket cliente = servidor.accept();
                //TratamentoClass tratamento = new TratamentoClass(cliente);
                    // cria a thread em cima deste objeto
                // Thread t = new Thread(tratamento);
                // inicia a thread
                //  t.start();
                System.out.println("Conectou");
                //Escreve a casca da saida, Ã© aqui que vocÃª define onde o que sera enviado vai ficar
                ObjectOutputStream sai = new ObjectOutputStream(cliente.getOutputStream());
                ObjectOutputStream sai2 = new ObjectOutputStream(cliente.getOutputStream());
                // String que vai ser colocada na casca
                String op = "editar";
                // Envio da string encapsulada
                sai.writeObject(contatos2);
                sai2.writeObject(op);
                System.out.println("Enviou");
                // Limpa o object
                sai.flush();
                sai.close();
                }   
            break;
                
            case "deletar":
                String idDel;
                Scanner scan = new Scanner (System.in);
                System.out.println("Informe o id do contato à deletar"); 
                idDel = scan.next();
                int numIdDel = Integer.parseInt(idDel);
                
                try ( // Cria um ServerSocket que vai se conectar com sockets
                    
                ServerSocket servidor = new ServerSocket(1236)) {
                System.out.println("Aguardando conexÃ£o");
                // Cria um Socket que Ã© definido como a abertura de conexÃ£o com o Server
                Socket cliente = servidor.accept();
                //TratamentoClass tratamento = new TratamentoClass(cliente);
                    // cria a thread em cima deste objeto
                // Thread t = new Thread(tratamento);
                // inicia a thread
                //  t.start();
                System.out.println("Conectou");
                //Escreve a casca da saida, Ã© aqui que vocÃª define onde o que sera enviado vai ficar
                ObjectOutputStream sai = new ObjectOutputStream(cliente.getOutputStream());
                ObjectOutputStream sai2 = new ObjectOutputStream(cliente.getOutputStream());
                // String que vai ser colocada na casca
                
                // Envio da string encapsulada
                String op = "deletar";
                
                sai.writeObject(numIdDel);
                sai2.writeObject(op);
                System.out.println("Enviou");
                // Limpa o object
                sai.flush();
                sai.close();
                }
       
            break;
        }           
    }
}
        
      
    

