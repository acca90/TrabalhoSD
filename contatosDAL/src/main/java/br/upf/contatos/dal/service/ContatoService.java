package br.upf.contatos.dal.service;

import br.upf.contatos.dal.dao.DaoGenerico;
import br.upf.contatos.dal.model.Contato;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricley
 */

public class ContatoService {
    
    private final DaoGenerico<Contato> dao;
     
    public ContatoService() {
        this.dao = new DaoGenerico<>(Contato.class);
    }
    
    public List<Contato> getAll() {
        try {
            return dao.getList();
        } catch (Exception ex) {
            Logger.getLogger(ContatoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Contato getById(Integer id) {
        try {
            return dao.getById(id);
        } catch (Exception ex) {
            Logger.getLogger(ContatoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Contato> getByCidade(String cidade) {
        try {
            return dao.getList("id", "cidade", cidade);
        } catch (Exception ex) {
            Logger.getLogger(ContatoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Contato add(Contato c) {
        try {
            dao.persist(c);
            return c;
        } catch (Exception ex) {
            Logger.getLogger(ContatoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Contato update(Contato c) {
        try {
            c = dao.merge(c);
            return c;
        } catch (Exception ex) {
            Logger.getLogger(ContatoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Contato delete(Integer id) {
        try {
            return delete(dao.getById(id));
        } catch (Exception ex) {
            Logger.getLogger(ContatoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Contato delete(Contato c) {
        try {
            dao.remove(c);
            return c;
        } catch (Exception ex) {
            Logger.getLogger(ContatoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
