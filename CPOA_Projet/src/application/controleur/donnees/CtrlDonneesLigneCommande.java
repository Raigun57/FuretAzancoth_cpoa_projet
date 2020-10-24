package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import metier.Commande;
import metier.LigneCommande;
import metier.Produit;

public class CtrlDonneesLigneCommande implements Initializable, ChangeListener<LigneCommande> {

	@FXML
	private TableView<LigneCommande> tabViewLigneCommande;
	@FXML
	private TableColumn<LigneCommande, String> colIdProduit = new TableColumn<>("ID Produit");
	@FXML
	private TableColumn<LigneCommande, Integer> colQuantite = new TableColumn<>("Quantite");
	@FXML
	private TableColumn<LigneCommande, Double> colTarif = new TableColumn<>("Tarif");
	@FXML
	private Button btnAjouterLigneCommande;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	@FXML
	public void modifLigneCommande() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/modifier/FicheModifierCommande.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			// Appelle du controleur de la fiche modifier
			CtrlFicheModifierCommande controleur = fxmlLoader.getController();

			// Initialisation des composants avec les donnees de la ligne recuperee
			// controleur.initDonnees(tabViewLigneCommande.getSelectionModel().getSelectedItem());
			// controleur.setSelectedId(tabViewLigneCommande().getSelectionModel().getSelectedItem().getIdCommande());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modifier une ligne de commande");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewLigneCommande.getItems().clear();
				// Rerempli la table de donnees
				tabViewLigneCommande.getItems().addAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void ajouterLigneCommande() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheLigneCommande.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter une ligne de commande");
			stage.setScene(new Scene(root, 480, 240));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewLigneCommande.getItems().clear();
				// Rerempli la table de donnees
				tabViewLigneCommande.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().findAll());
			} catch (Exception e) {
				e.getMessage();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void suppLigneCommande() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'une ligne de commande");
		alert.setContentText("Voulez vous vraiment supprimer cette ligne de commande ?");
		Optional<ButtonType> result = alert.showAndWait();

		LigneCommande lc = tabViewLigneCommande.getSelectionModel().getSelectedItem();
		if (result.get() == ButtonType.OK) {
			try {
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().delete(lc);
				// Vide la table de donnees
				// tabViewLigneCommande.getItems().clear();
				// Rerempli la table de donnees
				// tabViewLigneCommande.getItems().addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().findAll());
			} catch (Exception e) {
				e.getMessage();
			}
		} else
			tabViewLigneCommande.getSelectionModel().clearSelection();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialisation des colonnes
		colIdProduit
				.setCellValueFactory(new Callback<CellDataFeatures<LigneCommande, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<LigneCommande, String> param) {
						try {
							return new ReadOnlyStringWrapper(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire)
									.getProduitDAO().getById(param.getValue().getIdProduit()).getNom());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return null;
					}
				});

		colQuantite.setCellValueFactory(new PropertyValueFactory<LigneCommande, Integer>("quantite"));
		colTarif.setCellValueFactory(new PropertyValueFactory<LigneCommande, Double>("tarifUnitaire"));

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewLigneCommande.getSelectionModel().selectedItemProperty().addListener(this);
		this.tabViewLigneCommande.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
			Node source = evt.getPickResult().getIntersectedNode();

			while (source != null && !(source instanceof TableRow)) {
				source = source.getParent();
			}
			// Si le selection est null (ligne vide) alors on nettoie la selection
			if (source == null || (source instanceof TableRow && ((TableRow<?>) source).isEmpty())) {
				tabViewLigneCommande.getSelectionModel().clearSelection();
			}
		});
	}

	@Override
	public void changed(ObservableValue<? extends LigneCommande> observable, LigneCommande oldValue,
			LigneCommande newValue) {
		this.btnAjouterLigneCommande.setDisable(newValue != null);
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public void initDonnees(int id) {
		try {
			Commande commande = null;
			commande = dao.factory.DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().getById(id);
			HashMap<Produit, LigneCommande> map = dao.factory.DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire)
					.getCommandeDAO().getById(id).getListeLigneCommande();
			for (Map.Entry mapentry : map.entrySet()) {
				tabViewLigneCommande.getItems().add((LigneCommande) mapentry.getValue());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public TableView<LigneCommande> getTabViewLigneCommande() {
		return tabViewLigneCommande;
	}

	// Methode pour recuperer le produit de toutes les lignes de commandes
	public ArrayList<Integer> getProduit() {
		ArrayList<Integer> listeProduit = new ArrayList<Integer>();
		for (int i = 0; i < tabViewLigneCommande.getItems().size(); i++) {
			listeProduit.add(tabViewLigneCommande.getItems().get(i).getIdProduit());
		}
		return listeProduit;
	}

}
