package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Commande;
import utils.ConnexionBdd;

public class CommandeDAO {
	
	ConnexionBdd cn = ConnexionBdd.getInstance();
	
	public void updateQtePrepa(int qtePrepa, int id) {
		String query = "update detailcmd set qtePrepa = ? where idCmdeApproDepot = ?";
		
		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setInt(1, qtePrepa);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Commande> listeCommandeStatut(String statut) 
	{	
		List<Commande> cmdes = new ArrayList<>();
		
		String query = "Select nomCommande, statutCommande from cmdeapprodepot where statutCommande like ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1, "%" + statut + "%");
			try (ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					Commande uneCmde = new Commande(rs.getString("nomCommande"),0 ,rs.getString("statutCommande"));
					cmdes.add(uneCmde);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return cmdes;
	}
	
	public Commande afficherDetailsCmdeSelectione(String cmdeSelectionne) 
	{
		String query = "select nomCommande, designationProduit, qteCmde from detailproduit join cmdeapprodepot on idCmdeApproDepot = cmdeapprodepot.id join produit on idProduit = produit.id where nomCommande = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			stmt.setString(1,cmdeSelectionne);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					return new Commande(rs.getString("designationProduit"), rs.getInt("qteCmde"), "");
				}
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	 
	public void changerStatutCommande(String nomCommande, String newStatut) 
	{
		String query = "update cmdeapprodepot set statutCommande = ? where nomCommande = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1, newStatut);
			stmt.setString(2, nomCommande);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int selectIdCmde(String nomCommande) 
	{
		String query = "select id from cmdeapprodepot where nomCommande = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1, nomCommande);
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
} 
 