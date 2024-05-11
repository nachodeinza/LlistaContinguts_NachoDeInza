/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_model;


public class ContingutUsuari {
    private final String titol;
    private final String descripcio;
    private final String edad;
    private final String any;
    private final String genere;

    public ContingutUsuari(String titol, String descripcio, String edad, String any, String genere) {
        this.titol = titol;
        this.descripcio = descripcio;
        this.edad = edad;
        this.any = any;
        this.genere = genere;
    }

    @Override
    public String toString() {
        return titol + ", " + descripcio  + ", " + edad + ", " +  any + ", " + genere ;
    }

    public String getTitol() {
        return titol;
    }
    
}
