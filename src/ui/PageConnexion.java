package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UtilisateurDAO;
import model.Utilisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
 
public class PageConnexion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	public PageConnexion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(370,250,660,390);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Connexion");
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Identifiant :");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(160, 94, 117, 20);
		contentPane.add(lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(160, 127, 319, 19);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Mot de passe : ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(160, 156, 134, 13);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 179, 319, 19);
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
		            PageAccueil accueil = new PageAccueil(user);
		            accueil.setVisible(true);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(contentPane, "ERREUR | Identifiant ou mot de passe incorrect.", "Erreur connexion",
		                    JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnConnexion.setBounds(160, 222, 319, 21);
		contentPane.add(btnConnexion);
		
	}
	
}
