package dao.factory;

import dao.Persistance;
import dao.modele.CategorieDAO;
import dao.modele.ClientDAO;
import dao.modele.CommandeDAO;
import dao.modele.LigneCommandeDAO;
import dao.modele.ProduitDAO;
import metier.LigneCommande;

public abstract class DAOFactory {
	
	public static DAOFactory getDAOFactory(Persistance cible) {
		
		DAOFactory daoF = null;
		switch (cible) {
		case MYSQL:
			daoF = new MySQLDAOFactory();
			break;
		case ListeMemoire:
			daoF = new ListeMemoireDAOFactory();
			break;
		}
		return daoF;
	}
	
	public abstract CategorieDAO getCategorieDAO();
	public abstract ClientDAO getClientDAO();
	public abstract ProduitDAO getProduitDAO();
	public abstract CommandeDAO getCommandeDAO();
	public abstract LigneCommandeDAO<LigneCommande> getLigneCommandeDAO();

}
