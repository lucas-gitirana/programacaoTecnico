
import java.util.List;
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
public class DaoShopping extends Dao{
    public boolean inserir(Shopping s){
        try{
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException exc){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    public boolean editar(Shopping s){
        try{
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            return true;
        }catch(PersistenceException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    public Shopping selecionar(String nome){
        try{
           return (Shopping) em.createQuery("select s from Shopping s where s.nome = :nome")
                .setParameter("nome", nome).getSingleResult();
        }catch(NoResultException e){
            return null;
        }        
    }
    public List<Shopping> listar(){
        try{
            return em.createQuery("select s from Shopping s").getResultList();
        }catch(NoResultException e){
            return null;
        }        
    }    
    public void fechar(){
        em.close();
        emf.close();
    }
}
