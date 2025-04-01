package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class pageAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Utilisateur user; //l'utilisateur connecté avec son rôle

	public pageAccueil(Utilisateur user) {
		this.user = user; //initialiser l'utilisateur
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450,250,373,300);
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
		btnCommande.setBounds(96, 116, 164, 21);
		contentPane.add(btnCommande);
		
		JButton btnNewButton = new JButton("Liste des commandes");
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(user.getRole() == 2) {
					listeCmde listeCommande = new listeCmde(user);
					listeCommande.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
							"ERREUR", JOptionPane.ERROR_MESSAGE); 
				}
			}
		}); 
		btnNewButton.setBounds(96, 159, 164, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblAccueil = new JLabel("Page d'accueil");
		lblAccueil.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAccueil.setBounds(108, 26, 164, 21);
		contentPane.add(lblAccueil);
		
		JLabel lblUser = new JLabel("Bienvenue " + user.getLogin());
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(104, 71, 179, 13);
		contentPane.add(lblUser);
		
		JButton btnDeco = new JButton("Deconnexion");
		btnDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageConnexion pageConnexion = new pageConnexion();
				pageConnexion.setVisible(true);
				dispose();
			}
		});
		btnDeco.setBackground(new Color(128, 128, 255));
		btnDeco.setBounds(10, 232, 114, 21);
		contentPane.add(btnDeco);
	}
}
