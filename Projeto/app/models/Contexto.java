package models;

import javax.persistence.*;

/**
 * Created by david-pc on 01-04-2017.
 */
@Entity
@Table(name ="Contexto")
public class Contexto {
    @Id
    private String id;
    private String nome;
    private String grupo;
    private String sensor_id;


    @Column(name = "id")
    public String getContextoId() {
        return id;
    }

    public void setContextoId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sensor_id")
    public String getContextoSensor_id() {
        return sensor_id;
    }

    public void setContextoSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    @Basic
    @Column(name = "nome")
    public String getContextoNome() {
        return nome;
    }

    public void setContextoNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name="grupo")
    public String getContextoGrupo(){
        return grupo;
    }
    public void setContextoGrupo(String grupo) {
        this.grupo = grupo;
    }

}
