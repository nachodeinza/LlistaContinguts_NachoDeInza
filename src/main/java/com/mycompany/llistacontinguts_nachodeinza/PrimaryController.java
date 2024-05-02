package com.mycompany.llistacontinguts_nachodeinza;

import com.mycompany.llistacontinguts_model.Model;
import com.mycompany.llistacontinguts_model.Usuari;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController {

    Model model;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    ComboBox combobox;
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

    public void llista() throws SQLException {
        combobox.setItems(model.llistaUsuaris());
    }

    public void afegir() throws SQLException, IOException {
        Usuari usuari = new Usuari(nom_t.getText(),
                correu_t.getText(), contrasenya_t.getText());

        boolean ok = model.afegeixUsuari(usuari);
        if (ok) {
            llista();
            combobox.getSelectionModel().select(0);
            alerta("Usuari Afegit!!");
            esborrar();
        } else {
            alerta("No s'ha pogut afegir l'usuari!!");
        }
    }

    @FXML
    public void modificar() throws SQLException, IOException {
        int pos = combobox.getSelectionModel().getSelectedIndex();

        Usuari usuari = new Usuari(nom_t.getText(), correu_t.getText(),
                contrasenya_t.getText());

        boolean ok = model.modificarUsuari(usuari);

        if (ok) {
            llista();
            combobox.getSelectionModel().select(pos);
            alerta("Usuari Modificat!!");
            esborrar();
        } else {
            alerta("No s'ha pogut modificar l'usuari!!");
        }

    }

    @FXML
    public void iniciSessio() throws SQLException, IOException {

        Usuari usuari = new Usuari(correu_t2.getText(), contrasenya_t2.getText());
        boolean ok = model.consultarUsuari(usuari);

        if (ok) {
            llista();
            alerta("Sessió iniciada! " + "ID: " + model.getId_usuari());
            App.setRoot("secondary");
            esborrar();
        } else {

            alerta("No s'ha pogut iniciar sessió!");

        }
    }

    @FXML
    public void imprimeix() {

        if (!combobox.getSelectionModel().isEmpty()) {
            Usuari usuari = (Usuari) combobox.getSelectionModel().getSelectedItem();
            nom_t.setText(usuari.getNom());
            correu_t.setText(usuari.getCorreu());
            contrasenya_t.setText(usuari.getContrasenya());
            correu_t.setText(usuari.getCorreu());

        }

    }

    public void eliminar() throws SQLException {
        int pos = combobox.getSelectionModel().getSelectedIndex();
        Usuari usuari = new Usuari(nom_t.getText(), correu_t.getText(), contrasenya_t.getText());

        boolean ok = model.eliminarUsuari(usuari);
        if (ok) {
            llista();
            combobox.getSelectionModel().select(0);
            alerta("Usuari Eliminat!!");
            esborrar();
        } else {
            alerta("No s'ha pogut eliminar l'usuari!!");
        }

    }

    public void esborrar() {
        nom_t.clear();
        correu_t.clear();
        contrasenya_t.clear();
        combobox.getSelectionModel().select(null);
    }

    private void alerta(String text) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("Informació");
        alerta.setContentText(text);
        alerta.show();
    }

    public void initialize() {
        try {
            llista();
        } catch (SQLException ex) {
            System.out.println("error::" + ex.getMessage());
        }
    }

    public void injecta(Model model) {

        this.model = model;

    }

}
