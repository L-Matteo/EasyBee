package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class PageAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PageAccueil(Utilisateur user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370,250,660,390);
		setTitle("Page d'accueil");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCommande = new JButton("Passer Commande");
		btnCommande.setBackground(new Color(128, 128, 255));
		btnCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCommande.setBounds(177, 168, 287, 21);
		contentPane.add(btnCommande);
		
		JButton btnNewButton = new JButton("Préparation des commandes");
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(user.getRole() == 2) {
					ListeCmde listeCommande = new ListeCmde(user);
					listeCommande.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
							"ERREUR", JOptionPane.ERROR_MESSAGE); 
				}
			}
		}); 
		btnNewButton.setBounds(177, 231, 287, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblAccueil = new JLabel("Accueil");
		lblAccueil.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAccueil.setBounds(282, 49, 164, 21);
		contentPane.add(lblAccueil);
		
		JLabel lblUser = new JLabel("Bienvenue " + user.getLogin());
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(243, 101, 203, 13);
		contentPane.add(lblUser);
		
		JButton btnDeco = new JButton("Deconnexion");
		btnDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageConnexion pageConnexion = new PageConnexion();
				pageConnexion.setVisible(true);
				dispose();
			}
		});
		btnDeco.setBackground(new Color(128, 128, 255));
		btnDeco.setBounds(10, 322, 114, 21);
		contentPane.add(btnDeco);
		
		JButton btnSuiviCmde = new JButton("Suivi des commandes");
		btnSuiviCmde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getRole() == 1) {
					PageSuiviCmde suiviCmde = new PageSuiviCmde(user);
					suiviCmde.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
							"ERREUR", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		btnSuiviCmde.setBackground(new Color(128, 128, 255));
		btnSuiviCmde.setBounds(177, 199, 287, 21);
		contentPane.add(btnSuiviCmde);
	}
}
