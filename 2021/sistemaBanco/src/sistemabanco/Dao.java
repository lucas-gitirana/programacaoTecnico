/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lucasGitirana
 */
public class Dao {
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaBancoPU");
    protected EntityManager em = emf.createEntityManager();
    
    public void fechar(){
        em.close();
        emf.close();
    }
}
