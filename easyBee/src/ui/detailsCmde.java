package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Utilisateur;
import utils.ConnexionBdd;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class detailsCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomProduit;
	private JTextField textFieldQtnProduit;
	ConnexionBdd cn = ConnexionBdd.getInstance(); 
	Statement st;

	public detailsCmde(Utilisateur user, String cmdeSlectionne) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450,250,373,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.setBounds(10, 232, 85, 21);
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeCmde listeCmde = new listeCmde(user);
				listeCmde.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNomProduit = new JLabel("Nom : ");
		lblNomProduit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNomProduit.setBounds(70, 85, 112, 13);
		contentPane.add(lblNomProduit);
		
		textFieldNomProduit = new JTextField();
		textFieldNomProduit.setBounds(70, 108, 96, 19);
		contentPane.add(textFieldNomProduit);
		textFieldNomProduit.setColumns(10);
		
		JLabel lblQntProduit = new JLabel("Quantité :");
		lblQntProduit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQntProduit.setBounds(194, 85, 126, 13);
		contentPane.add(lblQntProduit);
		
		String requete = "select qtePrepa from detailcmd join cmdeapprodepot on idCmdeApproDepot = cmdeapprodepot.id where nomCommande = " + cmdeSlectionne;
		try {			
			st=cn.laconnexion().createStatement();
			ResultSet rs = st.executeQuery(requete);
			if(rs.next()){
				String nomCommande = rs.getString("nomCommande");
				int qte = rs.getInt("qtePrepa");
				textFieldNomProduit.setText(nomCommande);
				textFieldQtnProduit.setText(String.valueOf(qte));
			} else {
				JOptionPane.showMessageDialog(contentPane,"Impossible de récupérer la commande.","ERREUR",JOptionPane.ERROR_MESSAGE);
				}
		}catch (Exception ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(contentPane, 
		        "Erreur : " + ex.getMessage(), 
		        "ERREUR", 
		        JOptionPane.ERROR_MESSAGE);
		}
		
		textFieldQtnProduit = new JTextField();
		textFieldQtnProduit.setBounds(194, 108, 96, 19);
		contentPane.add(textFieldQtnProduit);
		textFieldQtnProduit.setColumns(10);
		
		JLabel lblDetails = new JLabel("Détails de la commande");
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDetails.setBounds(88, 24, 230, 13);
		contentPane.add(lblDetails);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setBackground(new Color(128, 128, 255));
		btnTerminer.setBounds(125, 156, 112, 21);
		contentPane.add(btnTerminer);
		
		
	}
}
