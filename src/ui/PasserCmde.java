package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.ProduitDAO;
import model.Utilisateur;

public class PasserCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PasserCmde(Utilisateur user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370, 250, 660, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setTitle("Passer une commande d'approvisionnement - Gestion des stocks");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomDuProduit = new JLabel("Nom du produit :");
		lblNomDuProduit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNomDuProduit.setBounds(190, 52, 105, 13);
		contentPane.add(lblNomDuProduit);

		JComboBox<String> produitCB = new JComboBox<String>();
		produitCB.setBounds(190, 72, 255, 22);
		contentPane.add(produitCB);

		ProduitDAO produitDAO = new ProduitDAO();
		List<String> produits = produitDAO.getProduitStoreStockBelowMinimum();
		produitCB.addItem("Sélectionnez un produit");

		for (String pdt : produits) {
			produitCB.addItem(pdt);
		}

		JLabel lblQntProduit = new JLabel("Quantité  :");
		lblQntProduit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQntProduit.setBounds(190, 148, 74, 13);
		contentPane.add(lblQntProduit);

		JTextField quantityTF = new JTextField();
		quantityTF.setBounds(190, 166, 255, 22);
		quantityTF.setText("0");
		contentPane.add(quantityTF);
		quantityTF.setColumns(10);

		JLabel lblQntProduitEntrepot = new JLabel("Stock Entrepot :");
		lblQntProduitEntrepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblQntProduitEntrepot.setBounds(190, 188, 255, 13);
		contentPane.add(lblQntProduitEntrepot);

		produitCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedProduit = (String) produitCB.getSelectedItem();
				if (selectedProduit != null) {
					int quantite = produitDAO.getQuantiteByNomProduit(selectedProduit);
					lblQntProduitEntrepot.setText(String.format("Stock Entrepôt : %d", quantite));
				}
			}
		});

		JButton validBtn = new JButton("Valider");
		validBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedProduit = (String) produitCB.getSelectedItem();
				int quantiteChoisi = Integer.parseInt(quantityTF.getText());

				int quantiteEntrepot = produitDAO.getQuantiteByNomProduit(selectedProduit);

				if (produits.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Aucun produit n'a besoin d'être réapprovisionné.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (selectedProduit == null || selectedProduit.equals("Sélectionnez un produit")) {
					JOptionPane.showMessageDialog(contentPane, "Veuillez choisir un produit.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (quantiteChoisi == 0) {
					JOptionPane.showMessageDialog(contentPane, "Veuillez saisir une quantité.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (quantiteChoisi > quantiteEntrepot) {
					JOptionPane.showMessageDialog(contentPane,
							"La quantité demandée est supérieure au stock disponible. Veuillez ajuster votre saisie.",
							"Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}

				ProduitDAO produitDAO = new ProduitDAO();
				boolean isCommandeCreated = produitDAO.createCommande("en attente", user.getRole(), selectedProduit,
						quantiteChoisi);

				if (isCommandeCreated) {
					JOptionPane.showMessageDialog(contentPane, "Commande effectuée, en attente de confirmation",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					PageAccueil accueil = new PageAccueil(user);
					accueil.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Une erreur s'est produite lors de la création de la commande.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

				System.out.println("Produit : " + selectedProduit + " Quantité : " + quantiteChoisi);
			}
		});
		validBtn.setBackground(new Color(128, 128, 255));
		validBtn.setBounds(190, 258, 255, 22);
		contentPane.add(validBtn);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(128, 128, 255));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageAccueil accueil = new PageAccueil(user);
				accueil.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(10, 322, 85, 21);
		contentPane.add(btnRetour);

	}
}
