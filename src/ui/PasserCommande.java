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
		setBounds(570, 250, 765, 465);
		setTitle("Passer une commande - Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Nouvelle commande d'approvisionnement");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(202, 20, 366, 25);
		contentPane.add(lblTitle);
		
		JLabel lblNomProduit = new JLabel("Nom du produit :");
		lblNomProduit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNomProduit.setBounds(88, 87, 158, 20);
		contentPane.add(lblNomProduit);
		
		comboBoxProduit = new JComboBox<>();
		comboBoxProduit.setBounds(88, 117, 234, 31);
		comboBoxProduit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		contentPane.add(comboBoxProduit);
		comboBoxProduit.addItem(new Produit(0,0,"— Sélectionnez un produit —",0,0));
		
		lblStock = new JLabel("Stock Entrepôt :");
		lblStock.setBounds(88, 165, 226, 25);
		lblStock.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		contentPane.add(lblStock);
		
		JLabel lblQte = new JLabel("Quantité :");
		lblQte.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblQte.setBounds(88, 205, 158, 20);
		contentPane.add(lblQte);
		
		textFieldQte = new JTextField();
		textFieldQte.setFont(new Font("Segoe UI", Font.BOLD, 14));
		textFieldQte.setBounds(88, 238, 234, 25);
		contentPane.add(textFieldQte);
		textFieldQte.setColumns(10);
		
		btnAddProduit = new JButton("Ajouter à la commande");
		btnAddProduit.setBackground(new Color(46, 204, 113));
		btnAddProduit.setForeground(Color.WHITE);
		btnAddProduit.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAddProduit.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnAddProduit.setBounds(88, 292, 234, 37);
		contentPane.add(btnAddProduit);
		
		btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnRetour.setBounds(219, 360, 93, 37);
		contentPane.add(btnRetour);
		
		btnValider = new JButton("Valider la commande");
		btnValider.setBackground(new Color(100, 150, 255));
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnValider.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnValider.setBounds(337, 360, 189, 37);
		contentPane.add(btnValider);
		
		model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Nom");
		model.addColumn("Quantité");
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);  
		table.getColumnModel().getColumn(1).setPreferredWidth(145); 
		table.getColumnModel().getColumn(2).setPreferredWidth(60);  
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setRowHeight(21);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(378, 92, 322, 183);
		contentPane.add(scrollPane);
		
		btnSupProduit = new JButton("Supprimer le produit");
		btnSupProduit.setBackground(new Color(220, 80, 80));
		btnSupProduit.setForeground(Color.WHITE);
		btnSupProduit.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSupProduit.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		btnSupProduit.setBounds(378, 292, 322, 37);
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
