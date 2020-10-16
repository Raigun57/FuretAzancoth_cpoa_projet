package requetes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;

public class RequetesClients {
	
	public static boolean AjouterClient(int id, String nom, String prenom) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `Client` VALUES (" + id + "," + "'" + nom + "'" + "," + "'" + prenom + "'" + ", 'test', 'test', 0, 'test', 0, 'test', 'test'" + ")");
			
		return nbLignes==1;
			
	}
	
	public static boolean SupprimerClient(int id) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `Client` WHERE id_client=" + id);
		
		return nbLignes==1;	
	}
	
	public static boolean ModifierClient(int idModif, int id, String nom, String prenom) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `Client` SET `id_client`=" + id + ",`nom`=" + "'" + nom + "'" + ",`prenom`=" + "'" + prenom + "'" + " WHERE id_client=" + idModif);
		
		return nbLignes==1;		
	}
	
	public static boolean listeClient() throws SQLException{
		int nbLignes = 0;
		
			ArrayList<String> listeClient = new ArrayList<String>();
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet rs = requete.executeQuery("SELECT nom, prenom FROM Client");
			while (rs.next()) {
				  String nom = rs.getString("nom");
				  String prenom = rs.getString("prenom");
				  String cl = nom.concat(" " + prenom);		//Concatenation du nom et du prenom
				  listeClient.add(cl);
				}
			System.out.println(listeClient);
		
		return nbLignes==1;	
	}

}
