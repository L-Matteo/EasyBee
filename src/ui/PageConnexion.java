package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
 
public class PageConnexion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JButton btnConnexion;
 
	public PageConnexion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(440, 250, 1060, 620);
		setTitle("Connexion - Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Connexion à la plateforme de gestion");
		lblTitle.setBounds(290, 40, 469, 35);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitle.setForeground(new Color(50, 50, 100));
		contentPane.add(lblTitle);
		
		JLabel lblLogin = new JLabel("Identifiant :");
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLogin.setBounds(360, 125, 117, 25);
		contentPane.add(lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(360, 160, 318, 35);
		textFieldLogin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Mot de passe : ");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPassword.setBounds(360, 230, 150, 25);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(360, 265, 318, 35);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contentPane.add(passwordField);
		
		JCheckBox showPassword = new JCheckBox("Afficher le mot de passe");
		showPassword.addActionListener(e -> {
			if (showPassword.isSelected()) {
				passwordField.setEchoChar((char) 0); // Affiche le mot de passe
			} else {
				passwordField.setEchoChar('\u2022'); // Rétablit les bullets ●●●
			}
		});
		showPassword.setBounds(360, 340, 250, 21);
		showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		showPassword.setBackground(new Color(240, 245, 255));
		contentPane.add(showPassword);
		
		btnConnexion = new JButton("Se connecter");
		btnConnexion.setBounds(360, 410, 318, 65);
		btnConnexion.setBackground(new Color(100, 150, 255));
		btnConnexion.setForeground(Color.WHITE);
		btnConnexion.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnConnexion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		contentPane.add(btnConnexion);
		
		
	}
	
	public String getLogin() { return textFieldLogin.getText(); }
	public void setLogin() { textFieldLogin.setText(""); }
	
	public char[] getPassword() { return passwordField.getPassword();}
	public void setPassword() { passwordField.setText(""); }
	
	public void setBtnListener(ActionListener listener) { btnConnexion.addActionListener(listener); }
}
