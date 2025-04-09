package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;

public class PageAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PageAccueil(Utilisateur user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370, 250, 660, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setTitle("Accueil - Gestion des stocks");
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton passerCmdBtn = new JButton("Passer Commande");
		passerCmdBtn.setBackground(new Color(128, 128, 255));
		passerCmdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getRole() == 1) {
					PasserCmde passerCmde = new PasserCmde(user);
					passerCmde.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité", "ERREUR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		passerCmdBtn.setBounds(177, 168, 287, 21);
		contentPane.add(passerCmdBtn);

		JButton listCmdBtn = new JButton("Liste des commandes");
		listCmdBtn.setBackground(new Color(128, 128, 255));
		listCmdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (user.getRole() == 2) {
					ListeCmde listeCommande = new ListeCmde(user);
					listeCommande.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité", "ERREUR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		listCmdBtn.setBounds(177, 207, 287, 21);
		contentPane.add(listCmdBtn);

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
	}
}
