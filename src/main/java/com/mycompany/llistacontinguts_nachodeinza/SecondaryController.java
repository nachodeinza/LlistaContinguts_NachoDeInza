package com.mycompany.llistacontinguts_nachodeinza;

import com.mycompany.llistacontinguts_model.Contingut;
import com.mycompany.llistacontinguts_model.Genere;
import com.mycompany.llistacontinguts_model.Model;
import com.mycompany.llistacontinguts_model.Usuari;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SecondaryController {

    Model model;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    ComboBox llista_contingut;
    @FXML
    TextField id_usuari;
    @FXML
    TextField titol;
    @FXML
    TextField descripcio;
    @FXML
    ComboBox edad;
    @FXML
    TextField any;
    @FXML
    ComboBox llista_generes;
    @FXML
    TextField nom_genere;

    public void mostraID() {
        id_usuari.setText("ID:" + model.getId_usuari());
    }

    public void injecta(Model model) {
        this.model = model;
    }

    public void llista() throws SQLException {
        llista_generes.setItems(model.llistaGeneres());
        edad.setItems(model.llistaEdad());
        llista_contingut.setItems(model.llistaContingut());
    }

    public void afegirGenere() throws SQLException, IOException {
        Genere genere = new Genere(nom_genere.getText());

        boolean ok = model.afegeixGenere(genere);
        
        if (ok) {
            alerta("Genere Afegit!!");
        } else {
            alerta("No s'ha pogut afegir el Génere");
        }
    }
    
    public void afegirContingut() throws SQLException, IOException {
        Contingut contingut = new Contingut(titol.getText(),descripcio.getText(),edad.getSelectionModel().getSelectedItem().toString(),any.getText(),llista_generes.getSelectionModel().getSelectedItem().toString());
        model.setNom_genere((String)llista_generes.getSelectionModel().getSelectedItem());
        boolean ok = model.afegeixContingut(contingut);
        if (ok) {
            alerta("Contingut Afegit!!");
        } else {
            alerta("No s'ha pogut afegir el Contingut");
        }
    }

    private void alerta(String text) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("Informació");
        alerta.setContentText(text);
        alerta.show();
    }

    public void esborrar() {
        nom_genere.clear();
        titol.clear();
        descripcio.clear();
        edad.getSelectionModel().select(null);
        any.clear();
        llista_generes.getSelectionModel().select(null);
    }

    public void initialize() {

        try {
            llista();
        } catch (SQLException ex) {
            System.out.println("error::" + ex.getMessage());
        }

    }

    public void eliminar() throws SQLException {
        int pos = llista_contingut.getSelectionModel().getSelectedIndex();
        Contingut contingut = new Contingut(titol.getText());
        boolean ok = model.eliminarContingut(contingut);
        if (ok) {
            llista();
            llista_contingut.getSelectionModel().select(pos);
            alerta("Usuari Eliminat!!");
            esborrar();
        } else {
            alerta("No s'ha pogut eliminar el contingut!!");
        }

    }
    
}
