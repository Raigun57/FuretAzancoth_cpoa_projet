package dao.factory;

import dao.modele.CategorieDAO;
import dao.modele.ClientDAO;
import dao.modele.CommandeDAO;
import dao.modele.LigneCommandeDAO;
import dao.modele.ProduitDAO;
import dao.mysql.MySQLCategorieDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLCommandeDAO;
import dao.mysql.MySQLLigneCommandeDAO;
import dao.mysql.MySQLProduitDAO;
import metier.LigneCommande;

public class MySQLDAOFactory extends DAOFactory {
	
	@Override
	public CategorieDAO getCategorieDAO() {
		return MySQLCategorieDAO.getInstance();
	}
	
	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
	}
	
	@Override
	public ProduitDAO getProduitDAO() {
		return MySQLProduitDAO.getInstance();
	}
	
	@Override
	public CommandeDAO getCommandeDAO() {
		return MySQLCommandeDAO.getInstance();
	}
	
	@Override
	public LigneCommandeDAO<LigneCommande> getLigneCommandeDAO() {
		return MySQLLigneCommandeDAO.getInstance();
	}

}
