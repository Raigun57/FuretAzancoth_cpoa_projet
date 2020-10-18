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
import metier.Commande;

public class CtrlDonneesCommande implements Initializable, ChangeListener<Commande> {

	@FXML
	private TableView<Commande> tabViewCommande;
	@FXML
	private TableColumn<Commande, Integer> colIdCommande = new TableColumn<>("ID Commande");
	@FXML
	private TableColumn<Commande, String> colDate = new TableColumn<>("Date");
	@FXML
	private TableColumn<Commande, Integer> colIdClient = new TableColumn<>("ID Client");
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	@Override
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		// Initiliasiation des colonnes
		colIdCommande.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idCommande"));
		colDate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
		colIdClient.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idClient"));
		// Initialisation de la table categorie
		tabViewCommande.getColumns().setAll(colIdCommande, colDate, colIdClient);

		try {
			tabViewCommande.getItems()
					.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().findAll());
		} catch (SQLException e) {
			e.getMessage();
		}

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewCommande.getSelectionModel().selectedItemProperty().addListener(this);

	}

	public void modifModele() {

	}

	public void suppModele() {

	}

	@Override
	public void changed(ObservableValue<? extends Commande> observable, Commande oldValue, Commande newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

}
