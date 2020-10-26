package application.controleur.fiche;

import java.io.IOException;
import java.net.URL;

import application.controleur.donnees.CtrlDonneesProduit;
import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.Categorie;
import metier.Produit;

public class CtrlFicheProduit {

	@FXML
	private TextField txtNom;
	@FXML
	private TextArea txtDescription;
	@FXML
	private TextField txtTarif;
	@FXML
	private ChoiceBox<Categorie> cbxCategorie;
	@FXML
	private Label labelProduit;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;

	private int i;

	@FXML
	public void valider() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nom = txtNom.getText().trim();
		String description = txtDescription.getText().trim();
		Double tarif = (double) 0;
		Categorie itemCategorie = cbxCategorie.getSelectionModel().getSelectedItem();
		boolean ok = true;

		if (nom.isEmpty()) {
			this.labelProduit.setText("Le nom est vide");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		if (description.isEmpty()) {
			this.labelProduit.setText("La description est vide");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		if (cbxCategorie.getSelectionModel().isEmpty()) {
			this.labelProduit.setText("La categorie n'est pas choisie");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		try {
			tarif = Double.parseDouble(txtTarif.getText().trim());
			if (tarif < 0) {
				this.labelProduit.setText("Le tarif ne peut pas être négatif");
				this.labelProduit.setTextFill(Color.RED);
				ok = false;
			}
		} catch (NumberFormatException | NullPointerException e) {
			this.labelProduit.setText("Le tarif n'est pas numerique ou le tarif est vide");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		try {
			URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesProduit.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			CtrlDonneesProduit controleur = fxmlLoader.getController();

			for (int i = 0; i < controleur.getTabViewProduit().getItems().size(); i++) {
				if (nom.equalsIgnoreCase(controleur.getNom().get(i))
						&& itemCategorie.getId() == controleur.getIdCateg().get(i)) {
					alerteDoublon();
					ok = false;
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (ok == true) {
			Produit produit = new Produit(nom, description, tarif, "visuel", itemCategorie.getId());
			try {
				if (i == 0)
					daoMySQL.getProduitDAO().create(produit);
				else if (i == 1)
					daoLM.getProduitDAO().create(produit);
				Stage stage = (Stage) btnValider.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void initDonnees() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoSQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		try {
			if (i == 0)
				cbxCategorie.setItems(FXCollections.observableArrayList(daoSQL.getCategorieDAO().findAll()));
			else if (i == 1)
				cbxCategorie.setItems(FXCollections.observableArrayList(daoLM.getCategorieDAO().findAll()));
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}
	}

	// Ferme la boite de dialogue
	@FXML
	public void retour() {
		Stage stage = (Stage) btnRetour.getScene().getWindow();
		stage.close();
	}

	public void alerteDoublon() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Doublon");
		alert.setContentText("Ce nom de produit existe deja pour cette categorie. Changez le nom ou la categorie");
		alert.show();
	}

	// Permet de set le bon index de la choice box de persistance
	public void setIndexPersistance(int i) {
		this.i = i;
	}

}
