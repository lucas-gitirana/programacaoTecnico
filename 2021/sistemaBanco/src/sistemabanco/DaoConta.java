/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

/**
 *
 * @author lucasGitirana
 */
public class DaoConta extends Dao{
    public boolean inserir(Conta c){
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
    public boolean editar(Conta c){
        try{
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    } 
   
}
