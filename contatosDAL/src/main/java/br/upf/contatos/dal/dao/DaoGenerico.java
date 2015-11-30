package br.upf.contatos.dal.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Mauricley
 * @param <T> Classe que o DaoGenerico vai atender
 */
public class DaoGenerico<T> implements Dao<T> {

    private final Class classe;
    private final String nomeClasse;

    public DaoGenerico(Class classe) {
        super();
        this.classe = classe;
        this.nomeClasse = classe.getSimpleName();
    }

    @Override
    public void persist(T instancia) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(instancia);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public T merge(T instancia) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();

        try {
            em.getTransaction().begin();
            instancia = em.merge(instancia);
            em.getTransaction().commit();
            return instancia;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(T instancia) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(em.merge(instancia));
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(Integer id) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(em.find(classe, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public T getById(Integer id) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();

        try {
            return (T) em.find(classe, id);
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> getList() throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();

        String query = "select t from " + nomeClasse + " t";

        try {
            List<T> lista = em.createQuery(query).getResultList();
            return lista;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }
}
