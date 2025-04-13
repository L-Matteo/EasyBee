package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Commande;
import model.Produit;
import utils.ConnexionBdd;

public class CommandeDAO {

	ConnexionBdd cn = ConnexionBdd.getInstance();

	public boolean createCommande(int roleUser, String nomProduit, int quantity) {
		String query1 = "INSERT INTO cmdeapprodepot (idCatSalarie, nomCommande) VALUES (?, ?)";
		String query2 = "INSERT INTO detailproduit (idProduit, idCmdeApproDepot, qteCmde) VALUES (?, ?, ?)";

		ProduitDAO produitDAO = new ProduitDAO();

		try {
			Produit produit = produitDAO.getProduitByNom(nomProduit);
			if (produit == null) {
				System.out.println("Produit introuvable : " + nomProduit);
				return false;
			}

			int idProduit = produit.getIdProduit();

			PreparedStatement stmt1 = cn.laconnexion().prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			stmt1.setInt(1, roleUser);
			stmt1.setString(2, nomProduit);
			stmt1.executeUpdate();

			ResultSet generatedKeys = stmt1.getGeneratedKeys();
			if (generatedKeys.next()) {
				int idCmdeApproDepot = generatedKeys.getInt(1);

				PreparedStatement stmt2 = cn.laconnexion().prepareStatement(query2);
				stmt2.setInt(1, idProduit);
				stmt2.setInt(2, idCmdeApproDepot);
				stmt2.setInt(3, quantity);
				stmt2.executeUpdate();

				return true;
			} else {
				System.out.println("Échec de la récupération de l'ID de la commande.");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erreur lors de la création de la commande : " + e.getMessage());
			return false;
		}
	}

	public List<Commande> listeCommandeEnAttente() {
		List<Commande> cmdesEnAttente = new ArrayList<>();

		String query = "Select nomCommande from cmdeapprodepot where statutCommande like '%en attente%'";

		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Commande uneCmdeEnAttente = new Commande(rs.getString("nomCommande"), 0, "");
				cmdesEnAttente.add(uneCmdeEnAttente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmdesEnAttente;
	}

	public Commande afficherDetailsCmdeSelectione(String cmdeSelectionne) {
		String query = "select nomCommande, qtePrepa from detailcmd join cmdeapprodepot on idCmdeApproDepot = cmdeapprodepot.id where nomCommande = ?";

		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			stmt.setString(1, cmdeSelectionne);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Commande(rs.getString("nomCommande"), rs.getInt("qtePrepa"), "");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void changerStatutCommande(String nomCommande, String newStatut) {
		String query = "update cmdeapprodepot set statutCommande = ? where nomCommande = ?";

		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			stmt.setString(1, newStatut);
			stmt.setString(2, nomCommande);

			int rowUpdated = stmt.executeUpdate();
			if (rowUpdated > 0) {
				JOptionPane.showMessageDialog(null, "Vous avez finis de préparer la commande", "Succès", 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Commande> listeCommandeEnCoursDeLivraison() {
		List<Commande> lesCommandesEnCoursDeLivraison = new ArrayList<>();

		String query = "Select nomCommande, statutCommande from cmdeapprodepot where statutCommande like '%en cours de livraison%'";

		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Commande uneCommande = new Commande(rs.getString("nomCommande"), 0, rs.getString("statutCommande"));
				lesCommandesEnCoursDeLivraison.add(uneCommande);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesCommandesEnCoursDeLivraison;
	}
}