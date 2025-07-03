package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Utilisateur;
import utils.ConnexionBdd;

public class UtilisateurDAO {
	
	static ConnexionBdd cn = ConnexionBdd.getInstance();
	
	public Utilisateur seConnecter(String login, String password) {
		
		String query = "SELECT identifiant, idCat FROM salarie WHERE identifiant = ? AND motDePasse = ?";
        
        try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {

            stmt.setString(1, login);
            stmt.setString(2, password); // ⚠️ À remplacer par un hashage sécurisé plus tard

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Utilisateur(rs.getString("identifiant"), rs.getInt("idCat"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
	}
	
}
