package dao.listememoire;

import metier.Categorie;

import java.util.ArrayList;
import java.util.List;

import dao.modele.CategorieDAO;

public class ListeMemoireCategorieDAO implements CategorieDAO {

	private static ListeMemoireCategorieDAO instance;

	private List<Categorie> donnees;

	public static ListeMemoireCategorieDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCategorieDAO();
		}

		return instance;
	}

	private ListeMemoireCategorieDAO() {

		this.donnees = new ArrayList<Categorie>();
		
		this.donnees.add(new Categorie(1, "Pulls", "lespulls.png"));
		this.donnees.add(new Categorie(2, "Bonnets", "lesbonnets.png"));
		this.donnees.add(new Categorie(4, "chaussettes", "leschaussettes.png"));

	}


	@Override
	public boolean create(Categorie objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet metier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Categorie objet) {
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Categorie objet) {

		Categorie supprime;
		
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Categorie getById(int id) {
		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(new Categorie(id));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne possede cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Categorie> findAll() {
		return (ArrayList<Categorie>) this.donnees;
	}
}
