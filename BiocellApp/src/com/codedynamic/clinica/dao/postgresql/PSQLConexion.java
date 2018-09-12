package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PSQLConexion {
	Connection conectar() {
        Connection conexion = null;
        //String url = "jdbc:postgresql://192.168.0.150/db_clinica";
        String url = "jdbc:postgresql://localhost/db_clinica_prueba";
        Properties propieadades = new Properties();
        //propieadades.setProperty("user", "dba_admin");
        //propieadades.setProperty("password", "admin4049");
        propieadades.setProperty("user", "dba_clinica");
        propieadades.setProperty("password", "admin4049");
        try {
            conexion = DriverManager.getConnection(url, propieadades);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
