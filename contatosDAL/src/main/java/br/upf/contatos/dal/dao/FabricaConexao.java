package br.upf.contatos.dal.dao;

/**
 *
 * @author Mauricley
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {
    public static EntityManagerFactory fabrica;
    
    public static EntityManager getEntityManager() {
        if (fabrica == null)
            fabrica = Persistence.createEntityManagerFactory("contatos");
        return fabrica.createEntityManager();
    }
}
