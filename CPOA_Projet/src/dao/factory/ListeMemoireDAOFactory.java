package dao.factory;

import dao.listememoire.ListeMemoireCategorieDAO;
import dao.listememoire.ListeMemoireClientDAO;
import dao.listememoire.ListeMemoireCommandeDAO;
import dao.listememoire.ListeMemoireLigneCommandeDAO;
import dao.listememoire.ListeMemoireProduitDAO;
import dao.modele.CategorieDAO;
import dao.modele.ClientDAO;
import dao.modele.CommandeDAO;
import dao.modele.LigneCommandeDAO;
import dao.modele.ProduitDAO;
import metier.LigneCommande;

public class ListeMemoireDAOFactory extends DAOFactory {
	
	@Override
	public CategorieDAO getCategorieDAO() {
		return ListeMemoireCategorieDAO.getInstance();
	}
	
	@Override
	public ClientDAO getClientDAO() {
		return ListeMemoireClientDAO.getInstance();
	}
	
	@Override
	public ProduitDAO getProduitDAO() {
		return ListeMemoireProduitDAO.getInstance();
	}
	
	@Override
	public CommandeDAO getCommandeDAO() {
		return ListeMemoireCommandeDAO.getInstance();
	}
	
	@Override
	public LigneCommandeDAO<LigneCommande> getLigneCommandeDAO() {
		return ListeMemoireLigneCommandeDAO.getInstance();
	}
}