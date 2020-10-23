package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controleur.modifier.CtrlFicheModifierCommande;
import dao.factory.DAOFactory;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import metier.Commande;

public class CtrlDonneesCommande implements Initializable, ChangeListener<Commande> {

	@FXML
	private TableView<Commande> tabViewCommande;
	@FXML
	private TableColumn<Commande, Integer> colIdCommande = new TableColumn<>("ID Commande");
	@FXML
	private TableColumn<Commande, String> colDate = new TableColumn<>("Date");
	@FXML
	private TableColumn<Commande, String> colIdClient = new TableColumn<>("Client");
	@FXML
	private Button btnAjouterCommande;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initiliasiation des colonnes
		colIdCommande.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idCommande"));
		colDate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
		colIdClient.setCellValueFactory(new Callback<CellDataFeatures<Commande, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Commande, String> param) {
				try {
					return new ReadOnlyStringWrapper(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire)
							.getClientDAO().getById(param.getValue().getIdClient()).getNom());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});

		try {
			tabViewCommande.getItems()
					.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().findAll());
		} catch (SQLException e) {
			e.getMessage();
		}

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewCommande.getSelectionModel().selectedItemProperty().addListener(this);

		tabViewCommande.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {

				try {
					URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesLigneCommande.fxml");
					FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
					Parent root = fxmlLoader.load();

					Stage stage = new Stage();

					CtrlDonneesLigneCommande controleur = fxmlLoader.getController();
					controleur.initDonnees(tabViewCommande.getSelectionModel().getSelectedItem().getIdCommande());

					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle(
							"Commande n " + tabViewCommande.getSelectionModel().getSelectedItem().getIdCommande());
					stage.setScene(new Scene(root, 600, 400));
					stage.show();
				} catch (IOException e) {
					e.getMessage();
				}

			}
		});

	}

	@FXML
	public void modifCommande() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/modifier/FicheModifierCommande.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			// Appelle du controleur de la fiche modifier
			CtrlFicheModifierCommande controleur = fxmlLoader.getController();

			// Initialisation des composants avec les donn�es de la ligne r�cup�rer
			controleur.initDonnees(tabViewCommande.getSelectionModel().getSelectedItem());
			controleur.setSelectedId(getTabViewCommande().getSelectionModel().getSelectedItem().getIdCommande());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modifier une commande");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewCommande.getItems().clear();
				// Rerempli la table de donnees
				tabViewCommande.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().findAll());
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
	public void ajouterCommande() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheCommande.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter une commande");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewCommande.getItems().clear();
				// Rerempli la table de donnees
				tabViewCommande.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().findAll());
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void suppCommande() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'une commande");
		alert.setContentText("Voulez vous vraiment supprimer cette commande ?");
		Optional<ButtonType> result = alert.showAndWait();

		Commande categ = tabViewCommande.getSelectionModel().getSelectedItem();
		if (result.get() == ButtonType.OK) {
			try {
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().delete(categ);
				// Vide la table de donnees
				tabViewCommande.getItems().clear();
				// Rerempli la table de donnees
				tabViewCommande.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().findAll());
			} catch (SQLException e) {
				e.getMessage();
			}
		} else
			tabViewCommande.getSelectionModel().clearSelection();
	}

	@Override
	public void changed(ObservableValue<? extends Commande> observable, Commande oldValue, Commande newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

	// Methode pour recuperer la date de toutes les commandes
	public ArrayList<String> getDate() {
		ArrayList<String> listeDate = new ArrayList<String>();
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (int i = 0; i < tabViewCommande.getItems().size(); i++) {
			LocalDate date = tabViewCommande.getItems().get(i).getDate();
			listeDate.add(formatage.format(date));
		}
		return listeDate;
	}

	// Methode pour recuperer le client de toutes les commandes
	public ArrayList<Integer> getClient() {
		ArrayList<Integer> listeClient = new ArrayList<Integer>();
		for (int i = 0; i < tabViewCommande.getItems().size(); i++) {
			listeClient.add(tabViewCommande.getItems().get(i).getIdClient());
		}
		return listeClient;
	}

	// Methode pour donner la table des commandes a la fiche ajouter en laissant la
	// table en privee
	public TableView<Commande> getTabViewCommande() {
		return tabViewCommande;
	}

}
