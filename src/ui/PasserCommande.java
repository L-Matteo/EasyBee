package ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PasserCommande extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PasserCommande() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(570, 250, 700, 420);
		setTitle("Passer une commande - Gestion des stocks");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 245, 255));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

		setContentPane(contentPane);
	}

}
