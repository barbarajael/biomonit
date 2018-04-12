package models;

import javax.persistence.*;

/**
 * Created by david-pc on 01-04-2017.
 */
@Entity
@Table(name ="Entidade")
public class Entidade {
    @Id
    private String id;
    private String contexto_id;
    private String nome;


    @Column(name = "id")
    public String getEntidadeId() {
        return id;
    }

    public void setEntidadeId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name= "contexto_id")
    public String getContextoId(){
        return contexto_id;
    }
    public void setContextoId(String contexto_id){
        this.contexto_id=contexto_id;
    }

    @Basic
    @Column(name="nome")
    public String getEntidadeNome(){
        return nome;
    }
    public void setEntidadeNome(String nome){
        this.nome = nome;
    }
}
