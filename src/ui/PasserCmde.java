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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CommandeDAO;
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
		lblNomDuProduit.setBounds(24, 40, 105, 13);
		contentPane.add(lblNomDuProduit);

		JComboBox<String> produitCB = new JComboBox<String>();
		produitCB.setBounds(24, 60, 255, 22);
		contentPane.add(produitCB);

		ProduitDAO produitDAO = new ProduitDAO();
		List<String> produits = produitDAO.getProduitStoreStockBelowMinimum();
		produitCB.addItem("Sélectionnez un produit");

		for (String pdt : produits) {
			produitCB.addItem(pdt);
		}

		JLabel lblQntProduit = new JLabel("Quantité  :");
		lblQntProduit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQntProduit.setBounds(24, 113, 74, 13);
		contentPane.add(lblQntProduit);

		JTextField quantityTF = new JTextField();
		quantityTF.setBounds(24, 131, 255, 22);
		quantityTF.setText("0");
		contentPane.add(quantityTF);
		quantityTF.setColumns(10);

		JLabel lblQntProduitEntrepot = new JLabel("Stock Entrepot :");
		lblQntProduitEntrepot.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblQntProduitEntrepot.setBounds(24, 153, 255, 13);
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

		DefaultTableModel tableModel = new DefaultTableModel(new Object[] { "Produit", "Quantité" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 🔒 Aucune cellule n'est modifiable
			}
		};
		JTable tableProduits = new JTable(tableModel);

		JButton btnAjouterProduit = new JButton("Ajouter à la commande");
		btnAjouterProduit.setBackground(new Color(46, 204, 113));
		btnAjouterProduit.setForeground(Color.WHITE);
		btnAjouterProduit.setBounds(24, 177, 255, 22);
		contentPane.add(btnAjouterProduit);

		btnAjouterProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedProduit = (String) produitCB.getSelectedItem();
				if (selectedProduit == null || selectedProduit.equals("Sélectionnez un produit")) {
					JOptionPane.showMessageDialog(contentPane, "Veuillez choisir un produit.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				for (int i = 0; i < tableModel.getRowCount(); i++) {
					String produitExistant = (String) tableModel.getValueAt(i, 0);
					if (produitExistant.equals(selectedProduit)) {
						JOptionPane.showMessageDialog(contentPane, "Ce produit a déjà été ajouté à la commande.",
								"Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				int quantiteChoisi;
				try {
					quantiteChoisi = Integer.parseInt(quantityTF.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(contentPane, "Quantité invalide.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (quantiteChoisi <= 0) {
					JOptionPane.showMessageDialog(contentPane, "Veuillez saisir une quantité supérieure à 0.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				int quantiteEntrepot = produitDAO.getQuantiteByNomProduit(selectedProduit);
				if (quantiteChoisi > quantiteEntrepot) {
					JOptionPane.showMessageDialog(contentPane,
							"La quantité demandée est supérieure au stock disponible.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				tableModel.addRow(new Object[] { selectedProduit, quantiteChoisi });
			}
		});

		JButton btnSupprimerProduit = new JButton("Supprimer le produit sélectionné");
		btnSupprimerProduit.setBackground(new Color(231, 76, 60));
		btnSupprimerProduit.setForeground(Color.WHITE);
		btnSupprimerProduit.setBounds(360, 177, 255, 22);
		contentPane.add(btnSupprimerProduit);

		btnSupprimerProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableProduits.getSelectedRow();
				if (selectedRow != -1) {
					tableModel.removeRow(selectedRow);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Veuillez sélectionner un produit à supprimer.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tableProduits);
		scrollPane.setBounds(360, 40, 255, 126);
		contentPane.add(scrollPane);

		JButton validBtn = new JButton("Valider la commande");
		validBtn.setBackground(new Color(46, 204, 113));
		validBtn.setForeground(Color.WHITE);
		validBtn.setBounds(183, 282, 255, 22);
		contentPane.add(validBtn);

		validBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Veuillez ajouter au moins un produit.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				CommandeDAO cmdeDAO = new CommandeDAO();
				boolean success = true;

				for (int i = 0; i < tableModel.getRowCount(); i++) {
					String produit = (String) tableModel.getValueAt(i, 0);
					int quantite = (int) tableModel.getValueAt(i, 1);

					boolean created = cmdeDAO.createCommande(user.getRole(), produit, quantite);
					if (!created) {
						success = false;
						break;
					}
				}

				if (success) {
					JOptionPane.showMessageDialog(contentPane, "Commandes enregistrées avec succès.", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					new PageAccueil(user).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Une erreur s'est produite.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBounds(10, 322, 85, 21);
		contentPane.add(btnRetour);

		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageAccueil accueil = new PageAccueil(user);
				accueil.setVisible(true);
				dispose();
			}
		});

	}
}
