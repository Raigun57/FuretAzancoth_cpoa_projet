	package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controleur.CtrlDetailAdresseClient;
import application.controleur.fiche.CtrlFicheClient;
import application.controleur.modifier.CtrlFicheModifierClient;
import dao.factory.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	private Button btnAjouterClient;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private TextField txtRechNomPrenom;

	@FXML
	private ChoiceBox<String> cbxPersistance;

	private ObservableList<String> persistance = FXCollections.observableArrayList("MySQL", "Liste memoire");

	private final ObservableList<Client> liste = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialisation des colonnes
		colId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));

		// Persistance
		try {
			cbxPersistance.setItems(persistance);
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}

		cbxPersistance.getSelectionModel().selectFirst();

		if (cbxPersistance.getSelectionModel().getSelectedIndex() == 0)
			refresh();
		else if (cbxPersistance.getSelectionModel().getSelectedIndex() == 1)
			refresh();

		cbxPersistance.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (cbxPersistance.getSelectionModel().getSelectedIndex() == 0)
				refresh();
			else if (cbxPersistance.getSelectionModel().getSelectedIndex() == 1)
				refresh();
		});

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewClient.getSelectionModel().selectedItemProperty().addListener(this);
		this.tabViewClient.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
			Node source = evt.getPickResult().getIntersectedNode();

			while (source != null && !(source instanceof TableRow)) {
				source = source.getParent();
			}
			// Si le selection est null (ligne vide) alors on nettoie la selection
			if (source == null || (source instanceof TableRow && ((TableRow<?>) source).isEmpty())) {
				tabViewClient.getSelectionModel().clearSelection();
			}
		});

		detail();
		filtre();
	}

	@FXML
	public void ajouterClient() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheClient.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			// Appelle du controleur de la fiche ajouter
			CtrlFicheClient controleur = fxmlLoader.getController();
			controleur.setIndexPersistance(getCbxPersistanceIndex());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter un client");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			refresh();
		} catch (IOException e) {
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

			controleur.setIndexPersistance(getCbxPersistanceIndex());
			// Initialisation des composants avec les donn�es de la ligne r�cup�rer
			controleur.initDonnees(tabViewClient.getSelectionModel().getSelectedItem());
			controleur.setSelectedId(getTabViewClient().getSelectionModel().getSelectedItem().getId());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modifier une categorie");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			refresh();
		} catch (IOException e) {
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
				if (getCbxPersistanceIndex() == 0) {
					DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getClientDAO().delete(client);
					refresh();
				} else if (getCbxPersistanceIndex() == 1) {
					DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().delete(client);
					refresh();
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		} else
			tabViewClient.getSelectionModel().clearSelection();
	}

	@Override
	public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
		this.btnAjouterClient.setDisable(newValue != null);
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

	// Methode pour recuperer l'identifiant de tous les clients
	public ArrayList<String> getIdentifiant() {
		ArrayList<String> listeIdentifiant = new ArrayList<String>();
		for (int i = 0; i < tabViewClient.getItems().size(); i++) {
			listeIdentifiant.add(tabViewClient.getItems().get(i).getIdentifiant());
		}
		return listeIdentifiant;
	}

	// Methode pour donner la table des categories a la fiche ajouter en laissant la
	// table en privee
	public TableView<Client> getTabViewClient() {
		return tabViewClient;
	}

	private void detail() {
		tabViewClient.setOnMouseClicked(event -> {
			if (tabViewClient.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {

				try {
					URL fxmlURL = getClass().getResource("/fxml/DetailAdresseClient.fxml");
					FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
					Parent root = fxmlLoader.load();

					Stage stage = new Stage();
					String nomPrenom = tabViewClient.getSelectionModel().getSelectedItem().getPrenomNom();

					CtrlDetailAdresseClient controleur = fxmlLoader.getController();
					controleur.initDonnees(tabViewClient.getSelectionModel().getSelectedItem());

					stage.initModality(Modality.NONE);
					stage.setTitle("Detail de l'adresse de " + nomPrenom);
					stage.setScene(new Scene(root, 600, 400));
					stage.show();
				} catch (IOException e) {
					e.getMessage();
				}

			}
		});
	}

	private void filtre() {
		liste.addAll(tabViewClient.getItems());
		FilteredList<Client> clientFiltre = new FilteredList<Client>(liste);

		txtRechNomPrenom.textProperty().addListener((observable, oldValue, newValue) -> {
			clientFiltre.setPredicate(client -> {
				// Si le text field est vide, on montre tous les clients
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (client.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (client.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (client.getNomPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (client.getPrenomNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});

		SortedList<Client> sortedData = new SortedList<>(clientFiltre);
		sortedData.comparatorProperty().bind(tabViewClient.comparatorProperty());
		tabViewClient.setItems(sortedData);
	}

	private void refresh() {
		FilteredList<Client> clientFiltre = null;
		try {
			if (getCbxPersistanceIndex() == 0)
				clientFiltre = new FilteredList<Client>(FXCollections
						.observableArrayList(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getClientDAO().findAll()));
			else if (getCbxPersistanceIndex() == 1)
				clientFiltre = new FilteredList<Client>(FXCollections.observableArrayList(
						DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().findAll()));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		SortedList<Client> sortedData = new SortedList<>(clientFiltre);
		tabViewClient.setItems(sortedData);
		tabViewClient.refresh();
	}

	public int getCbxPersistanceIndex() {
		return cbxPersistance.getSelectionModel().getSelectedIndex();
	}

}
