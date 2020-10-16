package menu;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuMYSQL {

	public static void menuMysql() throws SQLException {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Sur quelle table voulez vous travaillez ?");
		System.out.println("Categorie(1), Client(2), Produit(3), Commande(4), LigneCommande(5)");
		
		int choixTable;
		
		do
		{
			choixTable = sc.nextInt();		//Recuperation du choix de l'utilisateur (choix de la table a modifier)
			if(choixTable < 1 || choixTable > 5)		//On s'assure que l'utilisateur rentre un chiffre valide
			{
				System.out.print("Veuillez rentrer un entier entre 1 et 5 : ");
			}
		}while(choixTable < 1 || choixTable > 5);
		
		switch(choixTable)
		{
		case 1 :
			MenuCategorie.mySQLCategorie();
			break;
			
		case 2 :
			MenuClient.mySQLClient();
			break;
			
		case 3 :
			MenuProduit.mySQLProduit();
			break;
			
		case 4 :
			MenuCommande.mySQLCommande();
			break;
			
		case 5 :
			MenuLigneCommande.mySQLLigneCommande();
			break;
			
		default :
			System.out.print("Veuillez entrer un numero valide : ");
		}
	}	
	
}
