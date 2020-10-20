package application.controleur.modifier;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.Client;
import metier.Commande;

public class CtrlFicheModifierCommande implements Initializable {

	@FXML
	private TextField txtDate;
	@FXML
	private ChoiceBox<Client> cbxClient;
	@FXML
	private Label labelCommande;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;

	@FXML
	public void valider() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String date = txtDate.getText().trim();
		LocalDate dateFormate = null;

		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Client itemClient = cbxClient.getSelectionModel().getSelectedItem();
		boolean ok = true;

		if (date.isEmpty()) {
			this.labelCommande.setText("La date est vide");
			this.labelCommande.setTextFill(Color.RED);
			ok = false;
		}

		try {
			dateFormate = LocalDate.parse(date, formatage);
		} catch (Exception e) {
			e.getMessage();
			this.labelCommande.setText("La date est mal ecrite");
			this.labelCommande.setTextFill(Color.RED);
			ok = false;
		}

		if (cbxClient.getSelectionModel().isEmpty()) {
			this.labelCommande.setText("Le client n'est pas choisi");
			this.labelCommande.setTextFill(Color.RED);
			ok = false;
		}

		if (ok == true) {
			this.labelCommande.setText(txtDate.getText().trim() + ", " + cbxClient.getValue());
			this.labelCommande.setTextFill(Color.BLACK);

			Commande commande = new Commande(dateFormate, itemClient.getId(), null);
			try {
				daoLM.getCommandeDAO().create(commande);
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
			this.cbxClient.setItems(FXCollections.observableArrayList(dao.getClientDAO().findAll()));
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}
	}

}
