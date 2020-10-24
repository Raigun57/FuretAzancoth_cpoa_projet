package application.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import metier.Produit;

public class CtrlDetailProduit {

	@FXML
	private Label labelDescription;

	// Methode qui place les donnees de l'adresse du client selectionne dans les
	// labels
	public void initDonnees(Produit p) {
		labelDescription.setText(p.getDescription());
	}

}
