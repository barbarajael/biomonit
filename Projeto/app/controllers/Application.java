package controllers;


import models.Medidas;
import models.Sensor;
import models.gen_user;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.Pessoa;
import views.html.*;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static play.data.Form.form;

public class Application extends Controller {
    public static String user;

    public static Result index() {
        return ok(views.html.index.render("BioMonit"));
    }


    public static Result login() {
        return ok(
                login.render(form(Pessoa.class))
        );
    }


    public static Result getJson() {
        String origin = System.getProperty("user.home");

        try {
            PrintWriter out = new PrintWriter(origin + "/json");
            out.println(Medidas.getMedidasJson("00-00-00-00-00-00-00-E0-heartratePub"));
            out.close();
        } catch (FileNotFoundException e) {
            System.err.print("Ficheiro inexistente");
        }

        return ok(new java.io.File(origin + "/json"));
    }


    public static Result getXml() throws JAXBException, IOException, XMLStreamException {
        String origin = System.getProperty("user.home");
        Medidas.getMedidasXml("00-00-00-00-00-00-00-E0-heartratePub");
        return ok(new java.io.File(origin + "/xml"));
    }


    public static Result authenticate() {
        Form<Pessoa> loginForm = form(Pessoa.class).bindFromRequest();
        String utilizador = loginForm.get().utilizador;
        String pass = loginForm.get().pass;

        // TODO test only
        System.out.print(utilizador);
        System.out.print(pass);

        user = utilizador;

        if (Pessoa.authenticate(utilizador, pass) == null && Pessoa.authenticate2(utilizador, pass) == null) {
            System.out.print("invalid.user.or.password");
            System.out.print(utilizador);   // TODO test only
            return redirect(
                    routes.Application.login());
        }

        //se estiver no sistema como pessoa autorizada e tiver cargo de admin,
        //reencaminha para a pg de perfil do admin, caso contrário vai para a dashboard
        if(Pessoa.authenticate(utilizador,pass)!= null)
        {
            if (Pessoa.authenticate(utilizador, pass).cargo_id.equals("admin")) {
                System.out.print(Pessoa.authenticate(utilizador, pass).cargo_id.toString());    // TODO test only
                Pessoa.getNameInfo(utilizador);
                return redirect(routes.Application.admin_editsensors());
            }
        }

        Pessoa.getNameInfo(utilizador);
        return redirect(routes.Application.dashboard());
    }


