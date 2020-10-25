package dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connexion.Connexion;
import dao.modele.ClientDAO;
import metier.Client;

public class MySQLClientDAO implements ClientDAO {

	private static MySQLClientDAO instance;

	private MySQLClientDAO() {
	}

	// Vérifie si il existe une instance sinon en crée une
	public static MySQLClientDAO getInstance() {
		if (instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}

	@Override
	public boolean create(Client object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("INSERT INTO `Client`(`nom`, `prenom`, `identifiant`, `mot_de_passe`, `adr_numero`,"
						+ " `adr_voie`, `adr_code_postal`, `adr_ville`, `adr_pays`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

		// Pas besoin de gérer les id car clé primaire
		requete.setString(1, object.getNom());
		requete.setString(2, object.getPrenom());
		requete.setString(3, object.getIdentifiant());
		requete.setString(4, object.getMdp());
		requete.setInt(5, object.getNumero());
		requete.setString(6, object.getRue());
		requete.setInt(7, object.getCodePostal());
		requete.setString(8, object.getVille());
		requete.setString(9, object.getPays());

		nbLignes = requete.executeUpdate();

		return nbLignes == 1;
	}

	@Override
	public boolean update(Client object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE `Client` SET nom=?, prenom=?, identifiant=?, mot_de_passe=?, adr_numero=?, adr_voie=?, adr_code_postal=?, adr_ville=?,adr_pays=? WHERE id_client="
						+ object.getId());

		requete.setString(1, object.getNom());
		requete.setString(2, object.getPrenom());
		requete.setString(3, object.getIdentifiant());
		requete.setString(4, object.getMdp());
		requete.setInt(5, object.getNumero());
		requete.setString(6, object.getRue());
		requete.setInt(7, object.getCodePostal());
		requete.setString(8, object.getVille());
		requete.setString(9, object.getPays());

		nbLignes = requete.executeUpdate();

		return nbLignes == 1;
	}

	@Override
	public boolean delete(Client object) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion
				.prepareStatement("DELETE FROM Client where id_client=" + object.getId());

		nbLignes = requete.executeUpdate();

		return nbLignes == 1;
	}

	@Override
	public Client getById(int id) throws SQLException {
		Client client = null;

		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client where id_client=" + id);
		ResultSet res = requete.executeQuery();

		if (res.next()) {
			client = new Client(res.getInt(1), res.getString(2), res.getString(3));
		}
		return client;
	}

	@Override
	public ArrayList<Client> findAll() throws SQLException {
		ArrayList<Client> listeClient = new ArrayList<Client>();

		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client");
		ResultSet res = requete.executeQuery();

		while (res.next()) {
			listeClient.add(new Client(res.getInt(1), res.getString(2), res.getString(3)));
		}
		return listeClient;
	}

}
