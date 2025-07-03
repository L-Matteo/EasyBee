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
	private JButton btnListeErreur;
	private Utilisateur user;

	public PageAccueil(Utilisateur user) {
		
		this.user = user; 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(440, 250, 1060, 620);
		setTitle("Accueil | Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("Bienvenue " + user.getLogin() + " !");
		lblUser.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblUser.setForeground(new Color(50, 50, 100));
		lblUser.setBounds(350, 55, 469, 35);
		contentPane.add(lblUser);
		
		JLabel lblVendeurs = new JLabel("Espace Vendeur");
		lblVendeurs.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblVendeurs.setForeground(Color.DARK_GRAY);
		lblVendeurs.setBounds(120, 170, 150, 25);
		contentPane.add(lblVendeurs);
		
		JLabel lblPreparateurs = new JLabel("Espace Préparateur");
		lblPreparateurs.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPreparateurs.setForeground(Color.DARK_GRAY);
		lblPreparateurs.setBounds(630, 170, 175, 25);
		contentPane.add(lblPreparateurs);
		
		btnCommande = new JButton("Passer commande d'approvisionnement");
		btnCommande.setBounds(120, 240, 430, 50);
		btnCommande.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCommande.setBackground(new Color(100, 150, 255));
		btnCommande.setForeground(Color.WHITE);
		btnCommande.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		contentPane.add(btnCommande);
		 
		btnSuiviCmde = new JButton("Suivi des commandes");
		btnSuiviCmde.setBounds(120, 320, 430, 50);
		btnSuiviCmde.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSuiviCmde.setBackground(new Color(100, 150, 255));
		btnSuiviCmde.setForeground(Color.WHITE);
		btnSuiviCmde.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		contentPane.add(btnSuiviCmde);
		
		btnPrepa = new JButton("Liste des commandes");
		btnPrepa.setBounds(630, 240, 285, 50);
		btnPrepa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnPrepa.setBackground(new Color(100, 150, 255));
		btnPrepa.setForeground(Color.WHITE);
		btnPrepa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		contentPane.add(btnPrepa);
		
		btnListeErreur = new JButton("Liste des erreurs");
		btnListeErreur.setBackground(new Color(100, 150, 255));
		btnListeErreur.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnListeErreur.setForeground(Color.WHITE);
		btnListeErreur.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnListeErreur.setBounds(630, 320, 285, 50);
		contentPane.add(btnListeErreur);
		
		btnDeco = new JButton("Déconnexion");
		btnDeco.setBackground(new Color(220, 80, 80));
		btnDeco.setForeground(Color.WHITE);
		btnDeco.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnDeco.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnDeco.setBounds(20, 500, 180, 50);
		contentPane.add(btnDeco);
		
	}
	
	public Utilisateur getUser() { return this.user; } 
	public JButton getBtnCommande() { return btnCommande; }
	public JButton getBtnSuiviCmde() { return btnSuiviCmde; }
	public JButton getBtnPrepa() { return btnPrepa; }
	public JButton getBtnListeErreur() { return this.btnListeErreur; }
	public JButton getBtnDeco() { return btnDeco; } 
}
