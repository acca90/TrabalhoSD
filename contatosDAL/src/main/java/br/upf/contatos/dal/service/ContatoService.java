package br.upf.contatos.dal.service;

import br.upf.contatos.dal.dao.ContatoDao;
import br.upf.contatos.dal.model.Contato;
import java.util.List;
import javax.persistence.RollbackException;

/**
 *
 * @author Mauricley
 */

public class ContatoService {
    private final ContatoDao dao = new ContatoDao();
    
    public List<Contato> getAll() throws RuntimeException {
        try {
            return dao.getList();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um problema ao tentar buscar os contatos!");
        }
    }
    
    public Contato getById(Integer id) {
        try {
            return dao.getById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema ao buscar o contato #"+id+"!");
        }
    }
    
    public List<Contato> getByCidade(String cidade) {
        try {
            return dao.getByCidade(cidade);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema ao tentar buscar os contatos da cidade "+cidade+"!");
        }
    }
    
    public List<Contato> getByEmail(String email) throws RuntimeException {
        try {
            return dao.getByEmail(email);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema ao tentar buscar os contatos pelo email "+email+"!");
        }
    }
    
    public Contato add(Contato c) throws RuntimeException {
        boolean existe;
        try {
             existe = dao.exists(c);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema ao inserir o contato #"+c.getId()+"!");
        }
        if (existe)
            throw new RuntimeException("Já existe um contato com o código "+c.getId()+"!");
        try {
            dao.persist(c);
            return c;
        } catch (RollbackException ex) {
            throw new RuntimeException("O email informado ("+c.getEmail()+") já está sendo usado!");
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema ao tentar inserir o contato #"+c.getId()+"!");
        }
    }
    
    public Contato update(Contato c) throws RuntimeException {
        boolean existe;
        try {
             existe = dao.exists(c);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema ao atualizar o contato #"+c.getId()+"!");
        }
        if (!existe)
            throw new RuntimeException("Não existe contato com o código "+c.getId()+"!");
        try {
            c = dao.merge(c);
            return c;
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema ao atualizar o contato #"+c.getId()+"!");
        }
    }
    
    public Contato delete(Integer id) throws RuntimeException {
        Contato c;
        try {
             c = dao.getById(id);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema excluir o contato #"+id+"!");
        }
        
        if (c == null)
            throw new RuntimeException("O contato #"+id+" não existe!");
        
        return delete(c);
    }
    
    public Contato delete(Contato c) throws RuntimeException {
        boolean existe;
        try {
             existe = dao.exists(c);
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema excluir o contato #"+c.getId()+"!");
        }
        if (!existe)
            throw new RuntimeException("O contato #"+c.getId()+" não existe!");
        try {
            dao.remove(c);
            return c;
        } catch (Exception ex) {
            throw new RuntimeException("Ocorreu um problema excluir o contato #"+c.getId()+"!");
        }
    }
}
