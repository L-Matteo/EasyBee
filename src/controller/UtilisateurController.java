package controller;

import javax.swing.JOptionPane;

import dao.UtilisateurDAO;
import model.Utilisateur;
import ui.PageAccueil;
import ui.PageConnexion;

public class UtilisateurController {
	
	PageConnexion view;
	UtilisateurDAO dao;
	
	public UtilisateurController(PageConnexion view, UtilisateurDAO dao) {
		this.view = view;
		this.dao = dao;
		
		this.view.setBtnListener(e -> connexion());
		
	}
	
	public void connexion() {
		
		String login = view.getLogin();
		char[] password = view.getPassword();
		view.setLogin();
		view.setPassword();
		
		Utilisateur user = dao.seConnecter(login, new String(password));
		
		if (user != null) {
            JOptionPane.showMessageDialog(view, "Vous êtes connecté.");
            PageAccueil accueil = new PageAccueil(user);
            new AccueilController(accueil);
            view.dispose();
            accueil.setVisible(true);       
        } else {
            JOptionPane.showMessageDialog(view, "ERREUR | Identifiant ou mot de passe incorrect.", "Erreur connexion",
                    JOptionPane.ERROR_MESSAGE);
        }
	}

}
