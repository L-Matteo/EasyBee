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
import ui.ReportProblem;

public class CommandeController {
	
	Utilisateur user;
	String cmdeSelectionne;
	CommandeDAO daoCmde;
	ProduitDAO daoProduit;
	ListeCmde listView; 
	DetailsCmde detailsView;
	PageSuiviCmde suiviCmde;
	ReportProblem reportProblem;
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
		
		this.listView.getBtnNext().addActionListener(e -> validerListCmde());
		this.listView.getBtnRetour().addActionListener(e -> {
			PageAccueil accueil = new PageAccueil(user);
			new AccueilController(accueil);
			listView.dispose();
			accueil.setVisible(true);
		}); 
	}
	
	public void setDetailsView(DetailsCmde view) 
	{
		this.detailsView = view;
		 
		int idCmde = daoCmde.selectIdCmde(cmdeSelectionne);
		ArrayList<Produit> produitsCmde = daoCmde.afficherProduitCmdeSelectione(idCmde); 
		
		for(Produit unProduit: produitsCmde) {
			detailsView.getModel().addRow(new Object[]{
					unProduit.getCodeProduit(),
					unProduit.getDesignationProduit(), 
					unProduit.getQte()
			});
		}
		
		this.detailsView.getBtnTerminer().addActionListener(e -> TerminerDetailsCmde());
		this.detailsView.getBtnRetour().addActionListener(e -> {
			ListeCmde listeCmde = new ListeCmde(user);
			setListeView(listeCmde);
			detailsView.dispose();
			listeCmde.setVisible(true);
		});
		
	}
	
	public void setSuiviView(PageSuiviCmde view)
	{
		this.suiviCmde = view;
		
		List<Commande> commandes = daoCmde.listeCommandeStatut("en cours de livraison");
		
		for(Commande uneCommande : commandes) {
			this.suiviCmde.getComboBox().addItem(uneCommande.getNom());
		}
		
		suiviCmde.getComboBox().addItemListener(e -> { 
			if(!suiviCmde.getComboBox().getSelectedItem().equals("— Sélectionnez une commande —")) {
				String cmdeSelectionne = (String) this.suiviCmde.getComboBox().getSelectedItem();
				int idCmdeSelectionne = daoCmde.selectIdCmde(cmdeSelectionne);
				ArrayList<Produit> produitsCmde = daoCmde.afficherProduitCmdeSelectione(idCmdeSelectionne);	
				suiviCmde.getModel().setRowCount(0);
				for(Produit unProduit: produitsCmde) {
					suiviCmde.getModel().addRow(new Object[] {
							unProduit.getCodeProduit(),
							unProduit.getDesignationProduit(), 
							unProduit.getQte()
					});
				}
			} else {
				suiviCmde.getModel().setRowCount(0);
			}
		});
		 
		this.suiviCmde.getBtnRetour().addActionListener(e -> {
			PageAccueil accueil = new PageAccueil(user);
			new AccueilController(accueil);
			suiviCmde.dispose();
			accueil.setVisible(true); 
		}); 
		
		this.suiviCmde.getBtnTermine().addActionListener(e -> TerminerSuiviCmde());
		
		this.suiviCmde.getBtnProb().addActionListener(e -> {
			String cmdeSelectionne = (String) suiviCmde.getComboBox().getSelectedItem();
			if(!cmdeSelectionne.equals("— Sélectionnez une commande —")) {
				ReportProblem reportProblem = new ReportProblem(user, cmdeSelectionne);
				setReportProblem(reportProblem);
				suiviCmde.dispose();
				reportProblem.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(suiviCmde, "Veuillez choisir une commande valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	public void setReportProblem(ReportProblem view)
	{
		this.reportProblem = view;
		
		reportProblem.getBtnRetour().addActionListener(e -> {
			PageSuiviCmde pageSuiviCmde = new PageSuiviCmde(user);
			setSuiviView(pageSuiviCmde);
			reportProblem.dispose();
			pageSuiviCmde.setVisible(true);
		});
		
		reportProblem.getBtnValider().addActionListener(e -> signalerProb());
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
		
		this.passerCmde.getBtnRetour().addActionListener(e -> {
			PageAccueil accueil = new PageAccueil(user);
			new AccueilController(accueil);
			passerCmde.dispose();
			accueil.setVisible(true);
		});
		this.passerCmde.getBtnAddProduit().addActionListener(e -> addProduitTable());
		this.passerCmde.getBtnSuprProduit().addActionListener(e -> suprProduitTable());
		this.passerCmde.getBtnValider().addActionListener(e -> validerCmde());
	}
	
	public void validerListCmde() 
	{
		this.cmdeSelectionne = (String)listView.getComboBox().getSelectedItem();
		
		if(!cmdeSelectionne.equals("— Sélectionnez une commande —")) {
			DetailsCmde cmde = new DetailsCmde(user, cmdeSelectionne); 
			setDetailsView(cmde);
			listView.dispose();
			cmde.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(listView, "Veuillez sélectionner une commande", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	 
	public void TerminerDetailsCmde()
	{
		if(detailsView.getCheckBox().isSelected()) {
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
	
	public void TerminerSuiviCmde() 
	{
		this.cmdeSelectionne = (String) suiviCmde.getComboBox().getSelectedItem();
		
		if(!cmdeSelectionne.equals("— Sélectionnez une commande —")) {
			if(daoCmde.changerStatutCommande(cmdeSelectionne, "livrée")) {
				JOptionPane.showMessageDialog(suiviCmde, "Le statut de la commande a été changé.", "Succès", JOptionPane.INFORMATION_MESSAGE);
				PageSuiviCmde pageSuiviCmde = new PageSuiviCmde(user);
				setSuiviView(pageSuiviCmde); 
				suiviCmde.dispose();
				pageSuiviCmde.setVisible(true); 
			} else {
				JOptionPane.showMessageDialog(suiviCmde, "Une erreur est survenue.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(suiviCmde, "Veuillez choisir une commande valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	} 
	
	public void signalerProb() 
	{
		String descriptionProblem = reportProblem.getDescription().getText();
		
		if(!descriptionProblem.isEmpty()) {
			if(daoCmde.addDescriptionProblem(descriptionProblem, reportProblem.getCmdeSelectionne())) {
				if(daoCmde.changerStatutCommande(reportProblem.getCmdeSelectionne(), "Erreur")) {
					JOptionPane.showMessageDialog(reportProblem, "L'erreur a été signalée.", "Succès", JOptionPane.INFORMATION_MESSAGE);
					PageSuiviCmde pageSuiviCmde = new PageSuiviCmde(user);
					setSuiviView(pageSuiviCmde);
					reportProblem.dispose();
					pageSuiviCmde.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(reportProblem, "Une erreur est survenue.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(reportProblem, "Une erreur est survenue.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(reportProblem, "Veuillez décrire l'erreur.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
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
	
	public void suprProduitTable()
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
