/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author lucasGitirana
 */
@Entity
@DiscriminatorValue("C")
public class ContaCorrente extends Conta{
    
    private double limite;

    public ContaCorrente() {
    }

    
    public ContaCorrente(double limite, int numero, double saldo, Banco banco, Cliente cliente) {
        super(numero, saldo, banco, cliente);
        this.limite = limite;
    }


    @Override
    public double getSaldoDisponivel() {
        return saldo + limite;
    }

    @Override
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return super.toString()+ "ContaCorrente{" + "limite=" + limite + '}';
    }
    
     

    
    
}
