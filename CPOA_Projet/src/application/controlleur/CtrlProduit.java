package application.controlleur;

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
import metier.Categorie;

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
	private Button btnCreer;
	
	@FXML
	public void creerModele() {
		this.labelProduit.setText(toString());
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
		return txtNom.getText() + " (" + cbxCategorie.getValue() + ")" + ", " + txtTarif.getText() + " €";
	}
	
}
