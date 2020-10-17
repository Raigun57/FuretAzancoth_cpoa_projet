package application.controleur;

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
import metier.Categorie;
import metier.Produit;

public class CtrlProduit implements Initializable {

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
	private Button btnAjouter;
	@FXML
	private Button btnModifier;

	@FXML
	public void creerModele() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nom = txtNom.getText().trim();
		String description = txtDescription.getText().trim();
		Double tarif = (double) 0;
		Categorie itemCategorie = cbxCategorie.getSelectionModel().getSelectedItem();
		boolean ok = true;

		if (nom.isEmpty()) {
			this.labelProduit.setText("Le nom est vide");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (description.isEmpty()) {
			this.labelProduit.setText("La description est vide");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (cbxCategorie.getSelectionModel().isEmpty()) {
			this.labelProduit.setText("La categorie n'est pas choisie");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		try {
			tarif = Double.parseDouble(txtTarif.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelProduit.setText("Le tarif n'est pas numerique ou le tarif est vide");
			this.labelProduit.setTextFill(Color.web("#bb0b0b"));
			ok = false;
		}

		if (ok == true) {
			this.labelProduit.setText(toString());
			Produit produit = new Produit(nom, description, tarif, "visuel", itemCategorie.getId());
			try {
				/*
				 * daoLM.getProduitDAO().create(produit);
				 * daoMySQL.getProduitDAO().create(produit);
				 * System.out.println(daoLM.getProduitDAO().findAll());
				 * System.out.println(daoMySQL.getProduitDAO().findAll());
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void modifModele() {

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

	@Override
	public String toString() {
		return txtNom.getText().trim() + " (" + cbxCategorie.getValue() + ")" + ", " + txtTarif.getText().trim();
	}

}
