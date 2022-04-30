/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author lucasGitirana
 */
@Entity
@Table(name = "contas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
public abstract class Conta implements Serializable{
    @Id
    @GeneratedValue
    protected int id;
    protected int numero;
    protected double saldo;
    @ManyToOne
    @JoinColumn(name = "id_banco")
    protected Banco banco;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    protected Cliente cliente;

    public Conta() {
        id = 0;
        numero = 0;
        saldo = 0;
    }

    public Conta(int numero, double saldo, Banco banco, Cliente cliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.banco = banco;
        this.cliente = cliente;
    }
    
    public abstract double getSaldoDisponivel();
    
    public abstract double getLimite();
    
    public void sacar(double valor){
        if(valor <= saldo){
            saldo -= valor;
        }
    }
    
    public void depositar(double valor){
        saldo += valor;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Conta{" + "id=" + id + ", numero=" + numero + ", saldo=" + saldo + ", cliente= "+cliente.getNome()+ '}';
    }
    
    
    
}
