/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

/**
 *
 * @author lucasGitirana
 */
public class DaoBanco extends Dao{
    public boolean inserir(Banco b){
        try{
            em.getTransaction().begin();
            em.persist(b);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public boolean editar(Banco b){
           try{
            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public Banco selecionar(int numero){
        try{
            return (Banco) em.createQuery("select b from Banco b where b.numero = :numero").setParameter("numero", numero).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
    public List<Banco> listar(){
        try{
            return em.createQuery("select b from Banco b").getResultList();
        }catch(NoResultException e){
            return null;
        }        
    }
}
