package com.mycompany.llistacontinguts_nachodeinza;

import com.mycompany.llistacontinguts_model.Model;
import com.mycompany.llistacontinguts_model.Usuari;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController {

    Model model;

    /**
     * Métode per a canviar al secondary controller.
     * @throws IOException 
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    TextField nom_t;
    @FXML
    TextField correu_t;
    @FXML
    PasswordField contrasenya_t;
    @FXML
    TextField correu_t2;
    @FXML
    PasswordField contrasenya_t2;
    @FXML
    PasswordField novacontra;

    /**
     * Métode per afegir nou usuari
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @FXML
    public void afegir() throws SQLException, IOException {

        Usuari usuari = new Usuari(nom_t.getText(),
                correu_t.getText(), contrasenya_t.getText());

        boolean ok = model.afegeixUsuari(usuari);
        if (ok) {

            alerta("Usuari Afegit!!");
            esborrar();
        } else {
            alerta("No s'ha pogut afegir l'usuari!!");
        }
    }

    /**
     * Métode per modificar l'usuari.
     *
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @FXML
    public void modificar() throws SQLException, IOException {

        Usuari usuari = new Usuari(novacontra.getText(), correu_t.getText(), contrasenya_t.getText());

        boolean ok = model.modificarUsuari(usuari);

        if (ok) {

            alerta("Usuari Modificat!!");
            esborrar();
        } else {
            alerta("No s'ha pogut modificar l'usuari!!");
        }

    }

    /**
     * Métode per a iniciar sessió i executant el metode del
     * model.consultarusuari
     *
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    @FXML
    public void iniciSessio() throws SQLException, IOException {

        Usuari usuari = new Usuari(correu_t2.getText(), contrasenya_t2.getText());
        boolean ok = model.consultarUsuari(usuari);

        if (ok) {

            alerta("Sessió iniciada! " + "ID: " + model.getId_usuari()); // Prova per a veure que el ID esta ben agafat.
            App.setRoot("secondary");
            esborrar();
        } else {

            alerta("No s'ha pogut iniciar sessió!");

        }
    }

    /**
     * Métode per a eliminar usuari introduint tots els camps de l'apartat de
     * registre.
     *
     * @throws java.sql.SQLException
     */
    @FXML
    public void eliminar() throws SQLException {

        Usuari usuari = new Usuari(nom_t.getText(), correu_t.getText(), contrasenya_t.getText());

        boolean ok = model.eliminarUsuari(usuari);
        if (ok) {

            alerta("Usuari Eliminat!!");
            esborrar();
        } else {
            alerta("No s'ha pogut eliminar l'usuari!!");
        }

    }

    /**
     * Métode per a netejar els camps de registre.
     */
    public void esborrar() {

        nom_t.clear();
        correu_t.clear();
        contrasenya_t.clear();
        novacontra.clear();
    }

    /**
     *
     * @param text Métode per a crear una alerta per pantalla.
     */
    private void alerta(String text) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("Informació");
        alerta.setContentText(text);
        alerta.show();
    }

    /**
     *
     * @param model Métode per a injectar el model al controlador.
     */
    public void injecta(Model model) {

        this.model = model;

    }

}
