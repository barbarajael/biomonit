package models;

/**
 * Created by gil on 03-05-2017.
 */

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GroupMedidasParse {
    @XmlElement
    private List<Medidasparse> medidasparse = null;

    public List<Medidasparse> getMedidas() {
        return medidasparse;
    }

    public void setMedidas(List<Medidasparse> m) {
        this.medidasparse = m;
    }
}
