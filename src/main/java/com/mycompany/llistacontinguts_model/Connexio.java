package com.mycompany.llistacontinguts_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {

    /**
     * Classe per a crear la connexio amb la base de dades.
     */
    private final String URL = "jdbc:mysql://localhost:3306/LlistaContinguts_db";//nom bd 
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWD = "";

    /**
     * Métode per a connectar la base de dades amb el codi java.
     * @return
     */
    public Connection connecta() {
        Connection connexio = null;
        try {
            //Carreguem el driver          
            Class.forName(DRIVER);
            connexio = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
        return connexio;
    }

}
