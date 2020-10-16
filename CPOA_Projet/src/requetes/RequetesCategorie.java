package requetes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;

public class RequetesCategorie {

	public static boolean AjouterCategorie(int id, String titre, String visuel) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `Categorie`(`id_categorie`, `titre`, `visuel`) VALUES (" + id + "," + "'" + titre + "'" + "," + "'" + visuel + "'" + ")");
		
		return nbLignes==1;		
	}
	
	public static boolean SupprimerCategorie(int id) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `Categorie` WHERE id_categorie=" + id);
		
		return nbLignes==1;	
	}
	
	public static boolean ModifierCategorie(int idModif, int id, String titre, String visuel) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `Categorie` SET `id_categorie`=" + id + ",`titre`=" + "'" + titre + "'" + ",`visuel`=" + "'" + visuel + "'" + " WHERE id_categorie=" + idModif);
		
		return nbLignes==1;	
	}
	
	public static boolean listeCategorie() throws SQLException{
		int nbLignes = 0;

			ArrayList<String> listeCateg = new ArrayList<String>();
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet rs = requete.executeQuery("SELECT titre FROM Categorie");
			while (rs.next()) {
				  String titre = rs.getString("titre");
				  listeCateg.add(titre);
				}
			System.out.println(listeCateg);
		
		return nbLignes==1;
		
	}
	
	
}
