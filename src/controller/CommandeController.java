package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dao.CommandeDAO;
import model.Commande;
import model.Utilisateur;
import ui.DetailsCmde;
import ui.ListeCmde;
import ui.PageAccueil;
import ui.PageSuiviCmde;

public class CommandeController {
	
	Utilisateur user;
	String cmdeSelectionne;
	CommandeDAO dao;
	ListeCmde listView;
	DetailsCmde detailsView;
	PageSuiviCmde suiviCmde;
	
	public CommandeController(CommandeDAO dao, Utilisateur user) 
	{
		this.dao = dao;
		this.user = user;
	}
	
	public void setListeView(ListeCmde view) 
	{
		this.listView = view;
		
		List<Commande> commandes = dao.listeCommandeStatut("en attente");
		for(Commande commande : commandes) {
			listView.getComboBox().addItem(commande.getNom());
		}
		
		this.listView.getBtnNext().addActionListener(e -> openDetailsCmde());
		this.listView.getBtnRetour().addActionListener(e -> retourAccueilListCmde()); 
	}
	
	public void setDetailsView(DetailsCmde view) 
	{
		this.detailsView = view;
		 
		Commande commande = dao.afficherDetailsCmdeSelectione(cmdeSelectionne);
		
		if(commande != null) {
			detailsView.getNomProduit().setText(commande.getNom());
			detailsView.getQtnDemande().setText("Quantité demandée : " + String.valueOf(commande.getQte()));
		} else {
			JOptionPane.showMessageDialog(detailsView, "Impossible de trouver la commande", "ERREUR", JOptionPane.ERROR_MESSAGE);
			ListeCmde listeCmde = new ListeCmde(user);
			setListeView(listeCmde); 
			detailsView.dispose();
			listeCmde.setVisible(true);
		}
		
		this.detailsView.getBtnTerminer().addActionListener(e -> changerStatusDetailsCmde());
		this.detailsView.getBtnRetour().addActionListener(e -> retourDetailsCmde());
		
	}
	
	public void setSuiviView(PageSuiviCmde view) 
	{
		this.suiviCmde = view;
		
		List<Commande> commandes = dao.listeCommandeStatut("en cours de livraison");
		
		for(Commande uneCommande : commandes) {
			this.suiviCmde.getComboBox().addItem(uneCommande.getNom());
		}
		
		this.suiviCmde.getBtnTermine().addActionListener(e -> changerStatusSuiviCmde());
		this.suiviCmde.getBtnRetour().addActionListener(e -> retourAccueilSuiviCmde()); 
	}
	
	public void openDetailsCmde() 
	{
		this.cmdeSelectionne = (String)listView.getComboBox().getSelectedItem();
		DetailsCmde cmde = new DetailsCmde(user, cmdeSelectionne); 
		setDetailsView(cmde);
		listView.dispose();
		cmde.setVisible(true);
	}
	  
	public void retourAccueilListCmde() 
	{
		PageAccueil accueil = new PageAccueil(user);
		new AccueilController(accueil);
		listView.dispose();
		accueil.setVisible(true);
	}
	
	public void changerStatusDetailsCmde() 
	{
		int id = dao.selectIdCmde(cmdeSelectionne); 
		
		if(detailsView.getCheckBox().isSelected()) {
			try {
				int qtePrepa = Integer.parseInt(detailsView.getQtnPrepa().getText());
				dao.updateQtePrepa(qtePrepa,id);
			} catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(detailsView, "La valeur du champ \"Quantité préparée\" n'est pas valable.", "ERREUR", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			dao.changerStatutCommande(cmdeSelectionne, "en cours de livraison");
			JOptionPane.showMessageDialog(detailsView, "Le statut de la commande a été changé.", "Succès", JOptionPane.INFORMATION_MESSAGE);
			ListeCmde listeCmde = new ListeCmde(user);
			setListeView(listeCmde);
			detailsView.dispose();
			listeCmde.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(detailsView, "La commande est toujours en cours de préparation", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void retourDetailsCmde() 
	{
		ListeCmde listeCmde = new ListeCmde(user);
		setListeView(listeCmde);
		detailsView.dispose();
		listeCmde.setVisible(true);
	}
	
	public void changerStatusSuiviCmde() 
	{
		if(suiviCmde.getComboBox().getSelectedItem() != "— Sélectionnez une commande —") {
			if(suiviCmde.getCheckBox().isSelected()) {
				String cmdeSelectionne = (String)suiviCmde.getComboBox().getSelectedItem();
				dao.changerStatutCommande(cmdeSelectionne, "livrée");
				JOptionPane.showMessageDialog(suiviCmde, "Le statut de la commande a été changé", "Succès", JOptionPane.INFORMATION_MESSAGE);
				PageSuiviCmde pageSuiviCmde = new PageSuiviCmde(user);
				setSuiviView(pageSuiviCmde); 
				suiviCmde.dispose();
				pageSuiviCmde.setVisible(true); 
			} else {
				JOptionPane.showMessageDialog(suiviCmde, "La commande est toujours en cours de livraison", "ERREUR", JOptionPane.ERROR_MESSAGE);
			} 
		} else {
			JOptionPane.showMessageDialog(suiviCmde, "Erreur dans la sélection de la commande", "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	} 
	
	public void retourAccueilSuiviCmde() 
	{
		PageAccueil accueil = new PageAccueil(user);
		new AccueilController(accueil);
		suiviCmde.dispose();
		accueil.setVisible(true); 
	}

}
