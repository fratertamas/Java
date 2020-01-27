/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjdbcconnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Halacska-NB4
 */
public class DB {
    final String JDBC_Driver = "com.mysql.jdbc.Driver";
    final String DB_USER_NAME = "root";
    final String DB_PASSWORD = "";
    final String DB_URL = "jdbc:mysql://localhost:3306/hasznaltcikkek";  
    
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    public DB(){
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
            System.out.println("A híd létrejött");
        } catch (SQLException ex) {
            System.out.println("BAJ VAN - Connection ");
            System.out.println(""+ex);
        }
        
        if(conn != null){
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Baj van - createStatement");
                System.out.println(""+ex);
            }
        }
        
        try {
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Baj van - DatabaseMetaData");
            System.out.println(""+ex);        
        }
        
        try {
            ResultSet rs1 = dbmd.getTables(null, null, "felhasznalok", null);
            while (rs1.next()) {
//                System.out.println("TáblaNév: "+rs1.getString("Table_NAME"));
//                System.out.println("TáblaTípus: "+rs1.getString("TABLE_TYPE"));
//                System.out.println("TáblaSéma: "+rs1.getString("TABLE_SCHEM"));
//                System.out.println("DBNÉV: "+rs1.getString("TABLE_CAT"));
//                System.out.println(" ");
             }
        } catch (SQLException ex) {
            System.out.println("Baj van - ResultSet");
            System.out.println(""+ex);
        }
    }
    
    public void addUser(String nev, String jelszo){
        
        try {
            //CreateStatement segítségével 
//            String sql = "INSERT INTO felhasznalok (uname, passwd) VALUES ('"+nev+"', '"+jelszo+"');";
//            createStatement.execute(sql);
            //PreapareStatement segítségével
            String sql = "INSERT INTO felhasznalok (uname, passwd) VALUES (?,?);";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, nev);
            prst.setString(2, jelszo);
            prst.execute();
        } catch (SQLException ex) {
            System.out.println("Baj van - Add USER");
            System.out.println(""+ex);
        }
    }
    
    public void showAllUser(){
        String sql = "SELECT * FROM felhasznalok;";
        
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            String fnev;
            String pw;
            while(rs.next()){
                fnev = rs.getString("uname");
                pw = rs.getString("passwd");
                System.out.println(fnev +" | "+pw);
            }
        } catch (SQLException ex) {
            System.out.println("Baj van - Show User");
            System.out.println(""+ex);
        }
    }
}
