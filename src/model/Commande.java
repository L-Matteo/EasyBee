package model;

public class Commande {

	private int id;
	private String nom;
	private String statut;
	private String descriptionErreur;
	
		
	public Commande(String nom, String statut, String descriptionErreur) {
		this.nom = nom;
		this.statut = statut;
		this.descriptionErreur = descriptionErreur;
	}
		
	public int getId() { return this.id; }
	public String getNom() { return this.nom; }
	public void setNom(String nom) { this.nom = nom; }
	public String getStatut() { return this.statut; }
	public void setStatut(String statut) { this.statut = statut; }
	public String getErreur() { return this.descriptionErreur; }
}
