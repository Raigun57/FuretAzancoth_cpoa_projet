package dao.listememoire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.modele.CommandeDAO;
import metier.Commande;

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
		
		this.donnees = new ArrayList<Commande>();

		this.donnees.add(new Commande(1, date1, 1, null));
		this.donnees.add(new Commande(2, date2, 1, null));
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
