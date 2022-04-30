/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabanco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author lucasGitirana
 */
@Entity
@Table(name = "bancos")
public class Banco implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private int numero;
    private String endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private List<Conta> contas = new ArrayList<>();

    public Banco() {
        numero = 0;
        endereco = "";
    }

    public Banco(int numero, String endereco) {
        this.numero = numero;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
    
    public void adicionarContaCorrente(double limite, int numero, double saldo, Banco banco, Cliente cliente){
        Conta c = new ContaCorrente(limite, numero, saldo, banco, cliente);
        contas.add(c);
    }
    
    public void adicionarContaPoupanca(int variacao, int numero, double saldo, Banco banco, Cliente cliente){
        Conta c = new ContaPoupanca(variacao, numero, saldo, banco, cliente);
        contas.add(c);
    }

    @Override
    public String toString() {
        return "Banco{" + "id=" + id + ", numero=" + numero + ", endereco=" + endereco + '}';
    }
    
    
    
    
    
    
}
