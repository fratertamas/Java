/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjdbcconnection;

/**
 *
 * @author Halacska-NB4
 */
public class FirstJDBCConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DB db = new DB();
        
//        db.addUser("Margit", "Almafa");
        db.showAllUser();
    }
    
}
