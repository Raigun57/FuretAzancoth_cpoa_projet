package application.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import metier.Client;

public class CtrlDetailAdresseClient {

	@FXML
	private Label labelNumero;
	@FXML
	private Label labelRue;
	@FXML
	private Label labelCodePostal;
	@FXML
	private Label labelVille;
	@FXML
	private Label labelPays;

	// Methode qui place les donnees de l'adresse du client selectionne dans les
	// labels
	public void initDonnees(Client c) {
		int numero = c.getNumero();
		labelNumero.setText(Integer.toString(numero));
		labelRue.setText(c.getRue());

		int codePostal = c.getCodePostal();
		labelCodePostal.setText(Integer.toString(codePostal));
		labelVille.setText(c.getVille());
		labelPays.setText(c.getPays());
	}

}
