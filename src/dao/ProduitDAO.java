package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produit;
import utils.ConnexionBdd;

public class ProduitDAO {

	ConnexionBdd cn = ConnexionBdd.getInstance();

	public boolean createCommande(String statut, int roleUser, String nomProduit, int quantity) {
		String query1 = "INSERT INTO cmdeapprodepot (statutCommande, idCatSalarie, nomCommande) VALUES (?, ?, ?)";
		String query2 = "INSERT INTO detailproduit (idProduit, idCmdeApproDepot, qteCmde) VALUES (?, ?, ?)";

		try {
			Produit produit = this.getProduitByNom(nomProduit);
			if (produit == null) {
				System.out.println("Produit introuvable : " + nomProduit);
				return false;
			}

			int idProduit = produit.getIdProduit();

			PreparedStatement stmt1 = cn.laconnexion().prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			stmt1.setString(1, statut);
			stmt1.setInt(2, roleUser);
			stmt1.setString(3, nomProduit);
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

	public List<String> getProduitStoreStockBelowMinimum() {

		List<String> produits = new ArrayList<>();

		String query = "SELECT designationProduit FROM produit WHERE stockMag < stockMiniMag ORDER BY id";

		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				produits.add(rs.getString("designationProduit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}

	public int getQuantiteByNomProduit(String designationProduit) {

		String query = "SELECT stockEntrepot FROM produit WHERE designationProduit = ?";

		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			stmt.setString(1, designationProduit);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("stockEntrepot");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Produit getProduitByNom(String designationProduit) {

		String query = "SELECT id, codeProduit, stockMag, stockMiniMag, designationProduit, prixPdt, stockEntrepot FROM produit WHERE designationProduit = ?";

		try (PreparedStatement stmt = cn.laconnexion().prepareStatement(query)) {
			stmt.setString(1, designationProduit);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Produit(rs.getInt("id"), rs.getInt("codeProduit"), rs.getInt("stockMag"),
							rs.getInt("stockMiniMag"), rs.getString("designationProduit"), rs.getInt("prixPdt"),
							rs.getInt("stockEntrepot"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
