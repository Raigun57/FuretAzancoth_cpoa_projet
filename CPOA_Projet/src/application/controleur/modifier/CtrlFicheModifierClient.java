package application.controleur.modifier;

import java.io.IOException;
import java.net.URL;

import application.controleur.donnees.CtrlDonneesClient;
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
import metier.Client;

public class CtrlFicheModifierClient {

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
	private Button btnValider;
	@FXML
	private Button btnRetour;
	@FXML
	private Label labelClient;

	private int id;

	@FXML
	public void valider() {
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

		try {
			URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesClient.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			CtrlDonneesClient controleur = fxmlLoader.getController();

			for (int i = 0; i < controleur.getTabViewClient().getItems().size(); i++) {
				if (identifiant.equalsIgnoreCase(controleur.getIdentifiant().get(i))) {
					alerteDoublon();
					ok = false;
				}
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (ok == true) {
			this.labelClient.setText(txtNomClient.getText().trim() + ", " + txtPrenom.getText().trim() + ", "
					+ txtIdentifiant.getText().trim() + txtMdp.getText().trim());
			this.labelClient.setTextFill(Color.BLACK);

			Client client = new Client(getSelectedId(), nomClient, prenom, identifiant, mdp, numero, rue, codePostal,
					ville, pays);

			try {
				daoLM.getClientDAO().update(client);
				// daoMySQL.getClientDAO().create(Client);
				Stage stage = (Stage) btnValider.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}

	}

	// Ferme la boite de dialogue
	@FXML
	public void retour() {
		Stage stage = (Stage) btnRetour.getScene().getWindow();
		stage.close();
	}

	// methode qui initialise les donnees
	public void initDonnees(Client c) {
		txtNomClient.setText(c.getNom());
		txtPrenom.setText(c.getPrenom());
		txtIdentifiant.setText(c.getIdentifiant());
		txtMdp.setText(c.getMdp());

		int numero = c.getNumero();
		txtNumero.setText(Integer.toString(numero));

		txtRue.setText(c.getRue());

		int codePostal = c.getCodePostal();
		txtCodePostal.setText(Integer.toString(codePostal));

		txtVille.setText(c.getVille());
		txtPays.setText(c.getPays());
	}

	// set l'id du client selectionne dans la table
	public int setSelectedId(int id) {
		return this.id = id;
	}

	// retourne l'id du client selectionnee
	public int getSelectedId() {
		return setSelectedId(id);
	}

	public void alerteDoublon() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Doublon");
		alert.setContentText("Ce client existe deja");
		alert.show();
	}

}
