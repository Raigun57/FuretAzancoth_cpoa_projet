package dao.listememoire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.modele.LigneCommandeDAO;
import metier.LigneCommande;

public class ListeMemoireLigneCommandeDAO implements LigneCommandeDAO<LigneCommande> {

	private static ListeMemoireLigneCommandeDAO instance;

	private List<LigneCommande> donnees;

	public static ListeMemoireLigneCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireLigneCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireLigneCommandeDAO() {

		this.donnees = new ArrayList<LigneCommande>();

		this.donnees.add(new LigneCommande(1, 2, 2, 41.5));
		this.donnees.add(new LigneCommande(1, 6, 1, 15));
		this.donnees.add(new LigneCommande(2, 12, 4, 35));
	}


	@Override
	public boolean create(LigneCommande objet) {

		objet.setIdCommande(3);
		objet.setIdProduit(3);
		// Ne fonctionne que si l'objet m�tier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setIdCommande(objet.getIdCommande() + 1);
			objet.setIdProduit(objet.getIdProduit() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(LigneCommande objet) {
		
		// Ne fonctionne que si l'objet m�tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une ligne de commande inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(LigneCommande objet) {

		LigneCommande supprime;
		
		// Ne fonctionne que si l'objet m�tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une ligne de commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public LigneCommande getById(int id) {
		// Ne fonctionne que si l'objet m�tier est bien fait...
		int idx = this.donnees.indexOf(new LigneCommande(id, 5, 14, 50));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune ligne de commande ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<LigneCommande> findAll() {
		return (ArrayList<LigneCommande>) this.donnees;
	}

	@Override
	public LigneCommande getById(int idCommande, int idProduit) throws SQLException {
		int idx = this.donnees.indexOf(new LigneCommande(idCommande, idProduit));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune ligne de commande ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	
}
