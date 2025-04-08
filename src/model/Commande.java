package model;

public class Commande {

	private String nom;
	private int qte;
	private String statut;
	private int roleUser; 
	private String date;
		
	public Commande(String nom, int qte, String statut) {
		this.nom = nom;
		this.qte = qte;
		this.statut = statut;
	}
		
	public String getNom() { return this.nom; }
	public void setNom(String nom) { this.nom = nom; }
	public int getQte() { return this.qte; }
	public void setQte(int qte) { this.qte = qte; }
	public String getStatut() { return this.statut; }
	public void setStatut(String statut) { this.statut = statut; }
	public int getRoleUser() { return this.roleUser; }
	public void setRoleUser(int roleUser) { this.roleUser = roleUser; }
	public String getDate() { return this.date; }
	public void setDate(String date) { this.date = date; }
}
