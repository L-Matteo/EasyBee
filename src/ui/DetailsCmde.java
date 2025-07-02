package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class DetailsCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox chckbxStatus; 
	private JButton btnTerminer;
	private JButton btnRetour; 
	private JTable table;
	private DefaultTableModel model; 

	public DetailsCmde(Utilisateur user, String cmdeSlectionne) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(440, 250, 1060, 620);
		setTitle("Détails commande | Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Détails de la commande");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(50, 50, 100));
		lblTitle.setBounds(365, 35, 560, 45);
		contentPane.add(lblTitle);
		
		chckbxStatus = new JCheckBox("Préparation de la commande terminée");
		chckbxStatus.setBounds(270, 370, 350, 21);
		chckbxStatus.setBackground(new Color(240, 245, 255));
		chckbxStatus.setFont(new Font("Segoe UI", Font.PLAIN, 18)); 
		contentPane.add(chckbxStatus);
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.setBackground(new Color(46, 204, 113));
		btnTerminer.setBounds(270, 420, 475, 50);
		btnTerminer.setForeground(Color.WHITE);
		btnTerminer.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnTerminer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		contentPane.add(btnTerminer);
		
		btnRetour = new JButton("Retour");
		btnRetour.setBounds(20, 500, 180, 50);
		btnRetour.setBackground(new Color(52, 152, 219));
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		contentPane.add(btnRetour);
		
		model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Nom");
		model.addColumn("Quantité");
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(65);  
		table.getColumnModel().getColumn(1).setPreferredWidth(185); 
		table.getColumnModel().getColumn(2).setPreferredWidth(65);  
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setRowHeight(31);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(270, 115, 475, 230);
		contentPane.add(scrollPane);
	
	}
	
	public JCheckBox getCheckBox() { return this.chckbxStatus; } 
	public JButton getBtnTerminer() { return this.btnTerminer; }
	public JButton getBtnRetour() { return this.btnRetour; } 
	public DefaultTableModel getModel() { return this.model; }
	public JTable getTable() { return this.table; }
}
