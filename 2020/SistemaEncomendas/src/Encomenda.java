
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author letic
 */

@Entity
@Table (name = "encomendas")
public class Encomenda implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private String cliente;
    private int status;
    private double peso;
    private double volume;
    private double valor;
    
    public Encomenda(){        
        cliente = "";
        status = 0;
        peso = 0;
        volume = 0;
        valor = 0;       
    }
    
    public Encomenda(String cliente, int status, double peso, double volume, 
            double valor){
        this.cliente = cliente;
        this.status = status;
        this.peso = peso;
        this.volume = volume;
        this.valor = valor;        
    }
    
    public int getId(){
        return id;
    }
    
    public void setCliente(String cliente){
        this.cliente = cliente;
    }
    
    public String getCliente(){
        return cliente;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
    public int getStatus(){
       return status;
    }
    
     public void setPeso(double peso){
        this.peso = peso;
    }
    
    public double getPeso(){
        return peso;
    }
    
     public void setVolume(double volume){
        this.volume = volume;
    }
    
    public double getVolume(){
        return volume;
    }
    
     public void setValor(double valor){
        this.valor = valor;
    }
    
    public double getValor(){
        return valor;
    }
    
    @Override
    public String toString(){
        return "Encomenda: id = "+id+" / cliente = "+cliente+" / status = "+status+
                " / peso = "+peso+" / volume = "+volume+" / valor = "+valor;
    }
    
    public double calcularValorFrete(){
        
        if (valor > 2000){
            return 0;
        
        }else{
            return (0.05 * valor) + (volume * 5) + (peso * 2);
            
        }        
    }
    
    
    
    
    
}
