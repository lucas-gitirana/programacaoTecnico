
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
 * @author lucasgitirana
 */
@Entity
@Table(name = "segmentos")
public class Segmento implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    
    public Segmento(){
        id = 0;
        nome = "";
    }
    public Segmento(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Segmento{" + "id=" + id + ", nome=" + nome + '}';
    }    
}
