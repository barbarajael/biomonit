package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.SqlUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by david-pc on 01-04-2017.
 */
@Entity
@Table(name = "Sensor")
public class Sensor {
@Id
public String id;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public static Sensor newSensor(String id) {
        SqlUpdate insert = Ebean.createSqlUpdate("INSERT INTO Sensor(id) VALUES ('"+id+"')");
        insert.execute();
        return null;

    }
    public static Sensor deleteSensor(String id) {
        SqlUpdate delete = Ebean.createSqlUpdate("DELETE FROM Sensor Where id='"+id+"'");
        delete.execute();
        return null;

    }

    public static List<Sensor> listofsensor() {

        List<Sensor> sensor = find.findList();
        return sensor;


    }
    public static Model.Finder<String, Sensor> find = new Model.Finder<String,Sensor>(String.class, Sensor.class);
}