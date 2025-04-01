package model;

public class Commande {

	private String nom;
	private String statut;
	private int roleUser; 
	private String date;
		
	public Commande() {
			
	}
		
	public String getNom() { return this.nom; }
	public void setNom(String nom) { this.nom = nom; }
	public String getStatut() { return this.statut; }
	public void setStatut(String statut) { this.statut = statut; }
	public int getRoleUser() { return this.roleUser; }
	public void setRoleUser(int roleUser) { this.roleUser = roleUser; }
	public String getDate() { return this.date; }
	public void setDate(String date) { this.date = date; }
}
