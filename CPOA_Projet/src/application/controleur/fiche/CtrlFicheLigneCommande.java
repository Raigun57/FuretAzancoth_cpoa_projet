package application.controleur.fiche;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.controleur.donnees.CtrlDonneesLigneCommande;
import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.LigneCommande;
import metier.Produit;

public class CtrlFicheLigneCommande implements Initializable {

	@FXML
	private TextField txtQuantite;
	@FXML
	private TextField txtTarif;
	@FXML
	private ChoiceBox<Produit> cbxProduit;
	@FXML
	private Label labelLigneCommande;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;

	@FXML
	public void valider() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		int quantite = 0;
		Double tarif = (double) 0;

		Produit itemProduit = cbxProduit.getSelectionModel().getSelectedItem();
		boolean ok = true;

		try {
			quantite = Integer.parseInt(txtQuantite.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelLigneCommande.setText("La quantite n'est pas numerique ou la quantite est vide");
			this.labelLigneCommande.setTextFill(Color.RED);
			ok = false;
		}

		try {
			tarif = Double.parseDouble(txtTarif.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelLigneCommande.setText("Le tarif n'est pas numerique ou le tarif est vide");
			this.labelLigneCommande.setTextFill(Color.RED);
			ok = false;
		}

		if (cbxProduit.getSelectionModel().isEmpty()) {
			this.labelLigneCommande.setText("Le produit n'est pas choisi");
			this.labelLigneCommande.setTextFill(Color.RED);
			ok = false;
		}

		try {
			URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesLigneCommande.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			CtrlDonneesLigneCommande controleur = fxmlLoader.getController();

			for (int i = 0; i < controleur.getTabViewLigneCommande().getItems().size(); i++) {
				if (cbxProduit.getSelectionModel().getSelectedItem().getId() == controleur.getProduit().get(i)) {
					alerteDoublon();
					ok = false;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (ok == true) {
			LigneCommande ligneCommande = new LigneCommande(itemProduit.getId(), quantite, tarif);
			try {
				daoLM.getLigneCommandeDAO().create(ligneCommande);
				// daoMySQL.getCommandeDAO().create(produit);
				Stage stage = (Stage) btnValider.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// Ferme la boite de dialogue
	@FXML
	public void retour() {
		Stage stage = (Stage) btnRetour.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		try {
			this.cbxProduit.setItems(FXCollections.observableArrayList(dao.getProduitDAO().findAll()));
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}
	}

	public void alerteDoublon() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Doublon");
		alert.setContentText(
				"Vous avez rentr� un produit qui existe deja. Cela signifie que cette ligne de commande est deja existante.");
		alert.show();
	}

}