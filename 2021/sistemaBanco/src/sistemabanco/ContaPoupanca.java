/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author lucasGitirana
 */
@Entity
@DiscriminatorValue("P")
public class ContaPoupanca extends Conta implements Serializable{
    
    private int variacao;

    public ContaPoupanca() {
    }


    public ContaPoupanca(int variacao, int numero, double saldo, Banco banco, Cliente cliente) {
        super(numero, saldo, banco, cliente);
        this.variacao = variacao;
    }

    @Override
    public double getSaldoDisponivel() {
        if(variacao == 2){
            return saldo * 0.98;
        }else if(variacao == 4){
            return saldo * 0.94;
        }else if (variacao == 6){
            return saldo * 1;
        }else{
            return 0;
        }
    }

    public int getVariacao() {
        return variacao;
    }

    public void setVariacao(int variacao) {
        this.variacao = variacao;
    }

    @Override
    public String toString() {
        return super.toString()+ "ContaPoupanca{" + "variacao=" + variacao + '}';
    }

    @Override
    public double getLimite() {
        return 0;
    }
    
    
    
}
