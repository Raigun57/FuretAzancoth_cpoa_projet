package application.controleur.modifier;

import java.net.URL;
import java.util.ResourceBundle;

import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.Categorie;
import metier.Produit;

public class CtrlFicheModifierProduit implements Initializable {

	@FXML
	private TextField txtNom;
	@FXML
	private TextArea txtDescription;
	@FXML
	private TextField txtTarif;
	@FXML
	private ChoiceBox<Categorie> cbxCategorie;
	@FXML
	private Label labelProduit;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;

	@FXML
	public void valider() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nom = txtNom.getText().trim();
		String description = txtDescription.getText().trim();
		Double tarif = (double) 0;
		Categorie itemCategorie = cbxCategorie.getSelectionModel().getSelectedItem();
		boolean ok = true;

		if (nom.isEmpty()) {
			this.labelProduit.setText("Le nom est vide");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		if (description.isEmpty()) {
			this.labelProduit.setText("La description est vide");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		if (cbxCategorie.getSelectionModel().isEmpty()) {
			this.labelProduit.setText("La categorie n'est pas choisie");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		try {
			tarif = Double.parseDouble(txtTarif.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelProduit.setText("Le tarif n'est pas numerique ou le tarif est vide");
			this.labelProduit.setTextFill(Color.RED);
			ok = false;
		}

		if (ok == true) {
			this.labelProduit.setText(
					txtNom.getText().trim() + " (" + cbxCategorie.getValue() + ")" + ", " + txtTarif.getText().trim());
			Produit produit = new Produit(nom, description, tarif, "visuel", itemCategorie.getId());
			this.labelProduit.setTextFill(Color.BLACK);
			try {
				daoLM.getProduitDAO().create(produit);
				// daoMySQL.getProduitDAO().create(produit);
				Stage stage = (Stage) btnValider.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// Ferme la boite de dialogue
	@FXML
	public void retour() {
		Stage stage = (Stage) btnRetour.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		try {
			this.cbxCategorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
		} catch (Exception e) {
			System.out.println("Probleme avec la ChoiceBox");
			e.printStackTrace();
		}
	}

	// methode qui initialise les donnees
	public void initDonnees(Produit p) {
		txtNom.setText(p.getNom());
		txtDescription.setText(p.getDescription());
		Double tarif = p.getTarif();
		txtTarif.setText(tarif.toString());
		cbxCategorie.getSelectionModel().select(p.getIdCateg() - 1); // On doit enlever 1 sinon l'index n'est pas
																		// equivalent a l'objet selectionné
	}

}
