package model;

public class Produit {
	
	private int id;
	private int codeProduit;
	private String designationProduit;
	private double prixPdt;
	
	public Produit(int id, int codeProduit, String designationProduit, double prixPdt) {
		this.id = id;
		this.codeProduit = codeProduit;
		this.designationProduit = designationProduit;
		this.prixPdt = prixPdt;
	}
	
	public int getId() { return this.id; }
	public void setId(int id) {this.id = id; }
	public int getCodeProduit() { return this.codeProduit; } 
	public void setCodeProduit(int codeProduit) { this.codeProduit = codeProduit; }
	public String getDesignationProduit() { return this.designationProduit; }
	public void setDesignationProduit(String designationProduit) { this.designationProduit = designationProduit; }
	public double getPrix() { return this.prixPdt; }
	public void setPrix(double prixPdt) { this.prixPdt = prixPdt; }
	
	@Override
	public String toString()
	{
		return designationProduit;
	}
	
}
