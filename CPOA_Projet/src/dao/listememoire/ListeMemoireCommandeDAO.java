package dao.listememoire;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.modele.CommandeDAO;
import metier.Commande;
import metier.LigneCommande;
import metier.Produit;

public class ListeMemoireCommandeDAO implements CommandeDAO {

	private static ListeMemoireCommandeDAO instance;

	private List<Commande> donnees;

	public static ListeMemoireCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireCommandeDAO() {

		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1 = LocalDate.parse("02/09/2020", formatage);
		LocalDate date2 = LocalDate.parse("30/08/2020", formatage);

		Produit prod2 = ListeMemoireProduitDAO.getInstance().getById(2);
		Produit prod6 = ListeMemoireProduitDAO.getInstance().getById(6);

		HashMap<Produit, LigneCommande> listeLigneCommande1 = new HashMap<Produit, LigneCommande>();
		HashMap<Produit, LigneCommande> listeLigneCommande2 = new HashMap<Produit, LigneCommande>();
		
		try {
			listeLigneCommande1.put(prod2, ListeMemoireLigneCommandeDAO.getInstance().getById(1, 2));
			listeLigneCommande1.put(prod6, ListeMemoireLigneCommandeDAO.getInstance().getById(1, 6));
			listeLigneCommande2.put(prod2, ListeMemoireLigneCommandeDAO.getInstance().getById(2, 12));
		} catch (SQLException e) {
			e.getMessage();
		}

		this.donnees = new ArrayList<Commande>();

		this.donnees.add(new Commande(1, date1, 1, listeLigneCommande1));
		this.donnees.add(new Commande(2, date2, 1, listeLigneCommande2));
	}

	@Override
	public boolean create(Commande objet) {

		objet.setIdCommande(3);
		// Ne fonctionne que si l'objet metier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setIdCommande(objet.getIdCommande() + 1);
		}
		boolean ok = this.donnees.add(objet);

		return ok;
	}

	@Override
	public boolean update(Commande objet) {

		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
		} else {

			this.donnees.set(idx, objet);
		}

		return true;
	}

	@Override
	public boolean delete(Commande objet) {

		Commande supprime;

		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return objet.equals(supprime);
	}

	@Override
	public Commande getById(int id) {

		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(new Commande(id));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune commande ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Commande> findAll() {
		return (ArrayList<Commande>) this.donnees;
	}

}
