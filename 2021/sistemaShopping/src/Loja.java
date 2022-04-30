
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "lojas")
public class Loja implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String site;
    private String descricao;
    @Column(name = "numero_sala")
    private int numeroSala;  
    @ManyToOne
    @JoinColumn(name = "id_segmento")
    private Segmento segmento;   
    @ManyToOne
    @JoinColumn(name = "id_shopping")
    private Shopping shopping;
    
    public Loja(){
        id = 0;
        nome = ""              ;
        site = "";
        descricao = "";
        numeroSala = 0;
        segmento = null;
    }

    public Loja(String nome, String site, String descricao, int numeroSala, Segmento segmento) {
        this.nome = nome;
        this.site = site;
        this.descricao = descricao;
        this.numeroSala = numeroSala;
        this.segmento = segmento;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }   

    @Override
    public String toString() {
        return "Loja{" + "id=" + id + ", nome=" + nome + ", site=" + site + ", descricao=" + descricao + ", numeroSala=" + numeroSala + '}';
    }
    
    
    
    
}
