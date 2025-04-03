package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CommandeDAO;
import model.Commande;
import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class DetailsCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomProduit;
	private JTextField textFieldQtnProduit;

	public DetailsCmde(Utilisateur user, String cmdeSlectionne) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370,250,660,390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.setBounds(10, 322, 85, 21);
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListeCmde listeCmde = new ListeCmde(user);
				listeCmde.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNomProduit = new JLabel("Nom : ");
		lblNomProduit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNomProduit.setBounds(192, 114, 112, 13);
		contentPane.add(lblNomProduit);
		
		textFieldNomProduit = new JTextField();
		textFieldNomProduit.setBounds(192, 137, 247, 19);
		contentPane.add(textFieldNomProduit);
		textFieldNomProduit.setColumns(10);
		
		JLabel lblQntProduit = new JLabel("Quantité :");
		lblQntProduit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQntProduit.setBounds(192, 166, 126, 13);
		contentPane.add(lblQntProduit);
		
		textFieldQtnProduit = new JTextField();
		textFieldQtnProduit.setBounds(194, 189, 245, 19);
		contentPane.add(textFieldQtnProduit);
		textFieldQtnProduit.setColumns(10);
		
		CommandeDAO cmdeDAO = new CommandeDAO();
		Commande commande = cmdeDAO.afficherDetailsCmdeSelectione(cmdeSlectionne);
		
		if(commande != null) {
			textFieldNomProduit.setText(commande.getNom());
			textFieldQtnProduit.setText(String.valueOf(commande.getQte()));
		} else {
			JOptionPane.showMessageDialog(this, "Impossible de trouver la commande", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

		JLabel lblDetails = new JLabel("Détails de la commande");
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDetails.setBounds(218, 50, 230, 13);
		contentPane.add(lblDetails);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setBackground(new Color(128, 128, 255));
		btnTerminer.setBounds(194, 235, 245, 21);
		contentPane.add(btnTerminer);
		
	}
}
