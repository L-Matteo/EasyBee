package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Commande;
import model.Produit;
import utils.ConnexionBdd;

public class CommandeDAO {
	
	ConnexionBdd cn = ConnexionBdd.getInstance();
	
	public void updateQteRecu(int qteRecu, int id)
	{
		String query = "update detailcmd set qteRecu = ? where idCmdeApproDepot = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setInt(1, qteRecu);
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
					Commande uneCmde = new Commande(rs.getString("nomCommande"),rs.getString("statutCommande"), "");
					cmdes.add(uneCmde);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return cmdes;
	}
	
	public ArrayList<Produit> afficherProduitCmdeSelectione(int idCmde) 
	{
		ArrayList<Produit> produitsCmde = new ArrayList<>();
		
		String query = "select idProduit, codeProduit, designationProduit, qteCmde from detailproduit join produit on idProduit = produit.id "
				+ "where detailproduit.idCmdeApproDepot = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			stmt.setInt(1, idCmde);
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					Produit unProduit = new Produit(rs.getInt("idProduit"),rs.getInt("codeProduit"), rs.getString("designationProduit"),0,rs.getInt("qteCmde"));
					produitsCmde.add(unProduit);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return produitsCmde;
	} 
	 
	public boolean changerStatutCommande(String nomCommande, String newStatut) 
	{
		String query = "update cmdeapprodepot set statutCommande = ? where nomCommande = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1, newStatut);
			stmt.setString(2, nomCommande);
			stmt.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return false;
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
	 
	public int addCmdeApproDepot(int idCatSalarie) 
	{
		String queryInsert = "insert into cmdeapprodepot(dateCommande, statutCommande, idCatSalarie, nomCommande)"
				+ " values(CURRENT_DATE(),'en attente',?,'')";
		
		try(PreparedStatement stmtInsert = cn.laconnexion().prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS)){
			stmtInsert.setInt(1, idCatSalarie);
			stmtInsert.executeUpdate();
			
			ResultSet rs = stmtInsert.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				String queryUpdate = "update cmdeapprodepot set nomCommande = CONCAT('Commande n°', ?) where id = ?";
				try(PreparedStatement stmtUpdate = cn.laconnexion().prepareStatement(queryUpdate)){
					stmtUpdate.setInt(1, id);
					stmtUpdate.setInt(2, id);
					stmtUpdate.executeUpdate();
					return id;
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean addDetailsProduit(int idProduit, int idCmde, int qteCmde)
	{
		String query = "insert into detailproduit(idProduit, idCmdeApproDepot, qteCmde) values(?,?,?)";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setInt(1, idProduit);
			stmt.setInt(2, idCmde);
			stmt.setInt(3, qteCmde); 
			stmt.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addDescriptionProblem(String descriptionErreur, String nomCmde)
	{
		String query = "update cmdeapprodepot set descriptionErreur = ? where nomCommande = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1, descriptionErreur);
			stmt.setString(2, nomCmde);
			stmt.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Commande> getCommandeErreurs()
	{
		ArrayList<Commande> lesCommandes = new ArrayList<>();
		
		String query = "select nomCommande, statutCommande, descriptionErreur from cmdeapprodepot where statutCommande = 'Erreur'";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					lesCommandes.add(new Commande(rs.getString("nomCommande"), rs.getString("statutCommande"), rs.getString("descriptionErreur")));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lesCommandes;
	}
	 
	public boolean removeDescriptionProblem(String nomCmde)
	{
		String query = "update cmdeapprodepot set descriptionErreur = '' where nomCommande = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1,nomCmde);
			stmt.executeUpdate();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	 
}  
