/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.llistacontinguts_model;

/**
 *
 * @author alumne
 */
public class Usuari {
    private int id;
    private String nom;
    private String correu;
    private String contrasenya;

    public Usuari(int id,String nom, String correu, String contrasenya) {
        this.id = id;
        this.nom = nom;
        this.correu = correu;
        this.contrasenya = contrasenya;
    }

    public Usuari(String correu, String contrasenya) {
        this.correu = correu;
        this.contrasenya = contrasenya;
    }
    

    public Usuari(String nom, String correu, String contrasenya) {
        this.nom = nom;
        this.correu = correu;
        this.contrasenya = contrasenya;
    }

    public Usuari(int id, String correu, String contrasenya) {
        this.id = id;
        this.correu = correu;
        this.contrasenya = contrasenya;
    }

    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCorreu() {
        return correu;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return nom;
    }
    
    
    
}
