package main;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class listeCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Statement st;
	ConnexionBdd cn=new ConnexionBdd(); 

	public listeCmde(Utilisateur user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450,250,373,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBoxListCmde = new JComboBox();
		comboBoxListCmde.setBounds(88, 86, 183, 21);
		contentPane.add(comboBoxListCmde);
		
		String requete = "Select nomCommande from cmdeapprodepot where statutCommande like '%en attente%'";
		try {
			st=cn.laconnexion().createStatement();
			ResultSet rs = st.executeQuery(requete);
			while (rs.next()) {
		        String nomCommande = rs.getString("nomCommande");
		        comboBoxListCmde.addItem(nomCommande);
		    }
		} catch(Exception e) {
			System.out.println("Erreur");
		}
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(128, 128, 255));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageAccueil accueil = new pageAccueil(user);
				accueil.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(10, 232, 85, 21);
		contentPane.add(btnRetour);
		
		JLabel lblSelectCdm = new JLabel("Sélectionner la commande à préparer");
		lblSelectCdm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelectCdm.setBounds(41, 23, 347, 31);
		contentPane.add(lblSelectCdm);
		
		JButton btnNewButton = new JButton("Suivant");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detailsCmde cmde = new detailsCmde(user);
				cmde.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.setBounds(135, 145, 85, 21);
		contentPane.add(btnNewButton);
		
	}

}
