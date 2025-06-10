package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.CommandeDAO;
import dao.ProduitDAO;
import model.Commande;
import model.Produit;
import model.Utilisateur;
import ui.DetailsCmde;
import ui.ListeCmde;
import ui.PageAccueil;
import ui.PageSuiviCmde;
import ui.PasserCommande;

public class CommandeController {
	
	Utilisateur user;
	String cmdeSelectionne;
	CommandeDAO daoCmde;
	ProduitDAO daoProduit;
	ListeCmde listView; 
	DetailsCmde detailsView;
	PageSuiviCmde suiviCmde;
	PasserCommande passerCmde;
	
	public CommandeController(CommandeDAO daoCmde, ProduitDAO daoProduit, Utilisateur user) 
	{
		this.daoCmde = daoCmde;
		this.daoProduit = daoProduit;
		this.user = user;
	}
	
	public void setListeView(ListeCmde view) 
	{
		this.listView = view;
		
		List<Commande> commandes = daoCmde.listeCommandeStatut("en attente");
		for(Commande commande : commandes) {
			listView.getComboBox().addItem(commande.getNom());
		}
		
		this.listView.getBtnNext().addActionListener(e -> openDetailsCmde());
		this.listView.getBtnRetour().addActionListener(e -> retourAccueilListCmde()); 
	}
	
	public void setDetailsView(DetailsCmde view) 
	{
		this.detailsView = view;
		 
		Commande commande = daoCmde.afficherDetailsCmdeSelectione(cmdeSelectionne);
		
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
		
		List<Commande> commandes = daoCmde.listeCommandeStatut("en cours de livraison");
		
		for(Commande uneCommande : commandes) {
			this.suiviCmde.getComboBox().addItem(uneCommande.getNom());
		}
		
		this.suiviCmde.getBtnTermine().addActionListener(e -> changerStatusSuiviCmde());
		this.suiviCmde.getBtnRetour().addActionListener(e -> retourAccueilSuiviCmde()); 
	}
	
	public void setPasserCommande(PasserCommande view)
	{
		this.passerCmde = view;
		
		ArrayList<Produit> lesProduits = daoProduit.listeProduit();
		
		for(Produit unProduit: lesProduits) {
			this.passerCmde.getComboBox().addItem(unProduit.getDesignationProduit());
		}
		
		this.passerCmde.getBtnRetour().addActionListener(e -> retourAccueilPasserCmde());
		this.passerCmde.getBtnAddProduit().addActionListener(e -> addProduitTable());
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
		int id = daoCmde.selectIdCmde(cmdeSelectionne); 
		
		if(detailsView.getCheckBox().isSelected()) {
			try {
				int qtePrepa = Integer.parseInt(detailsView.getQtnPrepa().getText());
				daoCmde.updateQtePrepa(qtePrepa,id);
			} catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(detailsView, "La valeur du champ \"Quantité préparée\" n'est pas valable.", "ERREUR", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			daoCmde.changerStatutCommande(cmdeSelectionne, "en cours de livraison");
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
		this.cmdeSelectionne = (String) suiviCmde.getComboBox().getSelectedItem();
		
		int id = daoCmde.selectIdCmde(cmdeSelectionne);
		int qteRecu = Integer.parseInt(suiviCmde.getQteReçu().getText());
		
		if(!cmdeSelectionne.equals("— Sélectionnez une commande —")) {
			if(suiviCmde.getCheckBox().isSelected()) {
				try {
					daoCmde.updateQteRecu(qteRecu, id);
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(suiviCmde,"La valeur du champ \"Quantité reçue\" n'est pas valable.", "ERREUR", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				daoCmde.changerStatutCommande(cmdeSelectionne, "livrée");
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
	
	public void retourAccueilPasserCmde()
	{
		PageAccueil accueil = new PageAccueil(user);
		new AccueilController(accueil);
		passerCmde.dispose();
		accueil.setVisible(true);
	}
	
	// ne fonctionne pas
	public void addProduitTable()
	{
		Produit produit = (Produit) passerCmde.getComboBox().getSelectedItem();
		passerCmde.getModel().addRow(new Object[] {produit.getCodeProduit(),produit.getDesignationProduit(),25});
	}

}
