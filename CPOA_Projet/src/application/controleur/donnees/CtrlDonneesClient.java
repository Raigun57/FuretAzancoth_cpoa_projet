package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controleur.modifier.CtrlFicheModifierClient;
import dao.factory.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

	@FXML
	public void ajouterClient() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheClient.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter un client");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewClient.getItems().clear();
				// Rerempli la table de donnees
				tabViewClient.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().findAll());
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void modifClient() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/modifier/FicheModifierClient.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			// Appelle du controleur de la fiche modifier
			CtrlFicheModifierClient controleur = fxmlLoader.getController();

			// Initialisation des composants avec les données de la ligne récupérer
			controleur.initDonnees(tabViewClient.getSelectionModel().getSelectedItem());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modifier une categorie");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewClient.getItems().clear();
				// Rerempli la table de donnees
				tabViewClient.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().findAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void suppClient() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'un client");
		alert.setContentText("Voulez vous vraiment supprimer ce client ?");
		Optional<ButtonType> result = alert.showAndWait();

		Client client = tabViewClient.getSelectionModel().getSelectedItem();
		if (result.get() == ButtonType.OK) {
			try {
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().delete(client);
				// Vide la table de donnees
				tabViewClient.getItems().clear();
				// Rerempli la table de donnees
				tabViewClient.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().findAll());
			} catch (SQLException e) {
				e.getMessage();
			}
		} else
			tabViewClient.getSelectionModel().clearSelection();
	}

	@Override
	public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

}
