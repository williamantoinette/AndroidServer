package bdd;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

public class Utilisateur{
    public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public String getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public boolean isStatut() {
		return statut;
	}
	public void setStatut(boolean statut) {
		this.statut = statut;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private Integer _id;
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String dateInscription;
    private Adresse adresse;
    //private String adresseBis;
    private boolean statut;
    private String login;
    private String password;
    
    public boolean Inscription()
    {
    	
    	return true;
    }
    
    public boolean InsertUser() throws Exception {
		// Insere une personne dans la BDD
		java.util.Date date = new Date();
		String strDate = "";
		strDate += date.toLocaleString();
		
		Connection db = DbConnexion.Connecter();
		db.setAutoCommit(false);
		
		Statement stm_utilisateur = db.createStatement();
		Statement stm_adresse = db.createStatement();
		

		String reqAdresse = "INSERT INTO adresse (rue,codePostal,ville,pays) VALUES('"+
		this.getAdresse().getRue()+"','" +
		this.getAdresse().getCodePostal() + "','"+
		this.getAdresse().getVille() + "','"+
		this.getAdresse().getPays() + "')";
		
		int id_adresse = stm_adresse.executeUpdate(reqAdresse, Statement.RETURN_GENERATED_KEYS);
				
		String reqUtilisateur = "INSERT INTO utilisateur (nom,prenom,datenaissance,dateinscription,login,password,adresse_id)"
				+ "VALUES ('"
				+ nom
				+ "','"
				+ prenom
				+ "','"
				+ this.dateDeNaissance
				+ "','"
				+ strDate
				+ "','"
				+ login
				+ "','"
				+ this.password +
				"',"+
				id_adresse+")";

		System.out.println(reqAdresse);
		System.out.println(reqUtilisateur);
		try {
			stm_utilisateur.executeUpdate(reqUtilisateur);
			db.commit();
			return true;
		} catch (Exception ex) {
			db.rollback();
			System.out.println(ex.getMessage() + "Erreur d'ajout");
			return false;
		}

	}
}
