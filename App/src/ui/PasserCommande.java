package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Produit;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;

public class PasserCommande extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JComboBox<Produit> comboBoxProduit;
	private JTextField textFieldQte;
	private JButton btnAddProduit;
	private JButton btnRetour;
	private JButton btnValider;
	private DefaultTableModel model;
	private JButton btnSupProduit;
	private JTable table;
	private JLabel lblStock;

	public PasserCommande() {
		UIManager.put("Button.disabledText", Color.WHITE); // Obliger le bouton désactiver à avoir un text de couleur blanc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(440, 250, 1060, 620);
		setTitle("Passer une commande | Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Nouvelle commande d'approvisionnement");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(285, 35, 560, 45);
		contentPane.add(lblTitle);
		
		JLabel lblNomProduit = new JLabel("Nom du produit :");
		lblNomProduit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNomProduit.setBounds(120, 129, 248, 30);
		contentPane.add(lblNomProduit);
		
		comboBoxProduit = new JComboBox<>();
		comboBoxProduit.setBounds(120, 176, 305, 31);
		comboBoxProduit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contentPane.add(comboBoxProduit);
		comboBoxProduit.addItem(new Produit(0,0,"— Sélectionnez un produit —",0,0));
		
		lblStock = new JLabel("Stock Entrepôt :");
		lblStock.setBounds(120, 228, 226, 25);
		lblStock.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		contentPane.add(lblStock);
		
		JLabel lblQte = new JLabel("Quantité :");
		lblQte.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblQte.setBounds(120, 268, 158, 30);
		contentPane.add(lblQte);
		
		textFieldQte = new JTextField();
		textFieldQte.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textFieldQte.setBounds(120, 315, 305, 35);
		contentPane.add(textFieldQte);
		textFieldQte.setColumns(10);
		
		btnAddProduit = new JButton("Ajouter à la commande");
		btnAddProduit.setBackground(new Color(46, 204, 113));
		btnAddProduit.setForeground(Color.WHITE);
		btnAddProduit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAddProduit.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnAddProduit.setBounds(120, 375, 305, 50);
		contentPane.add(btnAddProduit);
		
		btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnRetour.setBounds(300, 482, 180, 50);
		contentPane.add(btnRetour);
		
		btnValider = new JButton("Valider la commande");
		btnValider.setBackground(new Color(100, 150, 255));
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnValider.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnValider.setBounds(517, 482, 250, 50);
		contentPane.add(btnValider);
		
		model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Nom");
		model.addColumn("Quantité");
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(50); 
		table.getColumnModel().getColumn(1).setPreferredWidth(175); 
		table.getColumnModel().getColumn(2).setPreferredWidth(40);  
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setRowHeight(31);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(541, 129, 432, 221);
		contentPane.add(scrollPane);
		
		btnSupProduit = new JButton("Supprimer le produit");
		btnSupProduit.setBackground(new Color(220, 80, 80));
		btnSupProduit.setForeground(Color.WHITE);
		btnSupProduit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSupProduit.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnSupProduit.setBounds(541, 375, 432, 50);
		contentPane.add(btnSupProduit);
	}
	
	public JComboBox<Produit> getComboBox() { return this.comboBoxProduit; }
	public JTextField getTextFieldQte() { return this.textFieldQte; }
	public JButton getBtnAddProduit() { return this.btnAddProduit; }
	public JButton getBtnRetour() { return this.btnRetour; }
	public JButton getBtnValider() { return this.btnValider; } 
	public DefaultTableModel getModel() { return this.model; }
	public JTable getTable() { return this.table; }
	public JButton getBtnSuprProduit() { return this.btnSupProduit; }
	public JLabel getLabelStock() { return this.lblStock; }
	
} 
