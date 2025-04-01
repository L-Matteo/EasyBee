package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnexionBdd;

public class CommandeDAO {
	
	ConnexionBdd cn = ConnexionBdd.getInstance();
	
	public void createCommande(String date, String statut, int roleUser, String nom) {
		
		String query = "insert into cmdeapprodepot(dateCommande, statutCommande, idCatSalarie, nomCommande) values(?,?,?,?)";
		
		if(date != "") {
			System.out.println("Erreur dans le format de la date");
		}
		
		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)){
			stmt.setString(1,date);
			stmt.setString(2, statut);
			stmt.setInt(3, roleUser);
			stmt.setString(4, nom);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'insertion de la commande : " + e.getMessage());
		}
	}
	
	public void deleteCommande() {
		
	}
}
