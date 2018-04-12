package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by david on 03-05-2017.
 */
@Entity
public class gen_user extends Model {
    @Id
    public String utilizador;
    public String nome;
    public String pass;
    public String passconf;


    public String getEmail() {
        return utilizador;
    }


}
