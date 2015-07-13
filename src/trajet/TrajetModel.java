package trajet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bdd.Adresse;
import bdd.DbConnexion;
import bdd.Utilisateur;

import java.util.*;

/**
 * Classe Trajet
 */
public class TrajetModel {
  
	 private Integer _id;
	    private Adresse depart;
	    private Adresse destination;
	    private Utilisateur conducteur;
	    private List<Utilisateur> passagers;
	    private  int nombrePlace;
	    private int nombrePlaceRestante;
	    private int detourMax;
	    private Date Date;

	    /**
	     * Constructeur par défaut
	     */
	    public TrajetModel(){

	    }

	    /**
	     * Constructeur avec arguments (sans passager)
	     * @param id
	     * @param depart
	     * @param destination
	     * @param conducteur
	     */
	    public TrajetModel(Integer id, Adresse depart, Adresse destination, Utilisateur conducteur){
	        this._id = id;
	        this.depart = depart;
	        this.destination = destination;
	        this.conducteur = conducteur;
	        this.passagers = null;
	    }

	    /**
	     * Constructeur avec arguments (avec passagers)
	     * @param id
	     * @param depart
	     * @param destination
	     * @param conducteur
	     * @param passagers
	     */
	    public TrajetModel(Integer id, Adresse depart, Adresse destination, Utilisateur conducteur, List<Utilisateur> passagers){
	        this._id = id;
	        this.depart = depart;
	        this.destination = destination;
	        this.conducteur = conducteur;
	        this.passagers = passagers;
	    }

	    // Getter - Setter
	    public Integer get_id() {
	        return _id;
	    }

	    public void set_id(Integer _id) {
	        this._id = _id;
	    }

	    public Adresse getDepart() {
	        return depart;
	    }

	    public void setDepart(Adresse depart) {
	        this.depart = depart;
	    }

	    public Adresse getDestination() {
	        return destination;
	    }

	    public void setDestination(Adresse destination) {
	        this.destination = destination;
	    }

	    public Utilisateur getConducteur() {
	        return conducteur;
	    }

	    public void setConducteur(Utilisateur conducteur) {
	        this.conducteur = conducteur;
	    }

	    public List<Utilisateur> getPassagers() {
	        return passagers;
	    }

	    public void setPassagers(List<Utilisateur> passagers) {
	        this.passagers = passagers;
	    }

	    public int getNombrePlace() {
	        return nombrePlace;
	    }

	    public void setNombrePlace(int nombrePlace) {
	        this.nombrePlace = nombrePlace;
	    }

	    public int getNombrePlaceRestante() {
	        return nombrePlaceRestante;
	    }

	    public void setNombrePlaceRestante(int nombrePlaceRestante) {
	        this.nombrePlaceRestante = nombrePlaceRestante;
	    }

	    public int getDetourMax() {
	        return detourMax;
	    }

	    public void setDetourMax(int detourMax) {
	        this.detourMax = detourMax;
	    }

	    public java.util.Date getDate() {
	        return Date;
	    }

	    public void setDate(java.util.Date date) {
	        Date = date;
	    }
       
	    public boolean Enregister() 
	    {
	    	try
	    	{
	    	Connection con = DbConnexion.Connecter();
	    	con.setAutoCommit(false);
	    	Statement stmt = con.createStatement();
	    	Statement stmt2 = con.createStatement();
	    	
	    	//On enregistre l'adresse de départ d'ailleurs
	    	String req = "INSERT INTO adresse(rue,codePostal,ville,pays) "+
	    				"VALUES('"+ this.getDepart().getRue() + "','"+
	    							this.getDepart().getCodePostal() + "','"+
	    							this.getDepart().getVille() + "','"+ 
	    							this.getDepart().getPays() + "')";
	    	
	    	int add_id = stmt.executeUpdate(req,Statement.RETURN_GENERATED_KEYS);
	    	System.out.println(req);
	    	
	    	String req2 = "INSERT INTO trajet (conducteur_id,nombre_place,place_restante,date,adresse_depart,adresse_arrivee)"+
	    					" VALUES("+ this.conducteur.get_id() +","
	    							  + this.nombrePlace + ","+
	    							  	this.nombrePlace +","+
	    							  	new java.sql.Date(new Date().getTime()) + ","+
	    							  	add_id + ","+
	    							    this.getDestination().get_id() +")";
	    	System.out.println(req2);
	    	stmt2.executeUpdate(req2);
	    	con.commit();
	    	
	    	return true;
	    	}
	    	catch(Exception ex)
	    	{
	    		System.out.println("Erreur SQL :" +  ex.getMessage());
	    		return false;
	    	}
	    }
    }


