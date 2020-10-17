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

	public void modifModele() {

	}

	public void suppModele() {

	}

	@Override
	public void changed(ObservableValue<? extends Categorie> observable, Categorie oldValue, Categorie newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

}
