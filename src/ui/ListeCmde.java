package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class ListeCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxListCmde;
	private JButton btnNext;
	private JButton btnRetour;

	public ListeCmde(Utilisateur user) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(570, 250, 700, 420);
		setTitle("Liste des commandes en attente - Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Sélectionner une commande à préparer");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(181, 36, 347, 31);
		contentPane.add(lblTitle);
		
		JLabel lblSelectCmd = new JLabel("Commandes en attente :");
		lblSelectCmd.setBounds(270, 91, 196, 13);
		lblSelectCmd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		contentPane.add(lblSelectCmd);
		
		comboBoxListCmde = new JComboBox<>();
		comboBoxListCmde.setBounds(196, 131, 300, 31);
		comboBoxListCmde.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		contentPane.add(comboBoxListCmde);
		comboBoxListCmde.addItem("— Sélectionnez une commande —");
		
		btnNext = new JButton("Suivant");
		btnNext.setBackground(new Color(46, 204, 113));
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNext.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnNext.setBounds(284, 191, 120, 37);
		contentPane.add(btnNext);
		
		btnRetour = new JButton("Retour");
		btnRetour.setBounds(10, 322, 120, 37);
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		contentPane.add(btnRetour);
	}
	
	public JComboBox<String> getComboBox() { return this.comboBoxListCmde; } 
	public JButton getBtnNext() { return this.btnNext; } 
	public JButton getBtnRetour() { return this.btnRetour; }

}
