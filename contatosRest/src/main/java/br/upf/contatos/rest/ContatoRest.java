package br.upf.contatos.rest;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("contato")
public class ContatoRest {
    
    private ContatoService service = new ContatoService();
    
    /**
     * Consulta: retorna os dados de um contato. Caso o contato não exista no BD, retornar essa informação
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(new GenericEntity<List<Contato>>(service.getAll()){}).build();
    }
    
    /**
     * Atributo pode ser um código ou a cidade, retornando a lista, de acordo com o atributo
     * Consulta: retorna os dados de um contato. Caso o contato não exista no BD, retornar essa informação
     * ListaCidade: deve ser recebida a cidade e retornar uma lista contendo o código, nome e e-mail de todos os contatos no BD de uma determinada cidade
     * @param atributo
     * @return 
     */
    @GET
    @Path("{atributo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getByAtributo(@PathParam("atributo") String atributo) {
        try{
            Integer codigo = Integer.parseInt(atributo);
            return Response.ok(new GenericEntity<Contato>(service.getById(codigo)){}).build();
        }catch(NumberFormatException nfe){
            return Response.ok(new GenericEntity<List<Contato>>(service.getByCidade(atributo)){}).build();
        }
    }
    
    /**
     * Adiciona: adiciona contatos ao BD. Caso código do contato ou e-mail já exista, não adicionar e informar um erro
     * @param c
     * @return 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response insert(Contato c) {
        if(service.getById(c.getId()) == null && service.getByEmail(c.getEmail()).isEmpty()){
            service.add(c);
        }
        return Response.ok(new GenericEntity<List<Contato>>(service.getAll()){}).build();
    }
    
    /**
     * Altera: altera contatos no BD. Deve-se informar o contato, o dado a ser alterado e seu novo valor. Caso o código do contato não exista, retornar essa informação
     * Ainda tem um problema, em vez de atualizar, está duplicando o contato no banco de dados
     * @param c
     * @return 
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(Contato c) {
        service.update(c);
        return Response.ok(new GenericEntity<List<Contato>>(service.getAll()){}).build();
    }
    
    /**
     * Excluir: apaga um contato no BD. Deve-se informar o código do contato. Caso o contato não exista no BD, retornar essa informação
     * @param id
     * @return 
     */
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.ok(new GenericEntity<List<Contato>>(service.getAll()){}).build();
    }
    
    /**
     * Excluir: apaga um contato no BD. Deve-se informar o código do contato. Caso o contato não exista no BD, retornar essa informação
     * Esse código não funciona. Pesquisei, e acho que o DELETE não aceita nenhum tipo de entrada no corpo da mensagem, apenas saída
     * @param c
     * @return 
     *
     */ 
      @DELETE
      @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
      @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
      public Response delete(Contato c) {
          service.delete(c);
          return Response.ok(new GenericEntity<List<Contato>>(service.getAll()){}).build();
      }
    
}
