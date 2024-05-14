package com.mycompany.llistacontinguts_model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author alumne
 */
public class Model {

    /**
     * Variables dintre del model de parametres que necesitarem durant tota
     * l'aplicació.
     */
    private int id_usuari;
    private int id_genere;
    private int id_contingut;
    private int id_contingut2;
    private String nom_genere;
    private String nom_contingut;

    /**
     * Getters i Setters.
     *
     * @return
     */
    public String getNom_contingut() {
        return nom_contingut;
    }

    public void setNom_contingut(String nom_contingut) {
        this.nom_contingut = nom_contingut;
    }

    public int getId_contingut() {
        return id_contingut;
    }

    public int getId_contingut2() {
        return id_contingut2;
    }

    public void setNom_genere(String nom_genere) {
        this.nom_genere = nom_genere;
    }

    public String getNom_genere() {
        return nom_genere;
    }

    public int getId_genere() {
        return id_genere;
    }

    public int getId_usuari() {
        return id_usuari;
    }

    /**
     * ObservableList de tipus usuari per a guardar en una llista els usuaris
     * creats.
     *
     * @return
     */
    public ObservableList<Usuari> llistaUsuaris() {
        ObservableList<Usuari> llistaUsuaris = FXCollections.observableArrayList();
        String sql = "select nom,correu,contrasenya from USUARI";
        //String sql="select nom from usuaris";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordre = connection.createStatement();
            ResultSet resultSet = ordre.executeQuery(sql);
            while (resultSet.next()) {
                llistaUsuaris.add(
                        new Usuari(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        )
                );
            }

            connection.close();

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }
        return llistaUsuaris;
    }

    /**
     * ObservableList de tipus Contingut per a guardar el contingut en una
     * llista.
     *
     * @return
     */
    public ObservableList<Contingut> llistaContingut() {
        ObservableList<Contingut> llistaContinguts = FXCollections.observableArrayList();
        String sql = "select titul,descripcio,clasificacio_edad,any_llançament,genere from CONTINGUT";
        //String sql="select nom from usuaris";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordre = connection.createStatement();
            ResultSet resultSet = ordre.executeQuery(sql);
            while (resultSet.next()) {
                llistaContinguts.add(
                        new Contingut(resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5))
                );

            }

            connection.close();

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }
        return llistaContinguts;
    }

    /**
     * ObservableList de tipus ContingutUsuari per a guardar el contingut de
     * cada usuari en una llista.
     *
     * @return
     */
    public ObservableList<ContingutUsuari> llistaContingutUsuari() {
        ObservableList<ContingutUsuari> llistaContingutUsuari = FXCollections.observableArrayList();
        String sql = "SELECT c.titul, c.descripcio, c.clasificacio_edad, c.any_llançament, c.genere FROM CONTINGUT c INNER JOIN USUARI_CONTINGUT uc ON c.contingut_id = uc.contingut_id WHERE uc.usuari_id = ?";
        Connection connection = new Connexio().connecta();
        try {

            PreparedStatement ordre5 = connection.prepareStatement(sql);
            ordre5.setInt(1, id_usuari);
            ResultSet resultSet5 = ordre5.executeQuery();
            while (resultSet5.next()) {
                llistaContingutUsuari.add(
                        new ContingutUsuari(
                                resultSet5.getString("titul"),
                                resultSet5.getString("descripcio"),
                                resultSet5.getString("clasificacio_edad"),
                                resultSet5.getString("any_llançament"),
                                resultSet5.getString("genere")
                        )
                );
            }

            connection.close();

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }
        return llistaContingutUsuari;

    }

    /**
     * ObservableList de tipus Valoració per a guardar totes les valoracions en
     * una llista.
     *
     * @return
     */
    public ObservableList<Valoracio> llistaValoracions() {

        ObservableList<Valoracio> llistaValoracions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM VALORACIO ";
        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre6 = connection.prepareStatement(sql);
            ResultSet resultSet6 = ordre6.executeQuery();
            while (resultSet6.next()) {
                llistaValoracions.add(
                        new Valoracio(resultSet6.getInt(1),
                                resultSet6.getString(2),
                                resultSet6.getDate(3),
                                resultSet6.getString(4),
                                resultSet6.getInt(5))
                );
            }
        } catch (Exception e) {
        }

        return llistaValoracions;

    }

    /**
     * He creat un ObservableList de tipus String per a el selector d'edat.
     *
     * @return
     */
    public ObservableList<String> llistaEdad() {
        ObservableList<String> llistaEdad = FXCollections.observableArrayList();

        llistaEdad.add("+3");
        llistaEdad.add("+12");
        llistaEdad.add("+18");

        return llistaEdad;
    }

    /**
     * He creat un ObservableList de tipus String per a el selector d'estat del
     * contingut.
     *
     * @return
     */
    public ObservableList<String> llistaEstat() {
        ObservableList<String> llistaEstat = FXCollections.observableArrayList();

        llistaEstat.add("Per veure");
        llistaEstat.add("Vista");
        llistaEstat.add("Veient");

        return llistaEstat;
    }

    /**
     * He creat un ObservableList de tipus String per al selector de
     * calificació.
     *
     * @return
     */
    public ObservableList<String> llistaCalificacio() {
        ObservableList<String> llistaCalificacio = FXCollections.observableArrayList();

        llistaCalificacio.add("0");
        llistaCalificacio.add("1");
        llistaCalificacio.add("2");
        llistaCalificacio.add("3");
        llistaCalificacio.add("4");
        llistaCalificacio.add("5");
        llistaCalificacio.add("6");
        llistaCalificacio.add("7");
        llistaCalificacio.add("8");
        llistaCalificacio.add("9");
        llistaCalificacio.add("10");

        return llistaCalificacio;
    }

    /**
     * He creat un ObservableList de tipus Genere per a guardar el nom dels
     * generes.
     *
     * @return
     */
    public ObservableList<Genere> llistaGeneres() {
        ObservableList<Genere> llistaGeneres = FXCollections.observableArrayList();
        String sql = "select nom_genere from GENERE";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordre = connection.createStatement();
            ResultSet resultSet = ordre.executeQuery(sql);
            while (resultSet.next()) {
                llistaGeneres.add(
                        new Genere(
                                resultSet.getString(1)
                        )
                );
            }

            connection.close();

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }
        return llistaGeneres;
    }

    /**
     * Métode de tipus Usuari per a consultar l'usuari introduint el correu i
     * contrasenya.
     *
     * @param usuari
     * @return
     */
    public boolean consultarUsuari(Usuari usuari) {
        boolean ok = false;
        String sql = "SELECT * FROM USUARI where correu=? AND contrasenya=?";
        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            ordre.setString(1, usuari.getCorreu());
            ordre.setString(2, usuari.getContrasenya());
            ResultSet resultSet = ordre.executeQuery();

            if (resultSet.next()) {
                id_usuari = resultSet.getInt("usuari_id");
                ok = true;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL:" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }

        return ok;
    }

    /**
     * Métode per a consultar l'id del contingut escribint el titol.
     *
     * @return
     */
    public boolean consultarID_Contingut() {
        boolean ok = false;
        String sql = "SELECT contingut_id FROM CONTINGUT where titul=?";
        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            ordre.setString(1, nom_contingut);
            ResultSet resultSet = ordre.executeQuery();

            if (resultSet.next()) {
                id_contingut2 = resultSet.getInt("contingut_id");
                ok = true;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL:" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }

        return ok;
    }

    /**
     * Métode de tipus usuari per a modificar l'usuari passant el nom, correu i
     * contrasenya.
     *
     * @param usuari
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public boolean modificarUsuari(Usuari usuari) throws IOException, SQLException {
        boolean ok = false;
        String sql = "UPDATE USUARI SET contrasenya=? where correu=? AND contrasenya=?";

        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            if (usuari.getNom().length() >= 1 && usuari.getCorreu().length() >= 1 && usuari.getContrasenya().length() >= 1 ) {

                ordre.setString(1, usuari.getNom());
                ordre.setString(2, usuari.getCorreu());
                ordre.setString(3, usuari.getContrasenya());
                ordre.executeUpdate();
                ok = true;
                System.out.println("usuari modificat");
            }

        } catch (SQLException e) {
            System.out.println("Error SQL:" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return ok;
    }

    /**
     * Métode de tipus usuari per a eliminar usuari introduint correu i
     * contrasenya.
     *
     * @param usuari
     * @return
     */
    public boolean eliminarUsuari(Usuari usuari) {
        boolean ok = false;
        String sql = "DELETE FROM USUARI WHERE correu=? AND contrasenya=? ";
        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            ordre.setString(1, usuari.getCorreu());
            ordre.setString(2, usuari.getContrasenya());
            ok = ordre.executeUpdate() > 0;
        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }
        return ok;
    }

    /**
     * Métode de tipus Contingut per a eliminar contingut passant el titol del
     * contingut.
     *
     * @param contingut
     * @return
     */
    public boolean eliminarContingut(Contingut contingut) {
        boolean ok = false;
        String sql = "DELETE FROM CONTINGUT WHERE titul=? ";
        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            ordre.setString(1, contingut.getTitol());
            ok = ordre.executeUpdate() > 0;
        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }
        return ok;
    }

    /**
     * Métode de tipus usuari per a afegir Usuari a la base de dades, passem el
     * nom, correu, contrasenya menys el ID, perque s'autoincrementa.
     *
     * @param usuari
     * @return
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean afegeixUsuari(Usuari usuari) throws SQLException, FileNotFoundException, IOException {
        boolean ok = false;
        Connection connection = new Connexio().connecta();
        String sql = "INSERT INTO USUARI(nom,correu,contrasenya) VALUES (?,?,?)";
        PreparedStatement ordre = connection.prepareStatement(sql);
        try {

            if (usuari.getNom().length() >= 1 && usuari.getCorreu().length() >= 1 && usuari.getContrasenya().length() >= 1) {
                ordre.setString(1, usuari.getNom());
                ordre.setString(2, usuari.getCorreu());
                ordre.setString(3, usuari.getContrasenya());
                ok = ordre.executeUpdate() > 0;

            }

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        return ok;
    }

    /**
     * Métode de tipus Contingut per a afegir Contingut a la base de dades,
     * introduint titul,descripcio,clasificacio_edad,any_llançament,genere
     *
     * @param contingut
     * @return
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean afegeixContingut(Contingut contingut) throws SQLException, FileNotFoundException, IOException {
        boolean ok = false;
        Connection connection = new Connexio().connecta();
        String sql = "INSERT INTO CONTINGUT(titul,descripcio,clasificacio_edad,any_llançament,genere) VALUES (?,?,?,?,?)";
        PreparedStatement ordre = connection.prepareStatement(sql);
        try {

            if (contingut.getTitol().length() >= 1 && contingut.getGenere().length() >= 1 && contingut.getEdad().length() >= 1 && contingut.getDescripcio().length() >= 1 && contingut.getAny().length() >= 1) {
                ordre.setString(1, contingut.getTitol());
                ordre.setString(2, contingut.getDescripcio());
                ordre.setString(3, contingut.getEdad());
                ordre.setString(4, contingut.getAny());
                ordre.setString(5, contingut.getGenere());
                ok = ordre.executeUpdate() > 0;
            }

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        String sql2 = "SELECT LAST_INSERT_ID() as contingut_id FROM CONTINGUT "; // Guardem la ultima ID del contingut que hem introduit.
        PreparedStatement ordre2 = connection.prepareStatement(sql2);
        ResultSet resultSet2 = ordre2.executeQuery();

        try {
            if (resultSet2.next()) {
                id_contingut = resultSet2.getInt("contingut_id"); // Guardem el ID en la variable del model.
                ordre2.executeQuery();
                ok = true;
            }

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        String sql3 = "SELECT genere_id FROM GENERE WHERE nom_genere=?";
        PreparedStatement ordre3 = connection.prepareStatement(sql3);
        ordre3.setString(1, nom_genere); // Error arreglat. 
        ResultSet resultSet3 = ordre3.executeQuery(); // Error arreglat.
        try {

            if (resultSet3.next()) {
                id_genere = resultSet3.getInt("genere_id");
                ordre3.execute();
                ok = true;
                System.out.println("ID del género seleccionado: " + id_genere); // Comprovació.
            }
        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        String sql4 = "INSERT INTO GENERE_CONTINGUT(genere_id, contingut_id) VALUES(?,?)"; // Inserta en la taula genere_contingut el id del genere i el id del contingut.
        PreparedStatement ordre4 = connection.prepareStatement(sql4);
        try {
            ordre4.setInt(1, id_genere);
            ordre4.setInt(2, id_contingut);
            ordre4.execute();
            ok = true;

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        String sql5 = "INSERT INTO USUARI_CONTINGUT(usuari_id, contingut_id) VALUES(?,?)"; // Inserta en la taula USUARI_CONTINGUT el id d'usuari i el id de contingut.
        PreparedStatement ordre5 = connection.prepareStatement(sql5);
        try {
            ordre5.setInt(1, id_usuari);
            ordre5.setInt(2, id_contingut);
            ordre5.execute();
            ok = true;

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        return ok;
    }

    /**
     * Métode de tipus Genere per afegir el Genere a la seva taula.
     *
     * @param genere
     * @return
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean afegeixGenere(Genere genere) throws SQLException, FileNotFoundException, IOException {
        boolean ok = false;
        Connection connection = new Connexio().connecta();
        String sql = "INSERT INTO GENERE(nom_genere) VALUES (?)";
        PreparedStatement ordre = connection.prepareStatement(sql);
        try {

            if (genere.getNom_genere().length() > 1) {
                ordre.setString(1, genere.getNom_genere());
                ordre.executeUpdate();
                ok = true;
            }

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        return ok;
    }

    /**
     * Métode de tipus Valoració per a afegir valoracions dintre de la taula
     * Valoració.
     *
     * @param valoracio
     * @return
     * @throws SQLException
     */
    public boolean afegeixValoracio(Valoracio valoracio) throws SQLException {
        boolean ok = false;
        Connection connection = new Connexio().connecta();
        String sql = "INSERT INTO VALORACIO(usuari_id, calificacio, comentari, data_valoracio, contingut_id) VALUES (?,?,?,?,?)";
        PreparedStatement ordre = connection.prepareStatement(sql);
        try {
            ordre.setInt(1, id_usuari);
            ordre.setString(2, valoracio.getCalificació());
            ordre.setString(3, valoracio.getComentari());
            ordre.setDate(4, valoracio.getData());
            ordre.setInt(5, id_contingut2);
            ordre.execute();
            ok = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ok;

    }

    /**
     * Métode de tipus Valoració per a modificar la taula USUARI_CONTINGUT.
     *
     * @param valoracio
     * @return
     * @throws SQLException
     */
    public boolean afegeixEstat(Valoracio valoracio) throws SQLException {
        boolean ok = false;
        Connection connection = new Connexio().connecta();
        String sql = "UPDATE USUARI_CONTINGUT SET estat=?, calificacio=? where contingut_id=? AND usuari_id=?";
        PreparedStatement ordre = connection.prepareStatement(sql);
        try {
            ordre.setString(1, valoracio.getEstat());
            ordre.setString(2, valoracio.getCalificació());
            ordre.setInt(3, id_contingut2);
            ordre.setInt(4, id_usuari);
            ordre.execute();
            ok = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ok;

    }
}
