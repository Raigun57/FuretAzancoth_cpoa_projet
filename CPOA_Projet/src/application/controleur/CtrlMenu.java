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
import metier.Client;
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

	// Composants de la partie Client
	@FXML
	private TextField txtNomClient;
	@FXML
	private TextField txtPrenom;
	@FXML
	private TextField txtIdentifiant;
	@FXML
	private TextField txtMdp;
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtRue;
	@FXML
	private TextField txtCodePostal;
	@FXML
	private TextField txtVille;
	@FXML
	private TextField txtPays;
	@FXML
	private Button btnAjouterClient;
	@FXML
	private Button btnVoirClient;
	@FXML
	private Label labelClient;

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

	// Méthodes pour la partie Client
	@FXML
	public void creerClient() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nomClient = txtNomClient.getText().trim();
		String prenom = txtPrenom.getText().trim();
		String identifiant = txtIdentifiant.getText().trim();
		String mdp = txtMdp.getText().trim();
		int numero = 0;
		String rue = txtRue.getText().trim();
		int codePostal = 0;
		String ville = txtVille.getText().trim();
		String pays = txtPays.getText().trim();
		boolean ok = true;

		if (nomClient.isEmpty()) {
			this.labelClient.setText("Le nom est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		if (prenom.isEmpty()) {
			this.labelClient.setText("Le prenom est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		if (identifiant.isEmpty()) {
			this.labelClient.setText("L'identifiant est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		if (mdp.isEmpty()) {
			this.labelClient.setText("Le mot de passe est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		try {
			numero = Integer.parseInt(txtNumero.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelClient.setText("Le numero n'est pas numerique ou le numero est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		if (rue.isEmpty()) {
			this.labelClient.setText("La rue est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		try {
			codePostal = Integer.parseInt(txtCodePostal.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelClient.setText("Le code postal n'est pas numerique ou le code postal est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		if (ville.isEmpty()) {
			this.labelClient.setText("La ville est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		if (pays.isEmpty()) {
			this.labelClient.setText("Le pays est vide");
			this.labelClient.setTextFill(Color.RED);
			ok = false;
		}

		if (ok == true) {
			this.labelClient.setText(txtNomClient.getText().trim() + ", " + txtPrenom.getText().trim() + ", "
					+ txtIdentifiant.getText().trim() + txtMdp.getText().trim());
			this.labelClient.setTextFill(Color.BLACK);
			Client client = new Client(nomClient, prenom, identifiant, mdp, numero, rue, codePostal, ville, pays);
			try {
				daoLM.getClientDAO().create(client);
				// daoMySQL.getClientDAO().create(Client);
				System.out.println(daoLM.getClientDAO().findAll());
				// System.out.println(daoMySQL.getClientDAO().findAll());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void voirClient() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesClient.fxml");
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

	// Méthodes pour la partie Categorie
	@FXML
	public void creerCategorie() {
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

		if (ok == true) {
			this.labelCategorie.setText(txtNomCateg.getText().trim() + ", " + txtVisuelCateg.getText().trim());
			this.labelCategorie.setTextFill(Color.BLACK);
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
		} catch (NumberFormatException | NullPointerException e) {
			this.labelProduit.setText("Le tarif n'est pas numerique ou le tarif est vide");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		if (ok == true) {
			this.labelProduit.setText(
					txtNom.getText().trim() + " (" + cbxCategorie.getValue() + ")" + ", " + txtTarif.getText().trim());
			Produit produit = new Produit(nom, description, tarif, "visuel", itemCategorie.getId());
			this.labelProduit.setTextFill(Color.BLACK);
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
