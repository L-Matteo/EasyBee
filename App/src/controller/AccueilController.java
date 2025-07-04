package controller;

import javax.swing.JOptionPane;

import dao.CommandeDAO;
import dao.ProduitDAO;
import dao.UtilisateurDAO;
import model.Utilisateur;
import ui.ListeCmde;
import ui.ListeErreur;
import ui.PageAccueil;
import ui.PageConnexion;
import ui.PageSuiviCmde;
import ui.PasserCommande;

public class AccueilController {
	
	private PageAccueil view;
	
	public AccueilController(PageAccueil view) {
		this.view = view;
		
		this.view.getBtnCommande().addActionListener(e -> openPasserCommande());
		this.view.getBtnSuiviCmde().addActionListener(e -> openSuiviCmde()); 
		this.view.getBtnPrepa().addActionListener(e -> openListeCmde());
		this.view.getBtnListeErreur().addActionListener(e -> openListeErreur());
		this.view.getBtnDeco().addActionListener(e -> seDeconnecter());
	}
	
	public void openSuiviCmde() {
		Utilisateur user = view.getUser();
		if(user.getRole() == 1) {
			PageSuiviCmde suiviCmde = new PageSuiviCmde(user);
			CommandeDAO daoCmde = new CommandeDAO();
			ProduitDAO daoProduit = new ProduitDAO();
			CommandeController controller = new CommandeController(daoCmde, daoProduit, user); 
			controller.setSuiviView(suiviCmde);
			view.dispose();
			suiviCmde.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
					"Erreur", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	public void openListeCmde() {
		Utilisateur user = view.getUser();
		if(user.getRole() == 2) {
			ListeCmde listeCommande = new ListeCmde(user);
			CommandeDAO daoCmde = new CommandeDAO();
			ProduitDAO daoProduit = new ProduitDAO();
			CommandeController controller = new CommandeController(daoCmde, daoProduit, user); 
			controller.setListeView(listeCommande); 
			view.dispose();
			listeCommande.setVisible(true); 
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité",
					"Erreur", JOptionPane.ERROR_MESSAGE); 
		}
	}
	
	public void openPasserCommande()
	{
		Utilisateur user = view.getUser();
		
		if(user.getRole() == 1) {
			PasserCommande passerCommande = new PasserCommande();
			CommandeDAO daoCmde = new CommandeDAO();
			ProduitDAO daoProduit = new ProduitDAO();
			CommandeController controller = new CommandeController(daoCmde, daoProduit, user); 
			controller.setPasserCommande(passerCommande);
			view.dispose();
			passerCommande.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité","Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void openListeErreur()
	{
		Utilisateur user = view.getUser();
		
		if(user.getRole() == 2) {
			ListeErreur listeErreur = new ListeErreur(user);
			CommandeDAO daoCmde = new CommandeDAO();
			ProduitDAO daoProduit = new ProduitDAO();
			CommandeController cmdeController = new CommandeController(daoCmde, daoProduit, user);
			cmdeController.setListeErreurView(listeErreur);
			view.dispose();
			listeErreur.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(view,"Vous n'avez pas le rôle nécessaire pour accéder à cette fonctionnalité","Erreur", JOptionPane.ERROR_MESSAGE);
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
