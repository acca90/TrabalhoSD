
import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matheus Sobucki
 */
public class ListarDados {
    public static void main(String[] args) {
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
    }
    
}
