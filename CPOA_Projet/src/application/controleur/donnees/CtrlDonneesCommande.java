package application.controleur.donnees;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

	@FXML
	public void suppModele() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'une commande");
		alert.setContentText("Voulez vous vraiment supprimer ce commande ?");
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

}
