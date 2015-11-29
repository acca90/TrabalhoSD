import java.util.Scanner;
import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.List;

public class ClienteGenerico {
   
public static void main(String[] args) {
    while (true) {
        System.out.println("Digite help, para o menu");
        Scanner scanner = new Scanner(System.in);   
        String line = scanner.nextLine();
        comando(line);
   }   
}
       
public static boolean comando(String line) { 
    String nome;
    String email;
    String end;
    String comp;
    String cep;
    String cid;
    String est;
    String alt;
        
    if (line.equals("help")) {    
        System.out.println("op = [ listar, incluir, editar, deletar, parar ], label = valor");
        System.out.println("Para sair digite: parar");           
        return true;
    } else if (line.equals("parar")) {       
        return false;    
    } else {  
        System.out.println("----------------------------------------------------------------------------------");    
            /* O COMANDO SPLIT QUEBRA A STRING */
        String[] chamadas = line.split(",");       
        String[] op = chamadas[0].split("=");    
            
        switch(op[1]) {        
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
                Scanner sler = new Scanner (System.in); 
                System.out.println("Nome:");
                nome = sler.next();
                System.out.println("Email:");
                email = sler.next();
                System.out.println("Endereco:");
                end = sler.next();
                System.out.println("Complemento:");
                comp = sler.next();
                System.out.println("CEP:");
                cep = sler.next();
                System.out.println("Cidade:");
                cid = sler.next();
                System.out.println("Estado");
                est = sler.next();
                System.out.println("Email Alternativo:");
                alt = sler.next();
                
                ContatoService service2 = new ContatoService();
                Contato novo = new Contato();
                novo.setNome (nome);
                novo.setEmail(email);
                Contato outro = service2.add(novo);
               System.out.println("Contato nome:" + outro.getNome());
            break;
                
            case "editar":
                System.out.println("2");
            break;
                
            case "deletar":        
                System.out.println("3"); 
                
            break;
        }           
    return true;       
    }    
}
}
