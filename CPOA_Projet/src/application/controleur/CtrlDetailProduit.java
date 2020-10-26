package application.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import metier.Produit;

public class CtrlDetailProduit {

	@FXML
	private Label labelDescription;
	private ImageView visuelProduit;
	// Methode qui place les donnees de l'adresse du client selectionne dans les
	// labels et affiche le visuel
	public void initDonnees(Produit p) {
		labelDescription.setText(p.getDescription());
		visuelProduit.setImage(p.getVisuel());
	}

}
