
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author letic
 */
public class DaoEncomenda {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaEncomendasPU");
    EntityManager em = emf.createEntityManager();
    
    public Encomenda selecionar(int id){
        try{
            Query consulta = em.createQuery("select e from Encomenda e where e.id = :num");
            consulta.setParameter("num", id);
            return (Encomenda) consulta.getSingleResult();
        }catch(NoResultException exc){
            return null;
        }
    }
    
    public boolean inserir(Encomenda e){
        
       try{
           em.getTransaction().begin();
           em.persist(e);
           em.getTransaction().commit(); 
           return true;
       }catch(PersistenceException exc){
           if (em.getTransaction().isActive()) {
               em.getTransaction().rollback();
           }           
           return false;
       } 
    }
    
    public boolean editar(Encomenda e){
        
        try{
            em.getTransaction().begin();
            em.merge(e);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException exc){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public List<Encomenda> listar(){
        try{
            Query consulta = em.createQuery("select e from Encomenda e");
            return consulta.getResultList();
        }catch(NoResultException exc){
            return null;
        }
    }
    
    public void fechar(){
        em.close();
        emf.close();
    }
    
    
}
