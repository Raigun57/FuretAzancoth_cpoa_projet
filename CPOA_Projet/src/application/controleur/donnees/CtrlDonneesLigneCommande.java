package application.controleur.donnees;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.LigneCommande;

public class CtrlDonneesLigneCommande implements Initializable {

	@FXML
	private TableView<LigneCommande> tabViewLigneCommande;
	@FXML
	private TableColumn<LigneCommande, Integer> colIdProduit = new TableColumn<>("ID Produit");
	@FXML
	private TableColumn<LigneCommande, Integer> colQuantite = new TableColumn<>("Quantite");
	@FXML
	private TableColumn<LigneCommande, Double> colTarif = new TableColumn<>("Tarif");

	@Override
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		// Initiliasiation des colonnes
		colIdProduit.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("idProduit"));
		colQuantite.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("quantite"));
		colTarif.setCellValueFactory(new PropertyValueFactory<LigneCommande, Double>("tarifUnitaire"));
		// Initialisation de la table categorie
		tabViewLigneCommande.getColumns().setAll(colIdProduit, colQuantite, colTarif);

		try {
			tabViewLigneCommande.getItems()
					.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().findAll());
		} catch (SQLException e) {
			e.getMessage();
		}
	}

}
