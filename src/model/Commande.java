package model;

public class Commande {

	private int id;
	private String nom;
	private int qte;
	private String statut;
	
		
	public Commande(String nom, int qte, String statut) {
		this.nom = nom;
		this.qte = qte;
		this.statut = statut;
	}
		
	public int getId() { return this.id; }
	public String getNom() { return this.nom; }
	public void setNom(String nom) { this.nom = nom; }
	public int getQte() { return this.qte; }
	public void setQte(int qte) { this.qte = qte; }
	public String getStatut() { return this.statut; }
	public void setStatut(String statut) { this.statut = statut; }
}
