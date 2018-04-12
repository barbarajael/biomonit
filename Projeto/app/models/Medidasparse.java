package models;

/**
 * Created by gil on 01-05-2017.
 */

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Medidasparse {

    private String sensor_id;
    private String timestamp;
    private String topic;
    private String valor;
    private String latitude;
    private String longitude;


    public String getSensor_id() {
        return sensor_id;
    }

    @XmlAttribute
    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @XmlElement
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;
    }

    @XmlElement
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getValor() {
        return valor;
    }

    @XmlElement
    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLatitude() {
        return latitude;
    }

    @XmlElement
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @XmlElement
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Medidasparse{" +
                "sensor_id='" + sensor_id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", topic='" + topic + '\'' +
                ", valor='" + valor + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}