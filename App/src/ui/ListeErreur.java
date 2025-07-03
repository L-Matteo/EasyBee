package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Utilisateur;

public class ListeErreur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Utilisateur user;
	private JLabel labelTitle;
	private DefaultTableModel model;
	private JButton btnRetour;
	private JButton btnSprError;
	private JTable table;

	public ListeErreur(Utilisateur user) {
		
		this.user = user;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(440, 250, 1060, 620);
		setTitle("Liste des erreurs | Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTitle = new JLabel("Suivi des commandes en erreurs");
		labelTitle.setBounds(325, 35, 560, 45);
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		labelTitle.setForeground(new Color(50,50,100));
		contentPane.add(labelTitle);
		
		model = new DefaultTableModel();
		model.addColumn("Nom commande");
		model.addColumn("Description erreur");
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table.setRowHeight(31);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(295, 120, 450, 220);
		contentPane.add(scrollPane);
		
		btnRetour = new JButton("Retour");
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
		btnRetour.setBounds(20, 500, 180, 50);
		contentPane.add(btnRetour);
		
		btnSprError = new JButton("Supprimer l'erreur");
		btnSprError.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSprError.setForeground(Color.WHITE);
		btnSprError.setBackground(new Color(220, 80, 80));
		btnSprError.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
		btnSprError.setBounds(295, 370, 450, 50);
		contentPane.add(btnSprError); 
	}
	
	public Utilisateur getUser() { return this.user; }
	public DefaultTableModel getModel() { return this.model; }
	public JTable getTable() { return this.table; }
	public JButton getBtnRetour() { return this.btnRetour; }
	public JButton getBtnSprError() { return this.btnSprError; }

}
