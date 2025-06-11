package controller;

import java.awt.Color;
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
			JOptionPane.showMessageDialog(detailsView, "Impossible de trouver la commande", "Erreur", JOptionPane.ERROR_MESSAGE);
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
			this.passerCmde.getComboBox().addItem(unProduit);
		} 
		
		passerCmde.getComboBox().addItemListener(e -> {
			Produit produitSelectionne = (Produit) passerCmde.getComboBox().getSelectedItem();
			int stock = daoProduit.voirStockEntrepot(produitSelectionne.getId());
			this.passerCmde.getLabelStock().setText("Stock Entrepôt : " + stock);
		});
		
		passerCmde.getBtnValider().setEnabled(false);
		passerCmde.getBtnValider().setBackground(new Color(149, 165, 166));
		
		passerCmde.getModel().addTableModelListener(e -> {
		    int rowCount = passerCmde.getModel().getRowCount();
		    boolean hasRows = rowCount > 0;
		    passerCmde.getBtnValider().setEnabled(hasRows);
		    passerCmde.getBtnValider().setBackground(
		        hasRows ? new Color(100, 150, 255) : new Color(149, 165, 166)
		    );
		});
		
		this.passerCmde.getBtnRetour().addActionListener(e -> retourAccueilPasserCmde());
		this.passerCmde.getBtnAddProduit().addActionListener(e -> addProduitTable());
		this.passerCmde.getBtnSuprProduit().addActionListener(e -> supProduitTable());
		this.passerCmde.getBtnValider().addActionListener(e -> validerCmde());
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
				JOptionPane.showMessageDialog(detailsView, "La valeur du champ \"Quantité préparée\" n'est pas valable.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(suiviCmde,"La valeur du champ \"Quantité reçue\" n'est pas valable.", "Erreur", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				daoCmde.changerStatutCommande(cmdeSelectionne, "livrée");
				JOptionPane.showMessageDialog(suiviCmde, "Le statut de la commande a été changé", "Succès", JOptionPane.INFORMATION_MESSAGE);
				PageSuiviCmde pageSuiviCmde = new PageSuiviCmde(user);
				setSuiviView(pageSuiviCmde); 
				suiviCmde.dispose();
				pageSuiviCmde.setVisible(true); 
			} else {
				JOptionPane.showMessageDialog(suiviCmde, "La commande est toujours en cours de livraison", "Erreur", JOptionPane.ERROR_MESSAGE);
			} 
		} else {
			JOptionPane.showMessageDialog(suiviCmde, "Erreur dans la sélection de la commande", "Erreur", JOptionPane.ERROR_MESSAGE);
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
	
	public void addProduitTable()
	{
		Produit produitSelectionne = (Produit) passerCmde.getComboBox().getSelectedItem();
		String textQte = passerCmde.getTextFieldQte().getText();
		
		if(produitSelectionne.getCodeProduit() != 0) {
			
			if(!textQte.isEmpty()) {
				
				int qte = Integer.parseInt(textQte);
				
				if(qte > 0) {
					passerCmde.getModel().addRow(new Object[] {
							produitSelectionne.getCodeProduit(),
							produitSelectionne.getDesignationProduit(),
							qte
					});
				} else {
					JOptionPane.showMessageDialog(passerCmde, "Veuillez saisir une quantité valide.","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(passerCmde, "Veuillez saisir une quantité valide.","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(passerCmde, "Veuillez choisir un produit valide.","Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void supProduitTable()
	{
		int selectedRow = passerCmde.getTable().getSelectedRow();
	    if (selectedRow != -1) {
	    	passerCmde.getModel().removeRow(selectedRow);
	    } else {
	    	JOptionPane.showMessageDialog(passerCmde, "Veuillez sélectionner un produit à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void validerCmde()
	{
		int countRow = passerCmde.getModel().getRowCount();
		
		if(countRow > 0) {
			int idRole = user.getRole();
			int idCmde = daoCmde.addCmdeApproDepot(idRole);
			
			if(idCmde != 0) {
				
				int rowCount = passerCmde.getModel().getRowCount();
				
				for(int i = 0; i < rowCount; i++) {
					try {
						int codeProduit = (int) passerCmde.getTable().getValueAt(i, 0);
						int idProduit = daoProduit.selectIdProduit(codeProduit);
						int qteProduit = (int) passerCmde.getTable().getValueAt(i, 2);
						daoCmde.addDetailsProduit(idProduit, idCmde, qteProduit);	
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				JOptionPane.showMessageDialog(passerCmde, "Votre commande a bien été ajoutée.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				PasserCommande passerCommande = new PasserCommande();
				passerCmde.dispose();
				setPasserCommande(passerCommande);
				passerCommande.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(passerCmde, "Erreur dans l'ajout de la commande, veuillez réessayer", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(passerCmde, "Erreur dans la commande, veuillez ajouter au moins 1 produit.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
	} 

}