    public static Result startSensors() {
        Runtime rt = Runtime.getRuntime();

        String path = "public\\sensors_class\\BiomonitSensors\\";
        System.out.println(path);
        String before = "java -cp " + path + "org.eclipse.paho.client.mqttv3-1.0.2.jar;" + path + "sqljdbc4.jar;" + path + "bin ";

        try {
            rt.exec(before + "sensors.subscriber.Subscriber");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            rt.exec(before + " sensors.publishers.health.HeartRateSensor");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            rt.exec(before + " sensors.publishers.home.BrightnessSensor");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            rt.exec(before + " sensors.publishers.home.TemperatureSensor");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return redirect(routes.Application.dashboard());
    }



    public static Result editprofile(){
        Form<Pessoa> loginForm = form(Pessoa.class).bindFromRequest();
        String nome = loginForm.get().nome;
        String pass = loginForm.get().pass;
        Integer contacto = loginForm.get().contacto;
        Integer n_cid =loginForm.get().n_cid;
        String cargo="na";
        System.out.print(user);
        if(Pessoa.exist(user)==null) {
            Pessoa.newPessoa(nome, pass, contacto, n_cid, user,cargo);
            return redirect(routes.Application.profile());

        }
        if (Pessoa.exist(user).cargo_id.equals("admin")) {

            Pessoa.editprofile(nome, pass, contacto, n_cid, user);
            Pessoa.deletegen(user);
            return redirect(routes.Application.admin_profile());
        }
        System.out.print(nome + pass + contacto + n_cid + user);
        Pessoa.editprofile(nome, pass, contacto, n_cid, user);
        return redirect(routes.Application.profile());


    }


    public static Result newAccount() {
        Form<gen_user> loginForm = form(gen_user.class).bindFromRequest();
        String nome = loginForm.get().nome;
        String pass = loginForm.get().pass;
        String passconf = loginForm.get().passconf;
        String email = loginForm.get().utilizador;

        // TODO test only
        System.out.print(pass+"!!!!!!!!!!!!");
        System.out.print(passconf+"!!!!!!!!!!!!!!!!!!");

        if (pass.equals(passconf)) {
            Pessoa.newAccount(nome,pass,email);
            System.out.print("Success");
            return redirect(routes.Application.login());
        }
        session().clear();
        return redirect(routes.Application.login());

    }

    public static Result newSensor() {
        Form<Sensor> loginForm = form(Sensor.class).bindFromRequest();
        System.out.print("passa no new account");
        String id = loginForm.get().id;
        if (Sensor.newSensor(id)!= null) {
            Sensor.newSensor(id);
            System.out.print("Success");
            return redirect(routes.Application.admin_editsensors());
        }
        session().clear();
        return redirect(routes.Application.admin_editsensors());

    }
    public static Result deleteSensor() {
        Form<Sensor> loginForm = form(Sensor.class).bindFromRequest();
        System.out.print("passa no delete");
        String id = loginForm.get().id;
        if (Sensor.deleteSensor(id)!= null) {
            Sensor.deleteSensor(id);
            System.out.print("Success");
            return redirect(routes.Application.admin_editsensors());
        }
        session().clear();
        return redirect(routes.Application.admin_editsensors());

    }

    public static Result deletePessoa() {
        Form<Pessoa> loginForm = form(Pessoa.class).bindFromRequest();
        String utilizador = loginForm.get().utilizador;
        System.out.print("passa no delete"+utilizador);
        if (Pessoa.exist(utilizador)!= null) {
            Pessoa.deletePessoa(utilizador);
            System.out.print("Success");
            return redirect(routes.Application.admin_editusers());
        }
        Pessoa.deletegen(utilizador);
        session().clear();
        return redirect(routes.Application.admin_editusers());

    }

    public static Result editPermission() {
        Form<Pessoa> loginForm = form(Pessoa.class).bindFromRequest();
        System.out.print("edit permission");
        String utilizador = loginForm.get().utilizador;
        String entidade_id = loginForm.get().entidade_id;
        String area_id = loginForm.get().area_id;
        String cargo_id = loginForm.get().cargo_id;
        String nome;
        String pass;
        if (Pessoa.exist(utilizador) != null) {
            System.out.print(utilizador + entidade_id + area_id + cargo_id);

            Pessoa.editPermission(utilizador, entidade_id, area_id, cargo_id);
            return redirect(routes.Application.admin_editusers());
        } else{
            gen_user p = Pessoa.existgen(utilizador);
        nome = p.nome;
        pass = p.pass;
        System.out.print("edit permission");
        Pessoa.newPessoa(nome, pass, 96,98 , utilizador, cargo_id);
        Pessoa.editPermission(utilizador, entidade_id, area_id, cargo_id);
        Pessoa.deletegen(utilizador);
        return redirect(routes.Application.admin_editusers());
        }
    }


    public static Result dashboard() {
        return ok(views.html.dashboard.render("BioMonit"));
    }

    public static Result aboutus() {
        return ok(views.html.about_us.render("BioMonit"));
    }

    public static Result createAccount() {
        return ok(create_account.render(form(gen_user.class)));
    }

    public static Result history() {
        return ok(views.html.history.render("BioMonit"));
    }
    public static Result profile() {
        return ok(views.html.profile.render(form(Pessoa.class)));
    }
    public static Result map() {
        return ok(views.html.map.render("BioMonit"));
    }
    public static Result admin_aboutus() {
        return ok(views.html.admin_about_us.render("BioMonit"));
    }
    public static Result admin_editsensors() {
        List sensor = Sensor.listofsensor();
        return ok(views.html.admin_edit_sensors.render(form(Sensor.class),sensor));
    }
    public static Result admin_removesensor() {
        List sensor = Sensor.listofsensor();
        return ok(views.html.admin_removersensor.render(form(Sensor.class),sensor));
    }
    public static Result admin_map() {
        return ok(views.html.admin_map.render("BioMonit"));
    }
    public static Result admin_editusers() {

        List utilizador = Pessoa.listofPerson();
        System.out.print(utilizador.toString()+"\n");
        List newuser = Pessoa.listofPerson2();
        System.out.print(newuser.toString()+"\n");

        return ok(views.html.admin_edit_users.render("BioMonit",utilizador,newuser,form(Pessoa.class)));
    }
    public static Result admin_profile() {
        return ok(views.html.admin_profile.render(form(Pessoa.class)));
    }


    public static Result admin_userdata() {
        return ok(views.html.admin_user_data.render(form(Pessoa.class)));
    }
}
