
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "shoppings")
public class Shopping implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopping")   
    private List<Loja> lojas = new ArrayList<>();

    public Shopping(){
        id = 0;
        nome = "";
        endereco = "";        
    }
    public Shopping(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }
    
    public void addLoja(String nome, String site, String descricao, int numeroSala, Segmento segmento){
        Loja l = new Loja(nome,site,descricao,numeroSala,segmento);
        lojas.add(l);
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Loja> getLojas() {
        return lojas;
    }    

    @Override
    public String toString() {
        return "Shopping{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + '}';
    }
    
    
    
    
    
}
