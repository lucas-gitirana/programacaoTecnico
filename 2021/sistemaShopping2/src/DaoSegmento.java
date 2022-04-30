
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
public class DaoSegmento extends Dao {
    public boolean inserir(Segmento s){
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
    public Segmento selecionar(String nome){
        try{
            return (Segmento) em.createQuery("select s from Segmento s where s.nome = :nome")
                .setParameter("nome", nome).getSingleResult();
        }catch(NoResultException e){
            return null;
        }        
    }
    public void fechar(){
        em.close();
        emf.close();
    }
    
}
