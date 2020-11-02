package application.controleur.donnees;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controleur.fiche.CtrlFicheCategorie;
import application.controleur.modifier.CtrlFicheModifierCategorie;
import dao.factory.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	private Button btnAjouterCategorie;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;
	@FXML
	private ChoiceBox<String> cbxPersistance;

	private ObservableList<String> persistance = FXCollections.observableArrayList("MySQL", "Liste memoire");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialisation des colonnes
		colId.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("id"));
		colNom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));
		colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));

		// Persistance
		try {
			cbxPersistance.setItems(persistance);
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}

		cbxPersistance.getSelectionModel().selectFirst();

		try {
			if (cbxPersistance.getSelectionModel().getSelectedIndex() == 0)
				tabViewCategorie.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().findAll());
			else if (cbxPersistance.getSelectionModel().getSelectedIndex() == 1)
				tabViewCategorie.getItems()
						.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().findAll());

		} catch (SQLException e) {
			e.getMessage();
		}

		cbxPersistance.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (cbxPersistance.getSelectionModel().getSelectedIndex() == 1) {
					tabViewCategorie.getItems().clear();
					tabViewCategorie.getItems()
							.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().findAll());
				} else if (cbxPersistance.getSelectionModel().getSelectedIndex() == 0) {
					tabViewCategorie.getItems().clear();
					tabViewCategorie.getItems()
							.addAll(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().findAll());
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		});

		btnSupprimer.setDisable(true);
		btnModifier.setDisable(true);

		this.tabViewCategorie.getSelectionModel().selectedItemProperty().addListener(this);
		this.tabViewCategorie.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
			Node source = evt.getPickResult().getIntersectedNode();

			while (source != null && !(source instanceof TableRow)) {
				source = source.getParent();
			}
			// Si le selection est null (ligne vide) alors on nettoie la selection
			if (source == null || (source instanceof TableRow && ((TableRow<?>) source).isEmpty())) {
				tabViewCategorie.getSelectionModel().clearSelection();
			}
		});

	}

	@FXML
	public void ajouterCategorie() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/fiche/FicheCategorie.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			CtrlFicheCategorie controleur = fxmlLoader.getController();
			controleur.setIndexPersistance(getCbxPersistanceIndex());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Ajouter une categorie");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				if (getCbxPersistanceIndex() == 1) {
					// Vide la table de donnees
					tabViewCategorie.getItems().clear();
					// Rerempli la table de donnees
					tabViewCategorie.getItems()
							.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().findAll());
				} else if (getCbxPersistanceIndex() == 0) {
					// Vide la table de donnees
					tabViewCategorie.getItems().clear();
					// Rerempli la table de donnees
					tabViewCategorie.getItems()
							.addAll(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().findAll());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void modifCategorie() {
		try {
			URL fxmlURL = getClass().getResource("/fxml/modifier/FicheModifierCategorie.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = fxmlLoader.load();

			// Appelle du controleur de la fiche modifier
			CtrlFicheModifierCategorie controleur = fxmlLoader.getController();

			// Initialisation des composants avec les donnï¿½es de la ligne rï¿½cupï¿½rer
			controleur.initDonnees(tabViewCategorie.getSelectionModel().getSelectedItem());
			controleur.setSelectedId(getTabViewCategorie().getSelectionModel().getSelectedItem().getId());
			controleur.setIndexPersistance(getCbxPersistanceIndex());

			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modifier une categorie");
			stage.setScene(new Scene(root, 600, 400));
			stage.showAndWait(); // Permet, avec le code suivant, de rafraichir la table de donnees

			try {
				if (getCbxPersistanceIndex() == 1) {
					// Vide la table de donnees
					tabViewCategorie.getItems().clear();
					// Rerempli la table de donnees
					tabViewCategorie.getItems()
							.addAll(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().findAll());
				} else if (getCbxPersistanceIndex() == 0) {
					// Vide la table de donnees
					tabViewCategorie.getItems().clear();
					// Rerempli la table de donnees
					tabViewCategorie.getItems()
							.addAll(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().findAll());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void suppCategorie() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire);
		DAOFactory daoSQL = DAOFactory.getDAOFactory(dao.Persistance.MYSQL);

		/*
		 * URL fxmlURL = getClass().getResource("/fxml/donnees/DonneesProduit.fxml");
		 * FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		 * 
		 * try { Parent root = fxmlLoader.load(); } catch (IOException e1) {
		 * e1.printStackTrace(); }
		 * 
		 * CtrlDonneesProduit controleur = fxmlLoader.getController(); Categorie categ =
		 * tabViewCategorie.getSelectionModel().getSelectedItem(); int idCateg =
		 * categ.getId(); boolean ok = true;
		 * 
		 * if (getCbxPersistanceIndex() == 0) { for (int i = 0; i <
		 * controleur.getIdCateg().size(); i++) {
		 * //controleur.setCbxPersistanceIndex(0); int prod =
		 * controleur.getIdCateg().get(i); if (idCateg == prod) { ok = false;
		 * alerteSuppCascade(); } } } else if (getCbxPersistanceIndex() == 1) {
		 * //controleur.setCbxPersistanceIndex(1); for (int i = 0; i <
		 * controleur.getIdCateg().size(); i++) { int prod =
		 * controleur.getIdCateg().get(i); if (idCateg == prod) { ok = false;
		 * alerteSuppCascade(); } } }
		 */

		Categorie categ = tabViewCategorie.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression d'une categorie");
		alert.setContentText("Voulez vous vraiment supprimer cette categorie ?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				if (getCbxPersistanceIndex() == 1) {
					daoLM.getCategorieDAO().delete(categ);
					// Vide la table de donnees
					tabViewCategorie.getItems().clear();
					// Rerempli la table de donnees
					tabViewCategorie.getItems().addAll(daoLM.getCategorieDAO().findAll());
				} else if (getCbxPersistanceIndex() == 0) {
					daoSQL.getCategorieDAO().delete(categ);
					// Vide la table de donnees
					tabViewCategorie.getItems().clear();
					// Rerempli la table de donnees
					tabViewCategorie.getItems().addAll(daoSQL.getCategorieDAO().findAll());
				}
			} catch (SQLException e) {
				e.getMessage();
			}
		} else
			tabViewCategorie.getSelectionModel().clearSelection();
	}

	@Override
	public void changed(ObservableValue<? extends Categorie> observable, Categorie oldValue, Categorie newValue) {
		this.btnAjouterCategorie.setDisable(newValue != null);
		this.btnSupprimer.setDisable(newValue == null);
		this.btnModifier.setDisable(newValue == null);
	}

	// Methode pour recuperer le titre de toutes les categories
	public ArrayList<String> getNom() {
		ArrayList<String> listeNom = new ArrayList<String>();
		for (int i = 0; i < tabViewCategorie.getItems().size(); i++) {
			listeNom.add(tabViewCategorie.getItems().get(i).getTitre());
		}
		return listeNom;
	}

	// Methode pour donner la table des categories a la fiche ajouter en laissant la
	// table en privee
	public TableView<Categorie> getTabViewCategorie() {
		return tabViewCategorie;
	}

	private int getCbxPersistanceIndex() {
		return cbxPersistance.getSelectionModel().getSelectedIndex();
	}

	/*
	 * private void alerteSuppCascade() { Alert alert = new
	 * Alert(AlertType.WARNING);
	 * alert.setTitle("Suppression d'une categorie utilisée dans un produit");
	 * alert.
	 * setContentText("Vous ne pouvez pas supprimer cette categorie car elle utilisée dans un produit"
	 * ); alert.show(); }
	 */

}
