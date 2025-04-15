package ui;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CommandeDAO;
import model.Commande;
import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class PageSuiviCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PageSuiviCmde(Utilisateur user) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370,250,660,390);
		setTitle("Suivi des commandes");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSuiviCmde = new JLabel("Suivi des commandes");
		lblSuiviCmde.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSuiviCmde.setBounds(225, 38, 200, 22);
		contentPane.add(lblSuiviCmde);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(193, 124, 255, 21);
		contentPane.add(comboBox);
		
		comboBox.addItem("");
		
		CommandeDAO commandeDAO = new CommandeDAO();
        List<Commande> commandes = commandeDAO.listeCommandeEnCoursDeLivraison();
		
		for(Commande uneCommande : commandes) {
			comboBox.addItem(uneCommande.getNom());
		}
		
		JCheckBox chckbxNewStatut = new JCheckBox("La commande a bien été livrée");
		chckbxNewStatut.setBounds(193, 183, 245, 21);
		contentPane.add(chckbxNewStatut);
		
		JButton btnTermine = new JButton("Terminé");
		btnTermine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				if(comboBox.getSelectedItem() != "") {
					if(chckbxNewStatut.isSelected()) {
						String cmdeSelectionne = (String)comboBox.getSelectedItem();
						commandeDAO.changerStatutCommande(cmdeSelectionne, "livrée");
						JOptionPane.showMessageDialog(contentPane, "Le statut de la commande a été changé", "Succès",0);
						PageSuiviCmde suiviCmde = new PageSuiviCmde(user);
						suiviCmde.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(contentPane, "La commande est toujours en cours de livraison", "ERREUR", JOptionPane.ERROR_MESSAGE);
					} 
				} else {
					JOptionPane.showMessageDialog(contentPane, "Erreur dans la sélection de la commande", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTermine.setBounds(193, 260, 255, 21);
		btnTermine.setBackground(new Color(128, 128, 255));
		contentPane.add(btnTermine);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageAccueil accueil = new PageAccueil(user);
				accueil.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(10, 322, 85, 21);
		btnRetour.setBackground(new Color(128, 128, 255));
		contentPane.add(btnRetour);
		
	}
}
