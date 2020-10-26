package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.modele.ProduitDAO;
import metier.Produit;

public class MySQLProduitDAO implements ProduitDAO {

	private static MySQLProduitDAO instance;

	private MySQLProduitDAO() {
	}

	// Vérifie si il existe une instance sinon en crée une
	public static MySQLProduitDAO getInstance() {
		if (instance == null) {
			instance = new MySQLProduitDAO();
		}
		return instance;
	}

	@Override
	public boolean create(Produit object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO `Produit`(`nom`, `description`, `tarif`, `visuel`, `id_categorie`) VALUES(?, ?, ?, ?, ?)");

		// Pas besoin de gérer les id car clé primaire
		requete.setString(1, object.getNom());
		requete.setString(2, object.getDescription());
		requete.setDouble(3, object.getTarif());
		requete.setString(4, object.getVisuel());
		requete.setInt(5, object.getIdCateg());

		nbLignes = requete.executeUpdate();

		return nbLignes == 1;
	}

	@Override
	public boolean update(Produit object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE `Produit` SET nom=?, description=?, tarif=?, visuel=?, id_categorie=? WHERE id_produit="
						+ object.getId());

		requete.setString(1, object.getNom());
		requete.setString(2, object.getDescription());
		requete.setDouble(3, object.getTarif());
		requete.setString(4, object.getVisuel());
		requete.setInt(5, object.getIdCateg());

		nbLignes = requete.executeUpdate();

		return nbLignes == 1;
	}

	@Override
	public boolean delete(Produit object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("DELETE FROM Produit where id_produit=" + object.getId());

		nbLignes = requete.executeUpdate();

		return nbLignes == 1;
	}

	@Override
	public Produit getById(int id) throws SQLException {
		Produit Produit = null;

		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit where id_produit=" + id);
		ResultSet res = requete.executeQuery();

		if (res.next()) {
			Produit = new Produit(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
					res.getInt(6));
		}
		return Produit;
	}

	@Override
	public ArrayList<Produit> findAll() throws SQLException {
		ArrayList<Produit> listeProduit = new ArrayList<Produit>();

		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit");
		ResultSet res = requete.executeQuery();

		while (res.next()) {
			listeProduit.add(new Produit(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4),
					res.getString(5), res.getInt(6)));
		}
		return listeProduit;
	}

}
