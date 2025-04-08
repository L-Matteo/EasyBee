package ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CommandeDAO;
import model.Commande;
import model.Utilisateur;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class ListeCmde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ListeCmde(Utilisateur user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370,250,660,390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBoxListCmde = new JComboBox<>();
		comboBoxListCmde.setBounds(189, 152, 255, 21);
		contentPane.add(comboBoxListCmde);
		
		comboBoxListCmde.addItem("");
		
		CommandeDAO commandeDAO = new CommandeDAO();
        List<Commande> commandes = commandeDAO.listeCommandeEnAttente();
		
		for(Commande commande : commandes) {
			comboBoxListCmde.addItem(commande.getNom());
		}
		
		JLabel lblSelectCdm = new JLabel("Sélectionner la commande à préparer");
		lblSelectCdm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelectCdm.setBounds(181, 59, 347, 31);
		contentPane.add(lblSelectCdm);
		
		JButton btnNewButton = new JButton("Suivant");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmdeSelectionne = (String)comboBoxListCmde.getSelectedItem();
				DetailsCmde cmde = new DetailsCmde(user, cmdeSelectionne);
				cmde.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.setBounds(189, 201, 255, 21);
		contentPane.add(btnNewButton);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(128, 128, 255));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageAccueil accueil = new PageAccueil(user);
				accueil.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(10, 322, 85, 21);
		contentPane.add(btnRetour);
	}

}
