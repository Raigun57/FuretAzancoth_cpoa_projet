package application.controleur.fiche;

import java.io.IOException;
import java.net.URL;

import application.controleur.donnees.CtrlDonneesCategorie;
import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.Categorie;

public class CtrlFicheCategorie {

	@FXML
	private TextField txtNomCateg;
	@FXML
	private TextField txtVisuelCateg;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;
	@FXML
	private Label labelCategorie;

	private int i;

	@FXML
	public void valider() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nomCategorie = txtNomCateg.getText().trim();
		String visuelCategorie = txtVisuelCateg.getText().trim();
		boolean ok = true;

		if (nomCategorie.isEmpty()) {
			this.labelCategorie.setText("Le nom est vide");
			this.labelCategorie.setTextFill(Color.RED);
			ok = false;
		}

		if (visuelCategorie.isEmpty()) {
			this.labelCategorie.setText("Le visuel est vide");
			this.labelCategorie.setTextFill(Color.RED);
			ok = false;
		}

		try {
			URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesCategorie.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			CtrlDonneesCategorie controleur = fxmlLoader.getController();

			for (int i = 0; i < controleur.getTabViewCategorie().getItems().size(); i++) {
				if (nomCategorie.equalsIgnoreCase(controleur.getNom().get(i))) {
					alerteDoublon();
					ok = false;
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (ok == true) {
			Categorie categ = new Categorie(nomCategorie, visuelCategorie);
			try {
				if (i == 0)
					daoMySQL.getCategorieDAO().create(categ);
				else if (i == 1)
					daoLM.getCategorieDAO().create(categ);

				Stage stage = (Stage) btnValider.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void retour() {
		Stage stage = (Stage) btnRetour.getScene().getWindow();
		stage.close();
	}

	public void alerteDoublon() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Doublon");
		alert.setContentText("Ce nom de categorie existe deja");
		alert.show();
	}

	// Permet de set le bon index de la choice box de persistance
	public void setIndexPersistance(int i) {
		this.i = i;
	}

}
