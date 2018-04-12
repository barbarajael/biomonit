package models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by david-pc on 29-03-2017.
 */
public class AddMedidas {

    public void addMedida() {

        Medidas medida= new Medidas();
        medida.setAtributos("555555;654564");
        medida.setSensor_id("1");

        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("NewPersistenceUnit");
        EntityManager manager = factory.createEntityManager();

      /* manager.getTransaction().begin();
        manager.persist(medida);
        manager.getTransaction().commit();*/

        System.out.println("Nome da medida: " + medida.getAtributos());

        manager.close();
    }
}

