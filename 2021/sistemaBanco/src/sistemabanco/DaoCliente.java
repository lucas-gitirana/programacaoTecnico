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
public class DaoCliente extends Dao{
    public boolean inserir(Cliente c){
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
    
    public Cliente selecionar(String cpf){
        try{
            return (Cliente) em.createQuery("select c from Cliente c where c.cpf = :cpf").setParameter("cpf", cpf).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
