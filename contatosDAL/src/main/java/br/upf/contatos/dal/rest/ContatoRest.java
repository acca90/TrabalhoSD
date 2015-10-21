package br.upf.contatos.dal.rest;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.dal.service.ContatoService;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Mauricley
 */

@Path("contatos")
public class ContatoRest implements Serializable {
    
    @Inject
    private ContatoService service;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Contato> getAll() {
        return service.getAll();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contato getById(@PathParam("id") Integer id) {
        return service.getById(id);
    }
    
    @GET
    @Path("c-{cidade}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Contato> getById(@PathParam("cidade") String cidade) {
        return service.getByCidade(cidade);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contato insert(Contato c) {
        return service.add(c);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contato update(Contato c) {
        return service.update(c);
    }
    
    @DELETE
    @Path("{id}")
    public Contato delete(@PathParam("id") Integer id) {
        return service.delete(id);
    }
}
