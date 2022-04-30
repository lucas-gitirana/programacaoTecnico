
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucasgitirana
 */
public class Dao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaShoppingPU");
    EntityManager em = emf.createEntityManager();
}
