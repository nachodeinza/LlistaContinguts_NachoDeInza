/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_nachodeinza;

import com.mycompany.llistacontinguts_model.Model;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

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
        } catch (SQLException ex) {
            System.out.println("error::" + ex.getMessage());
        }

    }

}
