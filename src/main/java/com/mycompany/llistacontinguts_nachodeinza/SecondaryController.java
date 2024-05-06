package com.mycompany.llistacontinguts_nachodeinza;

import com.mycompany.llistacontinguts_model.Contingut;
import com.mycompany.llistacontinguts_model.ContingutUsuari;
import com.mycompany.llistacontinguts_model.Genere;
import com.mycompany.llistacontinguts_model.Model;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

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
    @FXML
    ListView listview_contingut;

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
        listview_contingut.setItems(model.llistaContingutUsuari());
    }

    public void afegirGenere() throws SQLException, IOException {
        Genere genere = new Genere(nom_genere.getText());
        boolean ok = model.afegeixGenere(genere);

        if (ok) {
            alerta("Genere Afegit!!");
            llista_generes.setItems(model.llistaGeneres());
            esborrarGenere();
        } else {
            alerta("No s'ha pogut afegir el Génere");
        }
    }

    public void afegirContingut() throws SQLException, IOException {
        Contingut contingut = new Contingut(titol.getText(), descripcio.getText(), edad.getSelectionModel().getSelectedItem().toString(), any.getText(), llista_generes.getSelectionModel().getSelectedItem().toString());
        model.setNom_genere(llista_generes.getSelectionModel().getSelectedItem().toString());

        boolean ok = model.afegeixContingut(contingut);
        if (ok) {
            alerta("Contingut Afegit!!" + model.getNom_genere());
            listview_contingut.setItems(model.llistaContingutUsuari());
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

    public void esborrarGenere() {

        nom_genere.clear();

    }

    public void initialize() {

        try {
            llista();
        } catch (SQLException ex) {
            System.out.println("error::" + ex.getMessage());
        }

        listview_contingut.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                // Obtener el contenido seleccionado
                ContingutUsuari contingutSeleccionado = (ContingutUsuari) listview_contingut.getSelectionModel().getSelectedItem();
                model.setNom_contingut(contingutSeleccionado.getTitol());;
                model.consultarID_Contingut();
                // Aquí puedes realizar las acciones necesarias con el contenido seleccionado
                alerta("Contingut ID: " + model.getId_contingut2());
                try {
                    App.setRoot("third");
                } catch (IOException ex) {
                    Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void eliminar() throws SQLException {
        int pos = llista_contingut.getSelectionModel().getSelectedIndex();
        Contingut contingut = new Contingut(titol.getText());
        boolean ok = model.eliminarContingut(contingut);
        if (ok) {
            llista();
            llista_contingut.getSelectionModel().select(pos);
            alerta("Contingut Eliminat!!");
            esborrar();
        } else {
            alerta("No s'ha pogut eliminar el contingut!!");
        }

    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("third");
    }

}
