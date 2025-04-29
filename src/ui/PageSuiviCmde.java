package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class PageSuiviCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox; 
	private JCheckBox chckbxNewStatut;
	private JButton btnTermine;
	private JButton btnRetour;

	public PageSuiviCmde(Utilisateur user) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(570, 250, 700, 420);
		setTitle("Suivi des commandes - Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Suivi des commandes en cours de livraison");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(157, 34, 418, 22);
		contentPane.add(lblTitle);
		
		JLabel lblSelectCmd = new JLabel("Commandes en cours de livraison :");
		lblSelectCmd.setBounds(188, 86, 276, 13);
		lblSelectCmd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		contentPane.add(lblSelectCmd);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(188, 118, 300, 31);
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		contentPane.add(comboBox);
		comboBox.addItem("— Sélectionnez une commande —");
        
		chckbxNewStatut = new JCheckBox("La commande a bien été livrée");
		chckbxNewStatut.setBounds(188, 171, 245, 21);
		chckbxNewStatut.setBackground(new Color(240, 245, 255));
		contentPane.add(chckbxNewStatut);
		
		btnTermine = new JButton("Terminé");
		btnTermine.setBackground(new Color(46, 204, 113));
		btnTermine.setForeground(Color.WHITE);
		btnTermine.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnTermine.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnTermine.setBounds(282, 219, 120, 37);
		contentPane.add(btnTermine);
		
		btnRetour = new JButton("Retour");
		btnRetour.setBounds(10, 322, 120, 37);
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		contentPane.add(btnRetour);
		
	}
	
	public JComboBox<String> getComboBox() { return this.comboBox; } 
	public JCheckBox getCheckBox() { return this.chckbxNewStatut; } 
	public JButton getBtnTermine() { return this.btnTermine; }
	public JButton getBtnRetour() { return this.btnRetour; } 
}
