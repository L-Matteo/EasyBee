package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ReportProblem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextArea description;
	private JButton btnRetour;
	private JButton btnValider;
	Utilisateur user;
	String cmdeSelectionne;

	public ReportProblem(Utilisateur user, String cmdeSelectionne) {
		
		this.user = user;
		this.cmdeSelectionne = cmdeSelectionne;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(570, 250, 700, 420);
		setTitle("Signaler une erreur | Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Signaler une erreur dans la commande");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(172, 27, 351, 36);
		contentPane.add(lblTitle);
		
		JLabel lblDescription = new JLabel("Description de l'erreur : ");
		lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDescription.setBounds(172, 93, 164, 13);
		contentPane.add(lblDescription);
		
		description = new JTextArea();
		description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		description.setBounds(172, 116, 324, 154);
		contentPane.add(description); 
		
		btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnRetour.setBounds(189, 309, 113, 37);
		contentPane.add(btnRetour);
		
		btnValider = new JButton("Valider");
		btnValider.setBackground(new Color(46, 204, 113));
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnValider.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnValider.setBounds(322, 309, 144, 37);
		contentPane.add(btnValider);

	}
	
	public JButton getBtnRetour() { return this.btnRetour; }
	public JButton getBtnValider() { return this.btnValider; }
	public JTextArea getDescription() { return this.description; }
	public String getCmdeSelectionne() { return this.cmdeSelectionne; }
}
