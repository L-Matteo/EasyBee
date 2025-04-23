package controller;

import javax.swing.JOptionPane;

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
			view.close();
			PageSuiviCmde suiviCmde = new PageSuiviCmde(user);
			suiviCmde.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
					"ERREUR", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	public void openListeCmde() {
		Utilisateur user = view.getUser();
		if(user.getRole() == 2) {
			view.close();
			ListeCmde listeCommande = new ListeCmde(user);
			listeCommande.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
					"ERREUR", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	public void seDeconnecter() {
		view.close();
		PageConnexion pageConnexion = new PageConnexion();
		UtilisateurDAO dao = new UtilisateurDAO();
		new UtilisateurController(pageConnexion, dao);
		pageConnexion.setVisible(true);
	}
 
}
