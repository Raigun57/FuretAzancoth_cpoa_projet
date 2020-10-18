package application.controleur;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Client;

public class CtrlDonneesClient implements Initializable, ChangeListener<Client> {

	@FXML
	private TableView<Client> tabViewClient;
	@FXML
	private TableColumn<Client, Integer> colId = new TableColumn<>("ID");
	@FXML
	private TableColumn<Client, String> colNom = new TableColumn<>("Nom");
	@FXML
	private TableColumn<Client, String> colPrenom = new TableColumn<>("Prenom");
	@FXML
	private TableColumn<Client, String> colIdentifiant = new TableColumn<>("Identifiant");
	@FXML
	private TableColumn<Client, String> colMdp = new TableColumn<>("Mot de passe");
	@FXML
	private TableColumn<Client, String> colAdresse = new TableColumn<>("Adresse");
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	@Override
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		// Initiliasiation des colonnes
		colId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		colIdentifiant.setCellValueFactory(new PropertyValueFactory<Client, String>("identifiant"));
		colMdp.setCellValueFactory(new PropertyValueFactory<Client, String>("mdp"));
		// colAdresse.setCellValueFactory(new PropertyValueFactory<Client, Button>(""));
		// Initialisation de la table categorie
		tabViewClient.getColumns().setAll(colId, colNom, colPrenom, colIdentifiant, colMdp);

		try {
			tabViewClient.getItems()
					.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().findAll());
		} catch (SQLException e) {
			e.getMessage();
		}

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewClient.getSelectionModel().selectedItemProperty().addListener(this);

	}

	public void modifModele() {

	}

	public void suppModele() {

	}

	@Override
	public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

}
