/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_model;

/**
 *
 * @author alumne
 */
public class Genere {
    private int id;
    private final String nom_genere;

    public Genere(String nom_genere) {
        this.nom_genere = nom_genere;
    }

    public String getNom_genere() {
        return nom_genere;
    }

    @Override
    public String toString() {
        return nom_genere;
    }

    
    
    
}
