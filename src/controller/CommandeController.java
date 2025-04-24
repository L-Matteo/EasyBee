package controller;

import java.util.List;

import dao.CommandeDAO;
import model.Commande;
import model.Utilisateur;
import ui.DetailsCmde;
import ui.ListeCmde;
import ui.PageAccueil;
import ui.PageSuiviCmde;

public class CommandeController {
	
	Utilisateur user;
	CommandeDAO dao;
	ListeCmde listView;
	DetailsCmde detailsView;
	PageSuiviCmde suiviCmde;
	
	public CommandeController(CommandeDAO dao, Utilisateur user) {
		this.dao = dao;
		this.user = user;
	}
	
	public void setListeView(ListeCmde view) {
		this.listView = view;
		
		List<Commande> commandes = dao.listeCommandeStatut("en attente");
		for(Commande commande : commandes) {
			listView.getComboBox().addItem(commande.getNom());
		}
		
		this.listView.getBtnNext().addActionListener(e -> openDetailsCmde());
		this.listView.getBtnRetour().addActionListener(e -> retourAccueilListCmde()); 
	}
	
	public void setDetailsView(DetailsCmde view) {
		this.detailsView = view;
	}
	
	public void setSuiviView(PageSuiviCmde view) {
		this.suiviCmde = view;
	}
	
	public void openDetailsCmde() {
		String cmdeSelectionne = (String)listView.getComboBox().getSelectedItem();
		DetailsCmde cmde = new DetailsCmde(user, cmdeSelectionne);
		listView.close();
		cmde.setVisible(true);
	}
	
	public void retourAccueilListCmde() {
		PageAccueil accueil = new PageAccueil(user);
		new AccueilController(accueil);
		listView.close();
		accueil.setVisible(true);
	}

}
