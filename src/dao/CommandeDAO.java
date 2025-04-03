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
	
	public void createCommande(String date, String statut, int roleUser, String nom) {
		
		String query = "insert into cmdeapprodepot(dateCommande, statutCommande, idCatSalarie, nomCommande) values(?,?,?,?)";
		
		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1,date);
			stmt.setString(2, statut);
			stmt.setInt(3, roleUser);
			stmt.setString(4, nom);
			stmt.executeUpdate();
			System.out.println("Commande ajouté avec succès");
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'insertion de la commande : " + e.getMessage());
		}
	}
	
	public List<String> listeCommandeEnAttente() {
		
		List<String> commandes = new ArrayList<>();
		
		String query = "Select nomCommande from cmdeapprodepot where statutCommande like '%en attente%'";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query); ResultSet rs = stmt.executeQuery()){
			while(rs.next()) {
				commandes.add(rs.getString("nomCommande"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return commandes;
	}
	
	public Commande afficherDetailsCmdeSelectione(String cmdeSelectionne) {
		
		String query = "select nomCommande, qtePrepa from detailcmd join cmdeapprodepot on idCmdeApproDepot = cmdeapprodepot.id where nomCommande = ?";
		
		try(PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			stmt.setString(1,cmdeSelectionne);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					return new Commande(rs.getString("nomCommande"), rs.getInt("qtePrepa"));
				}
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
} 
