package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.CommandeDAO;
import model.Commande;
import model.Utilisateur;

public class PageSuiviCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JTextField filterField;
	private TableRowSorter<DefaultTableModel> sorter;

	public PageSuiviCmde(Utilisateur user) {
		setTitle("Suivi des commandes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370, 250, 720, 450);
		setResizable(false);

		contentPane = new JPanel(new BorderLayout(15, 15));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(new Color(245, 250, 255));
		setContentPane(contentPane);

		// === Titre ===
		JLabel lblTitle = new JLabel("Suivi des commandes en cours de livraison");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);

		// === Centre : Panel Table + Filtres ===
		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel.setOpaque(false);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		// === Champ de filtre ===
		JPanel filterPanel = new JPanel(new BorderLayout(5, 5));
		filterPanel.setOpaque(false);
		filterField = new JTextField();
		filterField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		filterPanel.add(new JLabel("🔍 Filtrer : "), BorderLayout.WEST);
		filterPanel.add(filterField, BorderLayout.CENTER);
		centerPanel.add(filterPanel, BorderLayout.NORTH);

		// === Table ===
		model = new DefaultTableModel(new Object[] { "Nom commande", "Statut" }, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		table = new JTable(model);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setRowHeight(22);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

		sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane, BorderLayout.CENTER);

		// === Bas de page ===
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		bottomPanel.setOpaque(false);

		JButton btnRefresh = createStyledButton("Rafraîchir", new Color(46, 134, 222));
		JButton btnRetour = createStyledButton("Retour", new Color(52, 152, 219));

		bottomPanel.add(btnRefresh);
		bottomPanel.add(btnRetour);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		// === Écouteurs ===
		btnRefresh.addActionListener(e -> chargerCommandes());
		btnRetour.addActionListener(e -> {
			new PageAccueil(user).setVisible(true);
			dispose();
		});

		filterField.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filtrer();
			}

			public void removeUpdate(DocumentEvent e) {
				filtrer();
			}

			public void changedUpdate(DocumentEvent e) {
				filtrer();
			}
		});

		// === Chargement initial ===
		chargerCommandes();
	}

	private void filtrer() {
		String text = filterField.getText();
		if (text.trim().length() == 0) {
			sorter.setRowFilter(null);
		} else {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		}
	}

	private void chargerCommandes() {
		model.setRowCount(0); // Clear table
		CommandeDAO commandeDAO = new CommandeDAO();
		List<Commande> commandes = commandeDAO.listeCommandeEnCoursDeLivraison();
		for (Commande cmd : commandes) {
			model.addRow(new Object[] { cmd.getNom(), cmd.getStatut() });
		}
	}

	private JButton createStyledButton(String text, Color bgColor) {
		JButton btn = new JButton(text);
		btn.setBackground(bgColor);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setFocusPainted(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		return btn;
	}
}
