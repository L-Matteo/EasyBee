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
	private JButton btnValider;

	public ListeErreur(Utilisateur user) {
		
		this.user = user;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(570, 250, 700, 420);
		setTitle("Liste des erreurs | Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTitle = new JLabel("Suivi des commandes en erreur");
		labelTitle.setBounds(201,30,293,20);
		labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		labelTitle.setForeground(new Color(50,50,100));
		contentPane.add(labelTitle);
		
		model = new DefaultTableModel();
		model.addColumn("Nom commande");
		model.addColumn("Description erreur");
		
		JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(157, 81, 360, 180);
		contentPane.add(scrollPane);
		
		btnRetour = new JButton("Retour");
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
		btnRetour.setBounds(181,307,120,37);
		contentPane.add(btnRetour);
		
		btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnValider.setForeground(Color.WHITE);
		btnValider.setBackground(new Color(46,204,113));
		btnValider.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
		btnValider.setBounds(322, 307, 170, 37);
		contentPane.add(btnValider); 
	}
	
	public Utilisateur getUser() { return this.user; }
	public DefaultTableModel getModel() { return this.model; }
	public JButton getBtnRetour() { return this.btnRetour; }
	public JButton getBtnValider() { return this.btnValider; }

}
