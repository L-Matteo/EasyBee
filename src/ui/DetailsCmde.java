package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CommandeDAO;
import model.Commande;
import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;

public class DetailsCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomProduit;
	private JTextField textFieldQtnPrepa;

	public DetailsCmde(Utilisateur user, String cmdeSlectionne) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(570, 250, 700, 420);
		setTitle("Détails de la commande en attente - Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Détails de la commande");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(241, 39, 230, 25);
		contentPane.add(lblTitle);
		
		JLabel lblNomProduit = new JLabel("Nom du produit : ");
		lblNomProduit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNomProduit.setBounds(200, 89, 158, 13);
		contentPane.add(lblNomProduit);
		
		textFieldNomProduit = new JTextField();
		textFieldNomProduit.setEditable(false);
		textFieldNomProduit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		textFieldNomProduit.setBounds(200, 112, 284, 25);
		contentPane.add(textFieldNomProduit);
		textFieldNomProduit.setColumns(10);
		
		JLabel lblQteDemande = new JLabel("Quantité demandée : ");
		lblQteDemande.setBounds(200, 147, 226, 25);
		lblQteDemande.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		contentPane.add(lblQteDemande);
		
		JLabel lblQntPrepa = new JLabel("Quantité préparée:");
		lblQntPrepa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblQntPrepa.setBounds(200, 182, 192, 13);
		contentPane.add(lblQntPrepa);
		
		textFieldQtnPrepa = new JTextField();
		textFieldQtnPrepa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		textFieldQtnPrepa.setBounds(200, 205, 284, 25);
		contentPane.add(textFieldQtnPrepa);
		textFieldQtnPrepa.setColumns(10);
		
		JCheckBox chckbxStatus = new JCheckBox("Préparation de la commande terminée");
		chckbxStatus.setBounds(200, 250, 249, 21);
		chckbxStatus.setBackground(new Color(240, 245, 255));
		contentPane.add(chckbxStatus);
		
		CommandeDAO cmdeDAO = new CommandeDAO();
		Commande commande = cmdeDAO.afficherDetailsCmdeSelectione(cmdeSlectionne);
		
		if(commande != null) {
			textFieldNomProduit.setText(commande.getNom());
			lblQteDemande.setText("Quantité demandée : " + String.valueOf(commande.getQte()));
		} else {
			JOptionPane.showMessageDialog(this, "Impossible de trouver la commande", "ERREUR", JOptionPane.ERROR_MESSAGE);
			ListeCmde listeCmde = new ListeCmde(user);
			listeCmde.setVisible(true);
			dispose();
		}
		
		int id = cmdeDAO.selectIdCmde(cmdeSlectionne);
		
		JButton btnTerminer = new JButton("Terminer"); // modifier la requête pour sauvegarder la quantité préparée
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxStatus.isSelected()) {
					try {
						int qtePrepa = Integer.parseInt(textFieldQtnPrepa.getText());
						cmdeDAO.updateQtePrepa(qtePrepa,id);
					} catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(contentPane, "La valeur du champ \"Quantité préparée\" n'est pas valable.", "ERREUR", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					cmdeDAO.changerStatutCommande(cmdeSlectionne, "en cours de livraison");
					JOptionPane.showMessageDialog(contentPane, "Le statut de la commande a été changé.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					ListeCmde listeCmde = new ListeCmde(user);
					listeCmde.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "La commande est toujours en cours de préparation", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTerminer.setBackground(new Color(46, 204, 113));
		btnTerminer.setBounds(333, 309, 126, 37);
		btnTerminer.setForeground(Color.WHITE);
		btnTerminer.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnTerminer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		contentPane.add(btnTerminer);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListeCmde listeCmde = new ListeCmde(user);
				listeCmde.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(223, 309, 93, 37);
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		contentPane.add(btnRetour);
		
	}
}
