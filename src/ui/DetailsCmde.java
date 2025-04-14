package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.CommandeDAO;
import dao.ProduitDAO;
import model.Commande;
import model.Utilisateur;

public class DetailsCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int qteProduitEntrepot;
	private Commande commande;

	public DetailsCmde(Utilisateur user, String cmdeSelectionne) {
		setTitle("Détails de la commande en attente - Gestion des stocks");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370, 250, 700, 420);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(20, 20));
		contentPane.setBackground(new Color(245, 250, 255));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		// === Titre ===
		JLabel lblTitre = new JLabel("📦 Détails de la commande en attente");
		lblTitre.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitre, BorderLayout.NORTH);

		// === Centre ===
		JPanel centrePanel = new JPanel();
		centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
		centrePanel.setOpaque(false);
		contentPane.add(centrePanel, BorderLayout.CENTER);

		// Nom du produit
		JLabel lblNomProduit = new JLabel("Nom du produit :");
		lblNomProduit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNomProduit.setAlignmentX(Component.CENTER_ALIGNMENT);
		centrePanel.add(lblNomProduit);

		JTextField textFieldNomProduit = new JTextField();
		textFieldNomProduit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldNomProduit.setMaximumSize(new Dimension(300, 30));
		textFieldNomProduit.setEditable(false);
		centrePanel.add(textFieldNomProduit);

		// Quantité demandée
		JLabel lblQntDemandee = new JLabel("Quantité demandée :");
		lblQntDemandee.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblQntDemandee.setAlignmentX(Component.CENTER_ALIGNMENT);
		centrePanel.add(lblQntDemandee);

		// Quantité en stock
		JLabel lblQntProduitEntrepot = new JLabel("Stock Entrepôt :");
		lblQntProduitEntrepot.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblQntProduitEntrepot.setAlignmentX(Component.CENTER_ALIGNMENT);
		centrePanel.add(lblQntProduitEntrepot);

		// Quantité préparée
		JLabel lblQntProduit = new JLabel("Quantité préparée :");
		lblQntProduit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblQntProduit.setAlignmentX(Component.CENTER_ALIGNMENT);
		centrePanel.add(lblQntProduit);

		JTextField textFieldQtnProduit = new JTextField();
		textFieldQtnProduit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldQtnProduit.setMaximumSize(new Dimension(300, 30));
		centrePanel.add(textFieldQtnProduit);

		// === Récupération des données ===
		CommandeDAO cmdeDAO = new CommandeDAO();
		commande = cmdeDAO.afficherDetailsCmdeSelectione(cmdeSelectionne);

		if (commande != null) {
			textFieldNomProduit.setText(commande.getNom());
			lblQntDemandee.setText(String.format("Quantité demandée : %d", commande.getQte()));

			ProduitDAO produitDAO = new ProduitDAO();
			qteProduitEntrepot = produitDAO.getQuantiteByNomProduit(commande.getNom());
			lblQntProduitEntrepot.setText(String.format("Stock Entrepôt : %d", qteProduitEntrepot));
		} else {
			JOptionPane.showMessageDialog(this, "Impossible de trouver la commande", "ERREUR",
					JOptionPane.ERROR_MESSAGE);
			new ListeCmde(user).setVisible(true);
			dispose();
			return;
		}

		// === Boutons ===
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setOpaque(false);

		JButton btnRetour = createStyledButton("Retour", new Color(52, 152, 219));
		buttonPanel.add(btnRetour);

		JButton btnTerminer = createStyledButton("Terminer", new Color(46, 204, 113));
		buttonPanel.add(btnTerminer);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		// === Actions ===
		btnRetour.addActionListener(e -> {
			new ListeCmde(user).setVisible(true);
			dispose();
		});

		btnTerminer.addActionListener(e -> {
			try {
				int qtePrepa = Integer.parseInt(textFieldQtnProduit.getText());

				if (qtePrepa < 0) {
					JOptionPane.showMessageDialog(contentPane, "Quantité invalide (négative).", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (qtePrepa > qteProduitEntrepot) {
					JOptionPane.showMessageDialog(contentPane, "Quantité supérieure au stock disponible.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (qtePrepa > commande.getQte()) {
					JOptionPane.showMessageDialog(contentPane, "Quantité supérieure à la quantité demandée.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (qtePrepa == 0) {
					int confirm = JOptionPane.showConfirmDialog(contentPane,
							"La quantité préparée est de 0. Voulez-vous vraiment continuer ?", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (confirm != JOptionPane.YES_OPTION) {
						return;
					}
				}

				boolean success = cmdeDAO.saveDetailsCmde(commande, qtePrepa);
				if (success) {
					JOptionPane.showMessageDialog(contentPane, "Quantité enregistrée avec succès.", "Succès",
							JOptionPane.INFORMATION_MESSAGE);
					new ListeCmde(user).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Échec de l'enregistrement.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(contentPane, "Veuillez entrer une quantité valide.", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private JButton createStyledButton(String text, Color bgColor) {
		JButton btn = new JButton(text);
		btn.setBackground(bgColor);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setFocusPainted(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		return btn;
	}
}
