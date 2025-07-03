package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PageSuiviCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox; 
	private JButton btnTermine;
	private JButton btnRetour;
	private JButton btnProb;
	private DefaultTableModel model;

	public PageSuiviCmde(Utilisateur user) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(440, 250, 1060, 620);
		setTitle("Suivi des commandes | Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Suivi des commandes en cours de livraison");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(285, 35, 560, 45);
		contentPane.add(lblTitle);
		
		JLabel lblSelectCmd = new JLabel("Commandes en cours de livraison :");
		lblSelectCmd.setBounds(85, 180, 350, 18);
		lblSelectCmd.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		contentPane.add(lblSelectCmd);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(85, 240, 437, 31);
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contentPane.add(comboBox);
		comboBox.addItem("— Sélectionnez une commande —");
		
		btnTermine = new JButton("Tout est ok");
		btnTermine.setBackground(new Color(46, 204, 113));
		btnTermine.setForeground(Color.WHITE);
		btnTermine.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnTermine.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnTermine.setBounds(85, 310, 170, 50);
		contentPane.add(btnTermine);
		
		btnRetour = new JButton("Retour");
		btnRetour.setBounds(20, 500, 180, 50);
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		contentPane.add(btnRetour);
		
		btnProb = new JButton("Signaler un problème");
		btnProb.setBackground(new Color(220, 80, 80));
		btnProb.setForeground(Color.WHITE);
		btnProb.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnProb.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnProb.setBounds(275, 310, 250, 50);
		contentPane.add(btnProb);
		
		model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Nom");
		model.addColumn("Quantité commandée");
		
		JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);  
		table.getColumnModel().getColumn(1).setPreferredWidth(185); 
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setRowHeight(31);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(570, 140, 430, 250);
		contentPane.add(scrollPane);
		
	}
	
	public JComboBox<String> getComboBox() { return this.comboBox; } 
	public JButton getBtnTermine() { return this.btnTermine; }
	public JButton getBtnRetour() { return this.btnRetour; }
	public JButton getBtnProb() { return this.btnProb; }
	public DefaultTableModel getModel() { return this.model; }
} 