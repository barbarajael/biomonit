package models;

import com.avaje.ebean.Model;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Entity
@Table(name = "Medidas")
public class Medidas {
    private String atributos;
    private String sensor_id;


    public static Model.Finder<Integer, Medidas> find = new Model.Finder<Integer,Medidas>(Integer.class, Medidas.class);

    @Id
    @Column(name = "valores")
    public String getAtributos() {
        return atributos;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
    }

    public static String getMedidasJson(String idsensor) {
        List<Medidas> medidas;
        medidas = find.where().eq("sensor_id",idsensor).findList();
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Medidas x : medidas) {

            String[] atrib = x.atributos.split(";");
            Medidasparse m = new Medidasparse();
            m.setSensor_id(x.getSensor_id());
            m.setTimestamp(atrib[0].toString());
            m.setTopic(atrib[1].toString());
            m.setValor(atrib[2].toString());
            m.setLatitude(atrib[4].toString());
            m.setLongitude(atrib[5].toString());

            JsonNode personJson = Json.toJson(m);

            sb.append(personJson.toString()+","+"\n");
        }
        sb.deleteCharAt(sb.length()-2);
        sb.append("]");

        return sb.toString();
    }

    public static void getMedidasXml(String idsensor) throws JAXBException {
        GroupMedidasParse g = new GroupMedidasParse();
        List<Medidas> medidas;
        List<Medidasparse> m1 = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        JAXBContext jaxbContext = JAXBContext.newInstance(GroupMedidasParse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        String origin = System.getProperty("user.home");
        File file = new File(origin + "/xml");

        medidas = find.where().eq("sensor_id",idsensor).findList();

        for (Medidas x : medidas) {

            String[] atrib = x.atributos.split(";");

            Medidasparse m = new Medidasparse();
            m.setSensor_id(x.getSensor_id());
            m.setTimestamp(atrib[0].toString());
            m.setTopic(atrib[1].toString());
            m.setValor(atrib[2].toString());
            m.setLatitude(atrib[4].toString());
            m.setLongitude(atrib[5].toString());

            m1.add(m);
        }

        g.setMedidas(m1);
        jaxbMarshaller.marshal(g, file);
    }

    public static List getLocalFromSensors(){
        List<Medidas> medidas;
        medidas = find.findList();
        List<String> local = new ArrayList<>();
        boolean equal;


        for (Medidas x : medidas) {
            equal = false;
            String[] atrib = x.atributos.split(";");
            List<Medidas> m1;
            //System.out.print(Arrays.toString(atrib));

            if(local.size()==0){
                local.add(atrib[4]);
                local.add(atrib[5]);
                //local.add(atrib[3]);

                m1=find.where().eq("sensor_id",atrib[3]).findList();

                String[] atriblast = m1.get(m1.size()-1).atributos.split(";");
                if(atriblast[2].equals("0")) // Off
                    local.add("0");
                else
                    local.add("1"); //sensor ligado
            }

            else {
                for (int i = 0; i < local.size(); i++) {

                    if (atrib[4].equals(local.get(i)) && atrib[5].equals(local.get(i + 1))){
                        equal=true;
                        break;
                    }
                    i+=2;

                }
                if(equal==false) {
                    local.add(atrib[4]);
                    local.add(atrib[5]);
                    m1=find.where().eq("sensor_id",atrib[3]).findList();

                    String[] atriblast = m1.get(m1.size()-1).atributos.split(";");
                    //local.add(atrib[3]);
                    if(atriblast[2].equals("0")) // Off
                        local.add("0");
                    else
                        local.add("1"); //sensor ligado
                }
            }
        }
        System.out.print(local.toString());

        return local;
    }


    public static List getMedidas(String idsensor) {
        List<Medidas> medidas;
        medidas = find.where().eq("sensor_id",idsensor).findList();

        List<String> mList = new ArrayList<>();

        for (Medidas x : medidas) {

            String[] atrib = x.atributos.split(";");
            HistoryMedidasParse m = new HistoryMedidasParse();
            m.setValor(atrib[2].toString().replaceAll("[a-zA-Z|%]+", ""));

            mList.add(m.getValor());
        }

        return mList;
    }

    public static List getMedidasWithoutZeros(String idsensor) {
        List<Medidas> medidas;
        medidas = find.where().eq("sensor_id",idsensor).findList();

        List<String> mList = new ArrayList<>();
        List<String> mListt = new ArrayList<>();

        for (Medidas x : medidas) {

            String[] atrib = x.atributos.split(";");
            HistoryMedidasParse m = new HistoryMedidasParse();
            m.setValor(atrib[2].toString().replaceAll("[a-zA-Z|%]+", ""));

            mList.add(m.getValor());
        }

        for (int i = 0; i<mList.size(); i++) {
            if(!(mList.get(i).equals("0"))) {
                mListt.add(mList.get(i));
            }
        }

        return mListt;
    }

    public static List getTimestamp(String idsensor) {
        List<Medidas> medidas;
        medidas = find.where().eq("sensor_id",idsensor).findList();

        List<String> mList = new ArrayList<>();

        for (Medidas x : medidas) {

            String[] atrib = x.atributos.split(";");
            TimestampParse m = new TimestampParse();
            m.setTimestamp(atrib[0].toString().substring(0, 19));

            // timestamp to milliseconds (long)
            String myDate = m.getTime().replace("T", " ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(myDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long timeInMillis = date.getTime();

            // js (on the other side) can't handle long (too big)
            // cast to string
            String strLong = Long.toString(timeInMillis);

            mList.add(strLong);
        }

        return mList;
    }

    public static List removeTimestampZeros(List<String> medidas, List<String> timestamp) {

        Map<String, String> map = new HashMap<>();
        List<String> mList = new ArrayList<>();

        for (int i = 0; i < medidas.size(); i++) {
            if(!(medidas.get(i).equals("0")))
                mList.add(timestamp.get(i));
        }

        return mList;
    }

    public static List getTimestampWithoutZeros(String idsensor) {
        List<String> medidasList = new ArrayList<>();
        List<String> timestampList = new ArrayList<>();
        List<String> list = new ArrayList<>();

        medidasList = getMedidas(idsensor);
        timestampList = getTimestamp(idsensor);
        list = removeTimestampZeros(medidasList, timestampList);

        return list;
    }


    @Basic
    @Column(name = "sensor_id")
    public String getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(String sensor_id) {
        this.sensor_id = sensor_id;
    }

    @Override
    public String toString() {
        return "Medidas{" +
                "atributos='" + atributos + '\'' +
                ", sensor_id='" + sensor_id + '\'' +
                '}';
    }
}
