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
					Produit unProduit = new Produit(rs.getInt("id"),rs.getInt("codeProduit"),rs.getString("designationProduit"),rs.getDouble("prixPdt"),0); 
					lesProduits.add(unProduit);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lesProduits; 
	}
	
	public int selectIdProduit(int codeProduit)
	{
		String query = "select id from produit where codeProduit = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setInt(1, codeProduit);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					return rs.getInt("id");
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int voirStockEntrepot(int idProduit) 
	{
		String query = "select stockEntrepot from produit where id = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setInt(1, idProduit);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return rs.getInt("stockEntrepot");
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
