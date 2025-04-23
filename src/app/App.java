package app;

import java.awt.EventQueue;

import controller.UtilisateurController;
import dao.UtilisateurDAO;
import ui.PageConnexion;

public class App {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageConnexion view = new PageConnexion();
					UtilisateurDAO dao = new UtilisateurDAO();
					new UtilisateurController(view, dao);
					view.setVisible(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
