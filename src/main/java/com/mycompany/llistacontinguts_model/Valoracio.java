/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_model;

/**
 *
 * @author Nacho
 */
public class Valoracio {
    private String estat;
    private int calificació;

    public Valoracio(String estat, int calificació) {
        this.estat = estat;
        this.calificació = calificació;
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

    public int getCalificació() {
        return calificació;
    }

    public void setCalificació(int calificació) {
        this.calificació = calificació;
    }
    
    
}
