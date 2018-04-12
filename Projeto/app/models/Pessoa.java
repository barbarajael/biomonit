package models;



import com.avaje.ebean.*;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.avaje.ebeaninternal.server.lib.util.Str;
import models.gen_user;

import java.util.List;

import static com.avaje.ebean.Expr.eq;


/**
 * Created by david-pc on 01-04-2017.
 */

@Entity
public class Pessoa extends Model {

    @Id
    public Integer n_cid;
    public String nome;
    public String utilizador;
    public String pass;
    public Integer contacto;
    public String entidade_id;
    public String area_id;
    public String cargo_id;

    public static String userID = "(default)";


    public Pessoa(String utilizador, String name, String password) {
        this.utilizador = utilizador;
        this.nome = name;
        this.pass = password;
    }

    public Pessoa() {
    }
    public static Pessoa exist(String utilizador) {
        // TODO test only


        Pessoa pessoa = find.where().eq("utilizador", utilizador).findUnique();
        return pessoa;
    }
    public static gen_user existgen(String utilizador) {
        // TODO test only


        gen_user genuser= find2.where().eq("utilizador", utilizador).findUnique();
        return genuser;
    }

    public static Pessoa authenticate(String utilizador, String pass) {
        // TODO test only
        System.out.println("\nTenta aceder à BD (Pessoa.authenticate)");
        System.out.print(utilizador+"\n");

        Pessoa pessoa = find.where().eq("utilizador", utilizador).eq("pass",pass).findUnique();
        return pessoa;
        }

    public static gen_user authenticate2(String utilizador, String pass) {
        // TODO test only
        System.out.println("\nTenta aceder à BD (Pessoa.authenticate)");
        System.out.print(utilizador+"\n");

        gen_user genUser= find2.where().eq("utilizador", utilizador).eq("pass", pass).findUnique();
        return genUser;

    }

    public static gen_user newAccount(String nome, String pass, String utilizador) {
        // TODO test only
        System.out.print(nome);
        System.out.print(pass);
        System.out.print(utilizador);

        SqlUpdate insert = Ebean.createSqlUpdate("INSERT INTO gen_user (nome, utilizador, pass, passconf) VALUES ('"+nome+"','"+utilizador+"','"+pass+"','"+pass+"')");
        insert.execute();
        return null;

    }
    public static Pessoa newPessoa(String nome, String pass,Integer contacto,Integer n_cid,String user,String cargo) {
        // TODO test only
        System.out.print(nome);
        System.out.print(pass);
        System.out.print(user);

        SqlUpdate insert = Ebean.createSqlUpdate("INSERT INTO Pessoa (nome, pass, contacto, n_cid, utilizador, cargo_id) VALUES ('"+nome+"','"+pass+"','"+contacto+"','"+n_cid+"','"+user+"','"+cargo+"')");
        insert.execute();
        return null;


    }
    public static Pessoa editprofile(String nome, String pass,Integer contacto,Integer n_cid,String utilizador) {
        SqlUpdate update = Ebean.createSqlUpdate("UPDATE Pessoa SET nome='"+nome+"',pass='"+pass+"',contacto='"+contacto+"',n_cid='"+n_cid+"' Where utilizador='"+utilizador+"'");
        update.execute();
        return null;

    }
    public static Pessoa editPermission(String utilizador, String entidade_id, String area_id,String cargo_id) {
        SqlUpdate update = Ebean.createSqlUpdate("UPDATE Pessoa SET entidade_id='"+entidade_id+"',area_id='"+area_id+"',cargo_id='"+cargo_id+"' Where utilizador='"+utilizador+"'");
        update.execute();
        return null;

    }
    public static Pessoa deletePessoa(String utilizador) {
        SqlUpdate delete = Ebean.createSqlUpdate("DELETE FROM Pessoa Where utilizador='"+utilizador+"'");
        delete.execute();
        return null;

    }
    public static gen_user deletegen(String utilizador) {
        SqlUpdate delete = Ebean.createSqlUpdate("DELETE FROM gen_user Where utilizador='"+utilizador+"'");
        delete.execute();
        return null;

    }

    public static List<Pessoa> listofPerson() {
        List<Pessoa> pessoas = find.findList();
        return pessoas;
    }


    public static List<gen_user> listofPerson2() {
        List<gen_user> gen_users = find2.findList();
        return gen_users;
    }


    public static Model.Finder<Integer, Pessoa> find = new Model.Finder<Integer,Pessoa>(Integer.class, Pessoa.class);
    public static Model.Finder<Integer,gen_user> find2 = new Model.Finder<Integer,gen_user>(Integer.class,gen_user.class);


    public String getName() {
        return nome;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.nome = name;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return utilizador;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.utilizador = email;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return pass;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.pass = password;
    }


    public static void getNameInfo (String mail) {
        String[] email = mail.split("@");
        userID = email[0];
        System.out.println(userID); // TODO test only
    }

    public static String sendNameInfo () {
        return userID;
    }
}



