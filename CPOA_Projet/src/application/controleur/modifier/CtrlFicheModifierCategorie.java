package application.controleur.modifier;

import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.Categorie;

public class CtrlFicheModifierCategorie {

	@FXML
	private TextField txtNomCateg;
	@FXML
	private TextField txtVisuelCateg;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;
	@FXML
	private Label labelCategorie;

	private int id;

	@FXML
	public void valider() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		DAOFactory daoMySQL = DAOFactory.getDAOFactory(Persistance.MYSQL);

		String nomCategorie = txtNomCateg.getText().trim();
		String visuelCategorie = txtVisuelCateg.getText().trim();
		boolean ok = true;

		if (nomCategorie.isEmpty()) {
			this.labelCategorie.setText("Le nom est vide");
			this.labelCategorie.setTextFill(Color.RED);
			ok = false;
		}

		if (visuelCategorie.isEmpty()) {
			this.labelCategorie.setText("Le visuel est vide");
			this.labelCategorie.setTextFill(Color.RED);
			ok = false;
		}

		if (ok == true) {
			this.labelCategorie.setText(txtNomCateg.getText().trim() + ", " + txtVisuelCateg.getText().trim());
			this.labelCategorie.setTextFill(Color.BLACK);

			Categorie categ = new Categorie(getSelectedId(), nomCategorie, visuelCategorie);

			try {
				daoLM.getCategorieDAO().update(categ);
				// daoMySQL.getProduitDAO().create(produit);
				Stage stage = (Stage) btnValider.getScene().getWindow();
				stage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void retour() {
		Stage stage = (Stage) btnRetour.getScene().getWindow();
		stage.close();
	}

	// methode qui initialise les donnees
	public void initDonnees(Categorie c) {
		txtNomCateg.setText(c.getTitre());
		txtVisuelCateg.setText(c.getVisuel());
	}

	// set l'id de la categorie selectionnee dans la table
	public int setSelectedId(int id) {
		return this.id = id;
	}

	// retourne l'id de la categorie selectionnee
	public int getSelectedId() {
		return setSelectedId(id);
	}

}
