package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.modele.CategorieDAO;
import metier.Categorie;

public class MySQLCategorieDAO implements CategorieDAO {
	
	private static MySQLCategorieDAO instance;
	
	private MySQLCategorieDAO() {}
	
	//V�rifie si il existe une instance sinon en cr�e une
	public static MySQLCategorieDAO getInstance() {
		if (instance==null) {
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}

	public boolean create(Categorie object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO `Categorie`(`titre`, `visuel`) VALUES(?, ?)");
		
		//Pas besoin de g�rer les id car cl� primaire
		requete.setString(1, object.getTitre());
		requete.setString(2, object.getVisuel());
		
		nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	public boolean update(Categorie object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("UPDATE `Categorie` SET titre=?, visuel=? WHERE id_categorie=" + object.getId());
		
		requete.setString(1, object.getTitre());
		requete.setString(2, object.getVisuel());
		
		nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	public boolean delete(Categorie object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Categorie where id_categorie=" + object.getId());
		
		nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	public Categorie getById(int id) throws SQLException {
		Categorie categ = null;
		
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie where id_categorie=" + id);
		ResultSet res = requete.executeQuery();
		
		if (res.next()) {
			categ = new Categorie(res.getInt(1), res.getString(2), res.getString(3));
		}
		return categ;
	}

	public ArrayList<Categorie> findAll() throws SQLException {
		ArrayList<Categorie> listeCateg = new ArrayList<Categorie>();
		
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie");
		ResultSet res = requete.executeQuery();
		
		while(res.next()) {
			listeCateg.add(new Categorie(res.getInt(1), res.getString(2), res.getString(3)));
		}
		return listeCateg;
	}
	
}
