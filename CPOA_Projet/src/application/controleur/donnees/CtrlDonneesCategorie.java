package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

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
import metier.Categorie;

public class CtrlDonneesCategorie implements Initializable, ChangeListener<Categorie> {

	@FXML
	private TableView<Categorie> tabViewCategorie;
	@FXML
	private TableColumn<Categorie, Integer> colId = new TableColumn<>("ID");
	@FXML
	private TableColumn<Categorie, String> colNom = new TableColumn<>("Nom");
	@FXML
	private TableColumn<Categorie, String> colVisuel = new TableColumn<>("Visuel");
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	@Override
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		// Initiliasiation des colonnes
		colId.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));
		// Initialisation de la table categorie
		tabViewCategorie.getColumns().setAll(colId, colNom, colVisuel);

		try {
			tabViewCategorie.getItems()
					.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().findAll());
		} catch (SQLException e) {
			e.getMessage();
		}

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewCategorie.getSelectionModel().selectedItemProperty().addListener(this);

	}

	@FXML
	public void ajouterCategorie() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheCategorie.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter une categorie");
			stage.setScene(new Scene(root, 600, 400));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifModele() {

	}

	@FXML
	public void suppModele() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'une categorie");
		alert.setContentText("Voulez vous vraiment supprimer ce categorie ?");
		Optional<ButtonType> result = alert.showAndWait();

		Categorie categ = tabViewCategorie.getSelectionModel().getSelectedItem();
		if (result.get() == ButtonType.OK) {
			try {
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().delete(categ);
				// Vide la table de donnees
				tabViewCategorie.getItems().clear();
				// Rerempli la table de donnees
				tabViewCategorie.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().findAll());
			} catch (SQLException e) {
				e.getMessage();
			}
		} else
			tabViewCategorie.getSelectionModel().clearSelection();
	}

	@Override
	public void changed(ObservableValue<? extends Categorie> observable, Categorie oldValue, Categorie newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

}
