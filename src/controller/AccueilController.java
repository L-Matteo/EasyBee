package controller;

import javax.swing.JOptionPane;

import dao.CommandeDAO;
import dao.UtilisateurDAO;
import model.Utilisateur;
import ui.ListeCmde;
import ui.PageAccueil;
import ui.PageConnexion;
import ui.PageSuiviCmde;

public class AccueilController {
	
	private PageAccueil view;
	
	public AccueilController(PageAccueil view) {
		this.view = view;
		
		this.view.getBtnSuiviCmde().addActionListener(e -> openSuiviCmde()); 
		this.view.getBtnPrepa().addActionListener(e -> openListeCmde());
		this.view.getBtnDeco().addActionListener(e -> seDeconnecter());
	}
	
	public void openSuiviCmde() {
		Utilisateur user = view.getUser();
		if(user.getRole() == 1) {
			PageSuiviCmde suiviCmde = new PageSuiviCmde(user);
			CommandeDAO dao = new CommandeDAO();
			CommandeController controller = new CommandeController(dao, user); 
			controller.setSuiviView(suiviCmde);
			view.dispose();
			suiviCmde.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
					"ERREUR", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	public void openListeCmde() {
		Utilisateur user = view.getUser();
		if(user.getRole() == 2) {
			ListeCmde listeCommande = new ListeCmde(user);
			CommandeDAO dao = new CommandeDAO();
			CommandeController controller = new CommandeController(dao, user); 
			controller.setListeView(listeCommande); 
			view.dispose();
			listeCommande.setVisible(true); 
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
					"ERREUR", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	public void seDeconnecter() {
		PageConnexion pageConnexion = new PageConnexion();
		UtilisateurDAO dao = new UtilisateurDAO();
		new UtilisateurController(pageConnexion, dao);
		view.dispose();
		pageConnexion.setVisible(true);
	}
 
}
