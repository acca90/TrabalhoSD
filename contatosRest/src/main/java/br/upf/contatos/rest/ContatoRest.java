package br.upf.contatos.rest;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.util.ArrayList;
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
        Retorno resposta = new Retorno();
        resposta.setErro("");
        resposta.setContatos(service.getAll());
        if(resposta.getContatos().size() < 1)
            resposta.setErro("Nenhum contato encontrado!");
        return Response.ok(new GenericEntity<Retorno>(resposta){}).build();
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
        Retorno resposta = new Retorno();
        try{
            Integer codigo = Integer.parseInt(atributo);
            List<Contato> selecionado = new ArrayList<>();
            Contato contato = service.getById(codigo);
            if(contato == null)
                resposta.setErro("Nenhum contato encontrado com esse código!");
            else
                selecionado.add(contato);
            resposta.setContatos(selecionado);
            return Response.ok(new GenericEntity<Retorno>(resposta){}).build();
        }catch(NumberFormatException nfe){
            resposta.setErro("");
            resposta.setContatos(service.getByCidade(atributo));
            if(resposta.getContatos().size() < 1)
                resposta.setErro("Nenhum contato encontrado nesta cidade!");
            return Response.ok(new GenericEntity<Retorno>(resposta){}).build();
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
        Retorno resposta = new Retorno();
        if(service.getById(c.getId()) == null && service.getByEmail(c.getEmail()).isEmpty()){
            Contato contato = service.add(c);
            if(contato.equals(c)){
                resposta.setErro("");
            }
            else
            {
                resposta.setErro("Erro ao inserir contato!");
            }
        }else
        {
            resposta.setErro("Já existe um contato registrado com mesmo código ou email!");
        }
        resposta.setContatos(service.getAll());
        return Response.ok(new GenericEntity<Retorno>(resposta){}).build();
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
        Retorno resposta = new Retorno();
        Contato contato = service.update(c);
        if(contato.equals(c))
        {
            resposta.setErro("");
        }
        else
        {
            resposta.setErro("Erro ao atualizar contato!");
        }
        resposta.setContatos(service.getAll());
        return Response.ok(new GenericEntity<Retorno>(resposta){}).build();
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
        Retorno resposta = new Retorno();
        Contato contato = service.delete(id);
        if(contato == null){
            resposta.setErro("Erro ao excluir contato!");
        }
        else{
            resposta.setErro("");
        }
        resposta.setContatos(service.getAll());
        return Response.ok(new GenericEntity<Retorno>(resposta){}).build();
    }
}
