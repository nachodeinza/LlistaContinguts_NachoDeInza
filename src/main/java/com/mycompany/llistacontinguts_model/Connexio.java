
package com.mycompany.llistacontinguts_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {

    //private final String URL = "jdbc:mysql://localhost:3306/dam_bd";//nom bd
    private final String URL = "jdbc:mysql://localhost:3306/LlistaContinguts_db";//nom bd 
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWD = "";   
   

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