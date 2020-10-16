package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.modele.LigneCommandeDAO;
import metier.LigneCommande;

public class MySQLLigneCommandeDAO implements LigneCommandeDAO<LigneCommande>{

	private static MySQLLigneCommandeDAO instance;
	
	private MySQLLigneCommandeDAO() {}
	
	//V�rifie si il existe une instance sinon en cr�e une
	public static MySQLLigneCommandeDAO getInstance() {
		if (instance==null) {
			instance = new MySQLLigneCommandeDAO();
		}
		return instance;
	}

	public boolean create(LigneCommande object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO `Ligne_commande`(`id_commande`, `id_produit`, `quantite`, `tarif_unitaire`) VALUES(?, ?, ?, ?)");
		
		//Pas besoin de g�rer les id car cl� primaire
		requete.setInt(1, object.getIdCommande());
		requete.setInt(2, object.getIdProduit());
		requete.setInt(3, object.getQuantite());
		requete.setDouble(4, object.getTarifUnitaire());
		
		nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	public boolean update(LigneCommande object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("UPDATE `Ligne_commande` SET quantite=?, tarif_unitaire=? WHERE id_commande=" + object.getIdCommande() + " AND id_produit=" + object.getIdProduit());
		
		requete.setInt(1, object.getQuantite());
		requete.setDouble(2, object.getTarifUnitaire());
		
		nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	public boolean delete(LigneCommande object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Ligne_commande where id_commande=" + object.getIdCommande());
		
		nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	public LigneCommande getById(int id) throws SQLException {
		LigneCommande ligneCommande = null;
		
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Ligne_commande where id_commande=" + id);
		ResultSet res = requete.executeQuery();
		
		if (res.next()) {
			ligneCommande = new LigneCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4));
		}
		return ligneCommande;
	}
	
	public LigneCommande getById(int idCommande, int idProduit) throws SQLException {
		LigneCommande ligneCommande = null;
		
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Ligne_commande where id_commande=" + idCommande + " and id_produit=" + idProduit);
		ResultSet res = requete.executeQuery();
		
		if (res.next()) {
			ligneCommande = new LigneCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4));
		}
		return ligneCommande;
	}

	public ArrayList<LigneCommande> findAll() throws SQLException {
		ArrayList<LigneCommande> listeLigneCommande = new ArrayList<LigneCommande>();
		
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Ligne_commande");
		ResultSet res = requete.executeQuery();
		
		while(res.next()) {
			listeLigneCommande.add(new LigneCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4)));
		}
		return listeLigneCommande;
	}
	
}
