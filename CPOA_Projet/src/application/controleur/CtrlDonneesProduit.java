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
import metier.Produit;

public class CtrlDonneesProduit implements Initializable, ChangeListener<Produit> {

	@FXML
	private TableView<Produit> tabViewProduit;
	@FXML
	private TableColumn<Produit, Integer> colId = new TableColumn<>("ID");
	@FXML
	private TableColumn<Produit, String> colNom = new TableColumn<>("Nom");
	@FXML
	private TableColumn<Produit, String> colDescription = new TableColumn<>("Description");
	@FXML
	private TableColumn<Produit, Double> colTarif = new TableColumn<>("Tarif");
	@FXML
	private TableColumn<Produit, String> colVisuel = new TableColumn<>("Visuel");
	@FXML
	private TableColumn<Produit, Integer> colIdCateg = new TableColumn<>("ID Categorie");
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	@Override
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		// Initiliasiation des colonnes
		colId.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
		colTarif.setCellValueFactory(new PropertyValueFactory<Produit, Double>("tarif"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Produit, String>("visuel"));
		colIdCateg.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idCateg"));
		// Initialisation de la table produit
		tabViewProduit.getColumns().setAll(colId, colNom, colDescription, colTarif, colVisuel, colIdCateg);

		try {
			tabViewProduit.getItems()
					.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().findAll());
		} catch (SQLException e) {
			e.getMessage();
		}

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewProduit.getSelectionModel().selectedItemProperty().addListener(this);

	}

	public void modifModele() {

	}

	public void suppModele() {

	}

	@Override
	public void changed(ObservableValue<? extends Produit> observable, Produit oldValue, Produit newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

}
