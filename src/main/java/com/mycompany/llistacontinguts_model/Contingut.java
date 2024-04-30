/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_model;

/**
 *
 * @author alumne
 */
public class Contingut {
    private int id;
    private String titol;
    private String descripcio;
    private String edad;
    private String any;
    private String genere;

    public Contingut(String titol, String descripcio, String edad, String any, String genere) {
        this.titol = titol;
        this.descripcio = descripcio;
        this.edad = edad;
        this.any = any;
        this.genere = genere;
    }

    public Contingut(String titol) {
        this.titol = titol;
    }

    
    public String getTitol() {
        return titol;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getEdad() {
        return edad;
    }

    public String getAny() {
        return any;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return titol + ", " + edad + ", " + any + ", " + genere ;
    }
    
    
}
