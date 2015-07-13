package authentification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.DbConnexion;
import bdd.*;
import com.google.gson.*;



/**
 * Servlet implementation class inscription
 */
public class inscription extends HttpServlet {
	
	private Gson jsonSerializer = new Gson();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{
		String login = request.getParameter("login");
		String pwd = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String DateNaissance = request.getParameter("DateNaissance");
		
		//Utilisateur user = ne< 
	
		
		//response.getWriter().write(Boolean.toString(InsertUser(nom, prenom, adresse, DateNaissance, login, pwd)));
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			response.getWriter().write("ERR");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
                    
            Utilisateur user = jsonSerializer.fromJson(json, Utilisateur.class);
            if(user != null)
            {
            	try
            	{
            	response.getWriter().write(Boolean.toString(user.InsertUser()));
            	}
            	catch(Exception ex)
            	{
            		response.getWriter().write("ERR");
            	}
            }
    		
        }
        
        
    
        
		
	}
	
	

}