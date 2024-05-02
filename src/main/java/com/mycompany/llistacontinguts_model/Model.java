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

    private int id_usuari;
    private int id_genere;
    private int id_contingut;
    private String nom_genere;

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

    public ObservableList<String> llistaEdad() {
        ObservableList<String> llistaEdad = FXCollections.observableArrayList();

        llistaEdad.add("+3");
        llistaEdad.add("+12");
        llistaEdad.add("+18");

        return llistaEdad;
    }

    public ObservableList<String> llistaEstat() {
        ObservableList<String> llistaEstat = FXCollections.observableArrayList();

        llistaEstat.add("Per veure");
        llistaEstat.add("Vista");
        llistaEstat.add("Veient");

        return llistaEstat;
    }

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

    public boolean modificarUsuari(Usuari usuari) throws IOException, SQLException {
        boolean ok = false;
        String sql = "UPDATE USUARI SET nom=?,correu=?,contrasenya=?";

        Connection connection = new Connexio().connecta();
        try {
            PreparedStatement ordre = connection.prepareStatement(sql);
            if (usuari.getNom().length() >= 1 && usuari.getCorreu().length() >= 1 && usuari.getContrasenya().length() >= 1) {

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

        String sql2 = "SELECT LAST_INSERT_ID() as contingut_id FROM CONTINGUT ";
        PreparedStatement ordre2 = connection.prepareStatement(sql2);
        ResultSet resultSet2 = ordre2.executeQuery();

        try {
            if (resultSet2.next()) {
                id_contingut = resultSet2.getInt("contingut_id");
                ordre2.executeQuery();
                ok = true;
            }

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        String sql3 = "SELECT genere_id FROM GENERE WHERE nom_genere=?";
        PreparedStatement ordre3 = connection.prepareStatement(sql3);
        ordre3.setString(1, nom_genere); // Establecer el valor del parámetro
        ResultSet resultSet3 = ordre3.executeQuery(); // Ejecutar la consulta después de establecer el valor del parámetro
        try {

            if (resultSet3.next()) {
                id_genere = resultSet3.getInt("genere_id");
                ordre3.execute();
                ok = true;
                System.out.println("ID del género seleccionado: " + id_genere); // Imprimir el ID del género seleccionado
            }
        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        String sql4 = "INSERT INTO GENERE_CONTINGUT(genere_id, contingut_id) VALUES(?,?)";
        PreparedStatement ordre4 = connection.prepareStatement(sql4);
        try {
            ordre4.setInt(1, id_genere);
            ordre4.setInt(2, id_contingut);
            ordre4.execute();
            ok = true;

        } catch (SQLException throwables) {
            System.out.println("Error:" + throwables.getMessage());
        }

        String sql5 = "INSERT INTO USUARI_CONTINGUT(usuari_id, contingut_id) VALUES(?,?)";
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
}
