package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controleur.CtrlDetailProduit;
import application.controleur.fiche.CtrlFicheProduit;
import application.controleur.modifier.CtrlFicheModifierProduit;
import dao.factory.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	@FXML
	private TextField txtRechNom;

	@FXML
	private ChoiceBox<String> cbxPersistance;

	private ObservableList<String> persistance = FXCollections.observableArrayList("MySQL", "Liste memoire");

	private final ObservableList<Produit> liste = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialisation des colonnes
		colId.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
		colTarif.setCellValueFactory(new PropertyValueFactory<Produit, Double>("tarif"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Produit, String>("visuel"));
		/*
		 * colIdCateg.setCellValueFactory(new Callback<CellDataFeatures<Produit,
		 * String>, ObservableValue<String>>() {
		 * 
		 * @Override public ObservableValue<String> call(CellDataFeatures<Produit,
		 * String> param) { try { return new
		 * ReadOnlyStringWrapper(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire)
		 * .getCategorieDAO().getById(param.getValue().getIdCateg()).getTitre()); }
		 * catch (SQLException e) { e.printStackTrace(); } return null; } });
		 */

		// Persistance
		try {
			cbxPersistance.setItems(persistance);
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}

		cbxPersistance.getSelectionModel().selectFirst();

		if (cbxPersistance.getSelectionModel().getSelectedIndex() == 0)
			refresh();
		else if (cbxPersistance.getSelectionModel().getSelectedIndex() == 1)
			refresh();

		cbxPersistance.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (cbxPersistance.getSelectionModel().getSelectedIndex() == 0)
				refresh();
			else if (cbxPersistance.getSelectionModel().getSelectedIndex() == 1)
				refresh();
		});

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewProduit.getSelectionModel().selectedItemProperty().addListener(this);
		this.tabViewProduit.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
			Node source = evt.getPickResult().getIntersectedNode();

			while (source != null && !(source instanceof TableRow)) {
				source = source.getParent();
			}
			// Si la selection est nulle (ligne vide) alors on nettoie la selection
			if (source == null || (source instanceof TableRow && ((TableRow<?>) source).isEmpty())) {
				tabViewProduit.getSelectionModel().clearSelection();
			}
		});

		detail();
		filtre();

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
			controleur.setIndexPersistance(getCbxPersistanceIndex());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modifier une categorie");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void ajouterProduit() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheProduit.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			// Appelle du controleur de la fiche ajouter
			CtrlFicheProduit controleur = fxmlLoader.getController();
			controleur.setIndexPersistance(getCbxPersistanceIndex());
			controleur.initDonnees();

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter un produit");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void suppProduit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'un produit");
		alert.setContentText("Voulez vous vraiment supprimer ce produit ?");
		Optional<ButtonType> result = alert.showAndWait();

		DAOFactory daoSQL = DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
		DAOFactory daoLM = DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire);

		Produit produit = tabViewProduit.getSelectionModel().getSelectedItem();
		if (result.get() == ButtonType.OK) {

			try {
				if (getCbxPersistanceIndex() == 0) {
					daoSQL.getProduitDAO().delete(produit);
					refresh();
				} else if (getCbxPersistanceIndex() == 1) {
					daoLM.getProduitDAO().delete(produit);
					refresh();
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		} else
			tabViewProduit.getSelectionModel().clearSelection();
	}

	@Override
	public void changed(ObservableValue<? extends Produit> observable, Produit oldValue, Produit newValue) {
		this.btnAjouterProduit.setDisable(newValue != null);
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

	private void detail() {
		tabViewProduit.setOnMouseClicked(event -> {
			if (tabViewProduit.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {

				try {
					URL fxmlURL = getClass().getResource("/fxml/DetailProduit.fxml");
					FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
					Parent root = fxmlLoader.load();

					Stage stage = new Stage();
					String nom = tabViewProduit.getSelectionModel().getSelectedItem().getNom();

					CtrlDetailProduit controleur = fxmlLoader.getController();
					controleur.initDonnees(tabViewProduit.getSelectionModel().getSelectedItem());

					stage.initModality(Modality.NONE);
					stage.setTitle("Detail du produit " + nom);
					stage.setScene(new Scene(root, 600, 400));
					stage.show();
				} catch (IOException e) {
					e.getMessage();
				}

			}
		});
	}

	private void filtre() {
		liste.addAll(tabViewProduit.getItems());
		FilteredList<Produit> produitFiltre = new FilteredList<Produit>(liste);

		txtRechNom.textProperty().addListener((observable, oldValue, newValue) -> {
			produitFiltre.setPredicate(produit -> {
				// Si le text field est vide, on montre tous les produits
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(produit.getTarif()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false;
			});
		});

		SortedList<Produit> sortedData = new SortedList<>(produitFiltre);
		sortedData.comparatorProperty().bind(tabViewProduit.comparatorProperty());
		tabViewProduit.setItems(sortedData);
	}

	private void refresh() {
		FilteredList<Produit> produitFiltre = null;
		try {
			if (getCbxPersistanceIndex() == 0)
				produitFiltre = new FilteredList<Produit>(FXCollections.observableArrayList(
						DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getProduitDAO().findAll()));
			else if (getCbxPersistanceIndex() == 1)
				produitFiltre = new FilteredList<Produit>(FXCollections.observableArrayList(
						DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().findAll()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SortedList<Produit> sortedData = new SortedList<>(produitFiltre);
		tabViewProduit.setItems(sortedData);
		tabViewProduit.refresh();
	}

	public int getCbxPersistanceIndex() {
		return cbxPersistance.getSelectionModel().getSelectedIndex();
	}

}
