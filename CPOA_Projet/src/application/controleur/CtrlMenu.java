package application.controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.Categorie;
import metier.Produit;

public class CtrlMenu implements Initializable {

	// Composants du menu
	@FXML
	private TabPane tabPaneMenu;
	@FXML
	private Tab tabClient;
	@FXML
	private Tab tabCategorie;
	@FXML
	private Tab tabProduit;
	@FXML
	private Tab tabCommande;

	// Composants de la partie Categorie
	@FXML
	private TextField txtNomCateg;
	@FXML
	private TextField txtVisuelCateg;
	@FXML
	private Button btnAjouterCateg;
	@FXML
	private Button btnVoirCategorie;
	@FXML
	private Label labelCategorie;

	// Composants de la partie Produit
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
	private Button btnAjouter;
	@FXML
	private Button btnVoirProduit;

	// Méthodes pour la partie Produit
	@FXML
	public void creerCategorie() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nomCategorie = txtNomCateg.getText().trim();
		String visuelCategorie = txtVisuelCateg.getText().trim();
		boolean ok = true;

		if (nomCategorie.isEmpty()) {
			this.labelCategorie.setText("Le nom est vide");
			this.labelCategorie.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (visuelCategorie.isEmpty()) {
			this.labelCategorie.setText("Le visuel est vide");
			this.labelCategorie.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (ok == true) {
			this.labelCategorie.setText(txtNomCateg.getText().trim() + ", " + txtVisuelCateg.getText().trim());
			Categorie categ = new Categorie(nomCategorie, visuelCategorie);
			try {
				daoLM.getCategorieDAO().create(categ);
				// daoMySQL.getProduitDAO().create(produit);
				System.out.println(daoLM.getCategorieDAO().findAll());
				// System.out.println(daoMySQL.getProduitDAO().findAll());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void voirCategorie() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesCategorie.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Visualisation des donnees");
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Méthodes pour la partie Produit
	@FXML
	public void creerProduit() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nom = txtNom.getText().trim();
		String description = txtDescription.getText().trim();
		Double tarif = (double) 0;
		Categorie itemCategorie = cbxCategorie.getSelectionModel().getSelectedItem();
		boolean ok = true;

		if (nom.isEmpty()) {
			this.labelProduit.setText("Le nom est vide");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (description.isEmpty()) {
			this.labelProduit.setText("La description est vide");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (cbxCategorie.getSelectionModel().isEmpty()) {
			this.labelProduit.setText("La categorie n'est pas choisie");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		try {
			tarif = Double.parseDouble(txtTarif.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelProduit.setText("Le tarif n'est pas numerique ou le tarif est vide");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (ok == true) {
			this.labelProduit.setText(
					txtNom.getText().trim() + " (" + cbxCategorie.getValue() + ")" + ", " + txtTarif.getText().trim());
			Produit produit = new Produit(nom, description, tarif, "visuel", itemCategorie.getId());
			try {
				daoLM.getProduitDAO().create(produit);
				// daoMySQL.getProduitDAO().create(produit);
				System.out.println(daoLM.getProduitDAO().findAll());
				// System.out.println(daoMySQL.getProduitDAO().findAll());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void voirProduit() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesProduit.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Visualisation des donnees");
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		try {
			this.cbxCategorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}
	}

}
