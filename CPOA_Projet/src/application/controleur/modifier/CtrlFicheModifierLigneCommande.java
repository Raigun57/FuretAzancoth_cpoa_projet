package application.controleur.modifier;

import java.util.HashMap;
import java.util.Map;

import dao.Persistance;
import dao.factory.DAOFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.Commande;
import metier.LigneCommande;
import metier.Produit;

public class CtrlFicheModifierLigneCommande {

	@FXML
	private TextField txtQuantite;
	@FXML
	private Label labelLigneCommande;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnRetour;

	private int id;
	private LigneCommande ligneCommande = null;

	@SuppressWarnings("rawtypes")
	@FXML
	public void valider() {
		DAOFactory daoLM = DAOFactory.getDAOFactory(Persistance.ListeMemoire);

		int quantite = 0;
		boolean ok = true;

		try {
			quantite = Integer.parseInt(txtQuantite.getText().trim());
		} catch (NumberFormatException | NullPointerException e) {
			this.labelLigneCommande.setText("La quantite n'est pas numerique ou la quantite est vide");
			this.labelLigneCommande.setTextFill(Color.RED);
			ok = false;
		}

		if (ok == true) {
			LigneCommande nouvLC = new LigneCommande(ligneCommande.getIdProduit(), quantite,
					ligneCommande.getTarifUnitaire());

			try {
				Commande commande = null;
				commande = daoLM.getCommandeDAO().getById(id);
				HashMap<Produit, LigneCommande> map = daoLM.getCommandeDAO().getById(id).getListeLigneCommande();

				for (Map.Entry mapentry : map.entrySet()) {
					if (ligneCommande == mapentry.getValue()) {
						daoLM.getLigneCommandeDAO().update(nouvLC);
						map.replace((Produit) mapentry.getKey(), nouvLC);
					}
				}

				Commande c = new Commande(commande.getIdCommande(), commande.getDate(), commande.getIdClient(), map);
				daoLM.getCommandeDAO().update(c);

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

	public void initDonnees(LigneCommande lc) {
		txtQuantite.setText(Integer.toString(lc.getQuantite()));
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLC(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

}
