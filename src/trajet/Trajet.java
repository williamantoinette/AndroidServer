package trajet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class trajet
 */
public class Trajet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Trajet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	     
		  Gson serialiseur = new Gson();
		  
	      
		  String json = "";
	        if(br != null){
	            json = br.readLine();
	        }
	        System.out.println("JSON : " + json);
	        TrajetModel trajet = serialiseur.fromJson(json, TrajetModel.class);
	       
	        if(trajet != null)
	        {
	        	
	        	if(trajet.Enregister())
	        	{
	        	response.getWriter().write("true");
	        	}
	        	else
	        	{
	        		response.getWriter().write("false");
	        	}
	        }
	        else
	        {
	        	response.getWriter().write("false");
	        }
	
	}

}
