package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import connexion.Connexion;
import dao.modele.CommandeDAO;
import metier.Commande;
import metier.LigneCommande;
import metier.Produit;

public class MySQLCommandeDAO implements CommandeDAO {

private static MySQLCommandeDAO instance;
	
	private MySQLCommandeDAO() {}
	
	//V�rifie si il existe une instance sinon en cree une
	public static MySQLCommandeDAO getInstance() {
		if (instance==null) {
			instance = new MySQLCommandeDAO();
		}
		return instance;
	}

	public boolean create(Commande object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO `Commande`(`date_commande`, `id_client`) VALUES(?, ?)");
		
		//Pas besoin de g�rer les id car cl� primaire
		requete.setDate(1, java.sql.Date.valueOf(object.getDate()));
		requete.setInt(2, object.getIdClient());
		
		nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	public boolean update(Commande object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("UPDATE `Commande` SET date_commande=?, id_client=? WHERE id_Commande=" + object.getIdCommande());
		
		requete.setDate(1, java.sql.Date.valueOf(object.getDate()));
		requete.setInt(2, object.getIdClient());
		
		nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	public boolean delete(Commande object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Commande WHERE id_commande=" + object.getIdCommande());
	
		nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	public Commande getById(int id) throws SQLException {
		Commande commande = null;
		HashMap<Produit, LigneCommande> listeLigneCommande = new HashMap<Produit, LigneCommande>();
		
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande WHERE id_commande=" + id);
		ResultSet res = requete.executeQuery();
		
		if(res.next()) {
			PreparedStatement requeteLigneCommande = laConnexion.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande=" + id);
			ResultSet resLigneCommande = requeteLigneCommande.executeQuery();
			
			while(resLigneCommande.next()) {
				PreparedStatement requeteProduit= laConnexion.prepareStatement("SELECT * FROM Produit WHERE id_produit=" + resLigneCommande.getInt(2));
				ResultSet resProduit= requeteProduit.executeQuery();
				
				if(resProduit.next()) {
					//On ajoute à la HashMap un nouveau Produit (la clé) et une nouvelle LigneCommande (la valeur)
					listeLigneCommande.put(new Produit(resProduit.getInt(1), resProduit.getString(2), resProduit.getString(3), resProduit.getDouble(4), resProduit.getString(5), resProduit.getInt(6)), 
							new LigneCommande(resLigneCommande.getInt(1), resLigneCommande.getInt(2), resLigneCommande.getInt(3), resLigneCommande.getDouble(4)));
				}
			}
		}
		
		commande = new Commande(res.getInt(1), res.getDate(2).toLocalDate(), res.getInt(3), listeLigneCommande);
		return commande;
	}

	public ArrayList<Commande> findAll() throws SQLException {
		ArrayList<Commande> listeCommande = new ArrayList<Commande>();
		HashMap<Produit, LigneCommande> listeLigneCommande = new HashMap<Produit, LigneCommande>();
		
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Commande");
		ResultSet res = requete.executeQuery();
		
		while(res.next()) {
			PreparedStatement requeteLigneCommande = laConnexion.prepareStatement("SELECT * FROM Ligne_commande WHERE id_commande=" + res.getInt(1));
			ResultSet resLigneCommande = requeteLigneCommande.executeQuery();
			
			while(resLigneCommande.next()) {
				PreparedStatement requeteProduit= laConnexion.prepareStatement("SELECT * FROM Produit WHERE id_produit=" + resLigneCommande.getInt(2));
				ResultSet resProduit= requeteProduit.executeQuery();
				
				if(resProduit.next()) {
					//On ajoute à la HashMap un nouveau Produit (la clé) et une nouvelle LigneCommande (la valeur)
					listeLigneCommande.put(new Produit(resProduit.getInt(1), resProduit.getString(2), resProduit.getString(3), resProduit.getDouble(4), resProduit.getString(5), resProduit.getInt(6)), 
							new LigneCommande(resLigneCommande.getInt(1), resLigneCommande.getInt(2), resLigneCommande.getInt(3), resLigneCommande.getDouble(4)));
				}
			}
			listeCommande.add(new Commande(res.getInt(1), res.getDate(2).toLocalDate(), res.getInt(3), listeLigneCommande));
		}
		
		return listeCommande;
	}
	
}
