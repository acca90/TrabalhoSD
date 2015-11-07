import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.List;
import java.util.Scanner;

public class teste2 {
    
    public static void main(String args[]) { 
        int cod = 0;  
        int id = 0; 
        String nome;
        String email;
        String end;
        String comp;
        String cep;
        String cid;
        String est;
        
        ContatoService service = new ContatoService();
        //ContatoService service = new ContatoService();
        Scanner sler = new Scanner (System.in); 
        
            pintaMenu();
            cod = sler.nextInt();
            
            switch (cod){
                case 1:
                    System.out.println("1-------");
                    Contato novo = new Contato();
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
                    
                    novo.setNome(nome);
                    novo.setEmail(email);
                    novo.setEmail(end);
                    novo.setEmail(comp);
                    novo.setEmail(cep);
                    novo.setEmail(cid);
                    novo.setEmail(est);
                    
                    Contato outro = service.add(novo);
                     
                     System.out.println("Contato id:" + outro.getId());
                     System.out.println("Contato nome:" + outro.getNome());
                    break;
                
                case 2:
                    System.out.println("2--------");
                    break;
            
                case 3:
                    System.out.println("3------");
                    System.out.println("id do cadastro:");
                    id = sler.nextInt();
                    service.delete(id);
                    System.out.printf("cadastro excluido:"+ id);
                    break;
            
                case 4:
                    System.out.println("4 ----");
                    
                    List<Contato> contatos = service.getAll();
                    for(Contato c: contatos) {
                        System.out.println("Contato:" + c.getNome());
                    }
                    break;
            }
        }      
    
    
    private static void pintaMenu(){
        System.out.println("1 - Cadastrar");  
        System.out.println("2 - Alterar");  
        System.out.println("3 - Excluir");              
        System.out.println("4 - Consultar");
        System.out.println("5 - Listar Cidade");
        System.out.println("6 - Sair");
    }

}
    



