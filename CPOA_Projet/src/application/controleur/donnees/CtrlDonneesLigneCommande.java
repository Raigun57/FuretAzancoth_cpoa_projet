package application.controleur.donnees;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import dao.factory.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Commande;
import metier.LigneCommande;
import metier.Produit;

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

	}
	
	public void initDonnees(int id) {
		try {	
			Commande commande = null;
			
			try {
				commande = dao.factory.DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().getById(id);
			} catch(Exception e) {
				
			}
			HashMap<Produit, LigneCommande> map = dao.factory.DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().getById(id).getListeLigneCommande();
			 for (Map.Entry mapentry : map.entrySet()) {
				 tabViewLigneCommande.getItems().add((LigneCommande) mapentry.getValue());
		        }
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
