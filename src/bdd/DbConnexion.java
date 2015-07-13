/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;


import java.sql.DriverManager;
import com.mysql.jdbc.Driver;

/**
 *
 * @author William-
 */
public class DbConnexion {

    private static String url = "jdbc:mysql://localhost:3306/blablasam2";
    private static String utilisateur = "root";
    private static String motDePasse = "";
    private static java.sql.Connection connexion = null;

    public static java.sql.Connection Connecter() {

        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            
             return connexion;
          
            
        } catch (Exception e) {
         
            System.err.println("SQL Error : " + e);
            return connexion;
        }
       

    }

}
