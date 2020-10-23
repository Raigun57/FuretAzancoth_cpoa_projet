package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controleur.modifier.CtrlFicheModifierProduit;
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
	private TableColumn<Produit, String> colIdCateg = new TableColumn<>("Categorie");
	@FXML
	private Button btnAjouterProduit;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialisation des colonnes
		colId.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
		colTarif.setCellValueFactory(new PropertyValueFactory<Produit, Double>("tarif"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Produit, String>("visuel"));
		colIdCateg.setCellValueFactory(new Callback<CellDataFeatures<Produit, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Produit, String> param) {
				try {
					return new ReadOnlyStringWrapper(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire)
							.getCategorieDAO().getById(param.getValue().getIdCateg()).getTitre());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
		});

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

	@FXML
	public void modifProduit() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/modifier/FicheModifierProduit.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			// Appelle du controleur de la fiche modifier
			CtrlFicheModifierProduit controleur = fxmlLoader.getController();

			// Initialisation des composants avec les donn�es de la ligne r�cup�rer
			controleur.initDonnees(tabViewProduit.getSelectionModel().getSelectedItem());
			controleur.setSelectedId(getTabViewProduit().getSelectionModel().getSelectedItem().getId());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modifier une categorie");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewProduit.getItems().clear();
				// Rerempli la table de donnees
				tabViewProduit.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().findAll());
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
	public void ajouterProduit() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheProduit.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter un produit");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				// Vide la table de donnees
				tabViewProduit.getItems().clear();
				// Rerempli la table de donnees
				tabViewProduit.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().findAll());
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void suppProduit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'un produit");
		alert.setContentText("Voulez vous vraiment supprimer ce produit ?");
		Optional<ButtonType> result = alert.showAndWait();

		Produit produit = tabViewProduit.getSelectionModel().getSelectedItem();
		if (result.get() == ButtonType.OK) {
			try {
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().delete(produit);
				// Vide la table de donnees
				tabViewProduit.getItems().clear();
				// Rerempli la table de donnees
				tabViewProduit.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().findAll());
			} catch (SQLException e) {
				e.getMessage();
			}
		} else
			tabViewProduit.getSelectionModel().clearSelection();
	}

	@Override
	public void changed(ObservableValue<? extends Produit> observable, Produit oldValue, Produit newValue) {
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

	// Methode pour recuperer le nom de toutes les produits
	public ArrayList<String> getNom() {
		ArrayList<String> listeNom = new ArrayList<String>();
		for (int i = 0; i < tabViewProduit.getItems().size(); i++) {
			listeNom.add(tabViewProduit.getItems().get(i).getNom());
		}
		return listeNom;
	}

	// Methode pour recuperer la categorie de tous les produits
	public ArrayList<Integer> getIdCateg() {
		ArrayList<Integer> listeNom = new ArrayList<Integer>();
		for (int i = 0; i < tabViewProduit.getItems().size(); i++) {
			listeNom.add(tabViewProduit.getItems().get(i).getIdCateg());
		}
		return listeNom;
	}

	// Methode pour donner la table des produits a la fiche ajouter en laissant la
	// table en privee
	public TableView<Produit> getTabViewProduit() {
		return tabViewProduit;
	}

}
