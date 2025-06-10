package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produit;
import utils.ConnexionBdd;

public class ProduitDAO {
	
	ConnexionBdd cn = ConnexionBdd.getInstance();
	
	public ArrayList<Produit> listeProduit() 
	{
		
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		
		String query = "select id, codeProduit, designationProduit, prixPdt from produit";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					Produit unProduit = new Produit(rs.getInt("id"),rs.getInt("codeProduit"),rs.getString("designationProduit"),rs.getDouble("prixPdt")); 
					lesProduits.add(unProduit);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lesProduits; 
	}
	
	public void voirStockEntrepot(int idProduit) 
	{
		
	}

}
