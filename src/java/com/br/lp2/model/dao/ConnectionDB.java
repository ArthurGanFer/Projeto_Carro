/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author 1147106
 */
public class ConnectionDB {
    private static String driver = "org.apache.derby.jdbc.ClientDriver";
    private static String protocolo = "jdbc:derby:";
    private static String db = "projeto_db";
    private static String dominio = "//localhost:1527/";
    private static String user = "arthur";
    private static String pwd = "123";
    private static Connection conn = null;
    
    public static Connection getInstance()
    {
        if(conn==null){
            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(protocolo + dominio + db , user, pwd);
            } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return conn;
    }
}
