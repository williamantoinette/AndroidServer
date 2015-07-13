package authentification;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bdd.DbConnexion;
import bdd.Utilisateur;
import bdd.Adresse;

/**
 * Servlet implementation class authentification
 */
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
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
			//response.getWriter().write("true");
			response.getWriter().write(estAuthentifie(login,pwd));
		}
		catch(Exception ex)
		{
			response.getWriter().write("ERR");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	}

	public String estAuthentifie(String username, String password)
			throws Exception {
		Statement reqAuthentification = DbConnexion.Connecter().createStatement();
		
		String req = "SELECT u.*, a.* " +
					  "FROM utilisateur u "+
					  "INNER JOIN adresse a " +
					  "ON a.id = u.adresse_id " +
					  "WHERE login = '"+ username +"' " +
					  "AND password = '" + password  +"'";
		
		System.out.println(req);
		
		
//		
//		String req = "SELECT *.u, *.add " +
//					  "FROM utilisateur u"+
//					  "INNER JOIN adresse add ON u.adresse_id = add.id "
//					  +" WHERE login='"+ username.trim() + 
//					  "' AND password='" + password.trim() + "'";
		

		ResultSet resultat = reqAuthentification.executeQuery(req);
		if (resultat.next()) {
			Utilisateur user = new Utilisateur();
			Adresse adresse = new Adresse();
			
			user.set_id(Integer.parseInt(resultat.getString("id")));
			user.setLogin(resultat.getString("login"));
			user.setPassword(resultat.getString("password"));
			user.setPrenom(resultat.getString("prenom"));
			user.setNom(resultat.getString("nom"));
			user.setDateDeNaissance(resultat.getString("datenaissance"));
			user.setDateInscription(resultat.getString("dateinscription"));
			
			adresse.setCodePostal(resultat.getString("codePostal"));
			adresse.setRue(resultat.getString("rue"));
			adresse.setVille(resultat.getString("ville"));
			adresse.setPays(resultat.getString("pays"));
			
			user.setAdresse(adresse);
			
			
			Gson serialiseur = new Gson();
			return serialiseur.toJson(user);
		} else {
			return "false";
		}

	}
}
