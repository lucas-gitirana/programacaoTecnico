
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lucasgitirana
 */
public class DaoLoja extends Dao {

    public boolean editar(Loja l) {
        try {
            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Loja l) {
        try {
            em.getTransaction().begin();
            em.remove(em.find(Loja.class, l.getId()));
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public void fechar(){
        em.close();
        emf.close();
    }
        
}
