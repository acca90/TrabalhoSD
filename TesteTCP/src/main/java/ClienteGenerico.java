
import java.util.Scanner;
import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import com.sun.corba.se.impl.orbutil.ObjectWriter;
import java.util.List;

public class ClienteGenerico {
   
public static void main(String[] args) {
    while (true) {
       if (comando()) {

       } else {

       break;

       }

   }
        
 }
       
public static boolean comando() {    
    
    System.out.println("Digite help, para o menu");
    Scanner scanner = new Scanner(System.in);   
    String line = scanner.nextLine();
        
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
               // ContatoService service = new ContatoService();
              //  List<Contato> contatos = service.getAll();
               // for(Contato c: contatos) {
                   // System.out.println("Contato:" + c.getNome());
               // }      
            break;
              
            case "incluir":   
                System.out.println("Digite conforme o exemplo abaixo:");                    
                System.out.println("nome = Maria, email = maria@upf.br , endereco = rua dos bobos, "
                        + "complemento = casa, cep = 9910000, cidade = Passo Fundo, estado = RS");
                Scanner add = new Scanner(System.in);   
                String lineadd = add.nextLine();
                
                String[] campo = lineadd.split(",");       
                String[] opi = campo[0].split("="); 
                
                //ContatoService service = new ContatoService();
               // Contato novo = new Contato();
               // System.out.println(lineadd);
               // novo.setNome ("teste");
               // novo.setEmail(campo[2]);
               // Contato outro = service.add(novo);
              //  System.out.println("Contato nome:" + outro.getNome());
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

