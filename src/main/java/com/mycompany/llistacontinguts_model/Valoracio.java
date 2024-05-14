/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_model;

import java.sql.Date;

/**
 *
 * @author Nacho
 */
public class Valoracio {
    private int id;
    private int usuari_id;
    private String comentari;
    private Date data;
    private String calificació;
    private int contingut_id;
    private String estat;

    public Valoracio(int usuari_id, String comentari, Date data, String calificació, int contingut_id) {
        this.usuari_id = usuari_id;
        this.comentari = comentari;
        this.data = data;
        this.calificació = calificació;
        this.contingut_id = contingut_id;
    }
    

    public Valoracio( String estat, String calificació) {
        this.estat = estat;
        this.calificació = calificació;
    }

    public int getUsuari_id() {
        return usuari_id;
    }

    public void setUsuari_id(int usuari_id) {
        this.usuari_id = usuari_id;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getContingut_id() {
        return contingut_id;
    }

    public void setContingut_id(int contingut_id) {
        this.contingut_id = contingut_id;
    }
    
    
    public Valoracio(String estat) {
        this.estat = estat;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getCalificació() {
        return calificació;
    }

    public void setCalificació(String calificació) {
        this.calificació = calificació;
    }
    
    
}
