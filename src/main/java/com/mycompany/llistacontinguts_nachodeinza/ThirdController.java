/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_nachodeinza;

import com.mycompany.llistacontinguts_model.Model;
import com.mycompany.llistacontinguts_model.Valoracio;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author Nacho
 */
public class ThirdController {

    Model model;

    public void injecta(Model model) {

        this.model = model;

    }
    @FXML
    Label nom;
    @FXML
    TextArea comentari;
    @FXML
    ComboBox estat;
    @FXML
    ComboBox calificacio;

    public void llistaValoracions() throws SQLException {
        estat.setItems(model.llistaEstat());
        calificacio.setItems(model.llistaCalificacio());
    }

    @FXML
    public void Cancelar() throws IOException {
        App.setRoot("secondary");
    }

    public void initialize() {

        try {
            llistaValoracions();
            nom.setText(model.getNom_contingut());
        } catch (SQLException ex) {
            System.out.println("error::" + ex.getMessage());
        }

    }
    
    public void afegirValoracio() throws SQLException   {
        
        Valoracio valoracio = new Valoracio(model.getId_usuari(), comentari.getText(), Date.valueOf(LocalDate.now()), calificacio.getSelectionModel().getSelectedItem().toString(), model.getId_contingut2());
        Valoracio valoracio1 = new Valoracio(estat.getSelectionModel().getSelectedItem().toString(),calificacio.getSelectionModel().getSelectedItem().toString());
        boolean ok = model.afegeixValoracio(valoracio);
        boolean ok1 = model.afegeixEstat(valoracio1);
        if (ok && ok1) {
            alerta("Valoració Afegida!!!");
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

}
