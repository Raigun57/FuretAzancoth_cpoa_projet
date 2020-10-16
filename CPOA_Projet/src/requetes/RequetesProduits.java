package requetes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connexion.Connexion;

public class RequetesProduits {
	
	public static boolean AjouterProduit(int id, String nom, String desc, double tarif, String visuel, int idCateg) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `Produit` VALUES (" + id + ",'" + nom + "'" + ", '" + desc + "'" + ", " + tarif + ", '" + visuel + "'" + ", " + idCateg + ")");
			
		return nbLignes==1;	
	}
	
	public static boolean SupprimerProduit(int id) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `Produit` WHERE id_produit=" + id);
			
		return nbLignes==1;	
	}
	
	public static boolean ModifierProduit(int idModif, int id, String nom, String desc, double tarif, String visuel, int idCateg) throws SQLException{
		int nbLignes = 0;
		
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `Produit` SET `id_produit`=" + id + ",`nom`=" + "'" + nom + "'" + ",`description`=" + "'" + desc + "'"  + ",`tarif`=" + tarif + ",`visuel`=" + "'" + visuel + "'" + ",`id_categorie`=" + idCateg + " WHERE id_produit=" + idModif);
			
		return nbLignes==1;	
	}
	
	public static boolean listeProduit() throws SQLException{
		int nbLignes = 0;
		
			ArrayList<String> listeProduit = new ArrayList<String>();
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet rs = requete.executeQuery("SELECT nom FROM Produit");
			while (rs.next()) {
				  String prod = rs.getString("nom");
				  listeProduit.add(prod);
				}
			System.out.println(listeProduit);
			
		return nbLignes==1;		
	
	}

}
