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

    public List<T> getList(String atributoOrdem)
            throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();
        String hql = "select t from " + nomeClasse + " t";

        try {
            hql += setOrderBy(atributoOrdem);
            List<T> lista = em.createQuery(hql).getResultList();
            return lista;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    public List<T> getList(String atributoOrdem,
            String atributoFiltro, String valorFiltro) throws Exception {
        EntityManager em = FabricaConexao.getEntityManager();
        String hql = "select t from " + nomeClasse + " t";

        try {
            hql += setFilter(atributoFiltro, valorFiltro);
            hql += setOrderBy(atributoOrdem);
            List<T> lista = em.createQuery(hql).getResultList();
            return lista;
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
        }
    }

    private String setFilter(String strAtributo, String strValor) {
        String r = "";
        if (strAtributo != null && !strAtributo.trim().isEmpty()
                && strValor != null && !strValor.trim().isEmpty()) {
            r = " where upper(t." + strAtributo + ") like '%" + strValor.toUpperCase() + "%'";
        }
        return r;
    }

    private String setOrderBy(String strOrdem) {
        String r = "";
        if (strOrdem != null && !strOrdem.trim().isEmpty()) {
            r = " order by t." + strOrdem;
        }
        return r;
    }
}
