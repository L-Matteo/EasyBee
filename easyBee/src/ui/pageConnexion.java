package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UtilisateurDAO;
import model.Utilisateur;
import utils.ConnexionBdd;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
 
public class pageConnexion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	public pageConnexion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450,250,373,300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Connexion");
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Identifiant :");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(110, 46, 117, 20);
		contentPane.add(lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(110, 66, 134, 19);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Mot de passe : ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(110, 104, 134, 13);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 120, 134, 19);
		contentPane.add(passwordField);
		
		JButton btnConnexion = new JButton("Se connecter");
		btnConnexion.setBackground(new Color(128, 128, 255));
		btnConnexion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String login = textFieldLogin.getText();
		        char[] password = passwordField.getPassword();
		        textFieldLogin.setText(""); 
		        passwordField.setText("");

		        Utilisateur user = UtilisateurDAO.seConnecter(login, new String(password));

		        if (user != null) {
		            JOptionPane.showMessageDialog(contentPane, "Vous êtes connecté.");
		            pageAccueil accueil = new pageAccueil(user);
		            accueil.setVisible(true);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(contentPane, "ERREUR | Identifiant ou mot de passe incorrect.", "Erreur connexion",
		                    JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnConnexion.setBounds(110, 169, 134, 21);
		contentPane.add(btnConnexion);
		
	}
	
}
