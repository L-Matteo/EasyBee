package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;

public class PageAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCommande;
	private JButton btnSuiviCmde;
	private JButton btnPrepa;
	private JButton btnDeco; 
	private Utilisateur user;

	public PageAccueil(Utilisateur user) {
		
		this.user = user; 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(570, 250, 700, 420);
		setTitle("Accueil - Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("Bienvenue " + user.getLogin() + " !");
		lblUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblUser.setForeground(new Color(50, 50, 100));
		lblUser.setBounds(223, 39, 347, 30);
		contentPane.add(lblUser);
		
		JLabel lblVendeurs = new JLabel("Espace Vendeur");
		lblVendeurs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVendeurs.setForeground(Color.DARK_GRAY);
		lblVendeurs.setBounds(76, 120, 131, 21);
		contentPane.add(lblVendeurs);
		
		JLabel lblPreparateurs = new JLabel("Espace Préparateur");
		lblPreparateurs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPreparateurs.setForeground(Color.DARK_GRAY);
		lblPreparateurs.setBounds(406, 120, 183, 21);
		contentPane.add(lblPreparateurs);
		
		btnCommande = new JButton("Passer Commande d'approvisionnement");
		btnCommande.setBounds(76, 164, 291, 37);
		btnCommande.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCommande.setBackground(new Color(100, 150, 255));
		btnCommande.setForeground(Color.WHITE);
		btnCommande.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		contentPane.add(btnCommande);
		 
		btnSuiviCmde = new JButton("Suivi des commandes");
		btnSuiviCmde.setBounds(76, 227, 291, 37);
		btnSuiviCmde.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSuiviCmde.setBackground(new Color(100, 150, 255));
		btnSuiviCmde.setForeground(Color.WHITE);
		btnSuiviCmde.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		contentPane.add(btnSuiviCmde);
		
		btnPrepa = new JButton("Liste des commandes");
		btnPrepa.setBounds(406, 164, 196, 37);
		btnPrepa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnPrepa.setBackground(new Color(100, 150, 255));
		btnPrepa.setForeground(Color.WHITE);
		btnPrepa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		contentPane.add(btnPrepa);
		
		btnDeco = new JButton("Déconnexion");
		btnDeco.setBackground(new Color(220, 80, 80));
		btnDeco.setForeground(Color.WHITE);
		btnDeco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDeco.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnDeco.setBounds(10, 322, 120, 37);
		contentPane.add(btnDeco);
	}
	
	public Utilisateur getUser() { return this.user; } 
	public JButton getBtnCommande() { return btnCommande; }
	public JButton getBtnSuiviCmde() { return btnSuiviCmde; }
	public JButton getBtnPrepa() { return btnPrepa; }
	public JButton getBtnDeco() { return btnDeco; } 

}
