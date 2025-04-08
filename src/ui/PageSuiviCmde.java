package ui;

import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CommandeDAO;
import model.Commande;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class PageSuiviCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public PageSuiviCmde() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370,250,660,390);
		setTitle("Suivi des commandes");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSuiviCmde = new JLabel("Suivi des commandes");
		lblSuiviCmde.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSuiviCmde.setBounds(225, 38, 200, 22);
		contentPane.add(lblSuiviCmde);
		
		CommandeDAO commandeDAO = new CommandeDAO();
        List<Commande> commandes = commandeDAO.listeCommandeEnCoursDeLivraison();
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nom commande");
		model.addColumn("Statut commande");
		
		for(Commande uneCommande : commandes) {
			model.addRow(new Object[] {uneCommande.getNom(), uneCommande.getStatut()});
		}

		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(117, 96, 404, 170); 
		contentPane.add(scrollPane);

		
	}
}
