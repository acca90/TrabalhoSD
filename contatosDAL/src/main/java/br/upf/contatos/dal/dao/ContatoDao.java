/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.dal.dao;

import br.upf.contatos.dal.model.Contato;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Mauricley
 */
public class ContatoDao extends DaoGenerico<Contato> {
    public ContatoDao() {
        super(Contato.class);
    }
    
    @Override
    public List<Contato> getList() throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();
        String query = "select c from Contato c order by c.id";
        try {
            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
    
    public List<Contato> getByCidade(String cidade) throws Exception {
        return listByCol("cidade", cidade);
    }
        
    public List<Contato> getByEmail(String email) throws Exception {
        return listByCol("email", email);
    }
    
    public boolean exists(Contato c) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();
        try {
            return (c.getId() != null && em.find(Contato.class, c.getId()) != null);
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
    
    private List<Contato> listByCol(String nomeAtr, String valorAtr) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();
        String query = "select c from Contato c where upper(c." + nomeAtr + ")"
                + "like :" + nomeAtr + " order by c.id";
        try {
            return em.createQuery(query).
                    setParameter(nomeAtr, "%"+valorAtr.toUpperCase()+"%").
                    getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
}
