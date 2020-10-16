package menu;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void choixPersistance() throws SQLException {
		int choixPersistance;
    	
    	System.out.println("Voulez vous travailler sur MySQL(1) ou ListeMemoire(2) ?");
		do
		{
			choixPersistance = sc.nextInt();		//Recuperation du choix de l'utilisateur (choix de la table a modifier)
			if(choixPersistance < 1 || choixPersistance > 2)		//On s'assure que l'utilisateur rentre un chiffre valide
			{
				System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
			}
		}while(choixPersistance < 1 || choixPersistance > 2);
		
		if(choixPersistance == 1)
		{
			System.out.println("Debut MySQL");
			MenuMYSQL.menuMysql();
		}	
		else if(choixPersistance == 2) {
			System.out.println("Debut ListeMemoire");
			MenuListeMemoire.menuListeMemoire();
		}
	}

	public static void quitter() throws SQLException {
    	int choixQuitter;
    	
    	System.out.print("Voulez vous changer de persistance(1) ou quitter(2) ? ");
		do
		{
			choixQuitter = sc.nextInt();
			if(choixQuitter < 1 || choixQuitter > 2)
			{
				System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
			}
		}while(choixQuitter < 1 || choixQuitter > 2);
		
		if(choixQuitter == 1) {
			choixPersistance();		// 1 : l'utilisateur passe sur une autre table
		}
		else if(choixQuitter == 2) {
			System.out.println("Fin du programme");// 2 : quitte et met fin au programme
		}
    }
    
    public static void changerTableMySQL() throws SQLException {
    	int choixTable;
    	
    	System.out.print("Voulez vous passer sur une autre table(1) ou quitter(2) ? ");
    	
		do
		{
			choixTable = sc.nextInt();
			if(choixTable < 1 || choixTable > 2)
			{
				System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
			}
		}while(choixTable < 1 || choixTable > 2);
		
		if(choixTable == 1) {
			MenuMYSQL.menuMysql();		// 1 : l'utilisateur passe sur une autre table
		}
		else if(choixTable == 2) {
			System.out.println("Fin MySQL");
			quitter(); // 2 : quitte et met fin au programme
		}
    }
    
    public static void changerTableListeMemoire() throws SQLException {
    	int choixTable;
    	
    	System.out.print("Voulez vous passer sur une autre table(1) ou quitter(2) ? ");
    	
		do
		{
			choixTable = sc.nextInt();
			if(choixTable < 1 || choixTable > 2)
			{
				System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
			}
		}while(choixTable < 1 || choixTable > 2);
		
		if(choixTable == 1) {
			MenuListeMemoire.menuListeMemoire();	// 1 : l'utilisateur passe sur une autre table
		}
		else if(choixTable == 2) {
			System.out.println("Fin ListeMemoire");
			quitter(); // 2 : quitte et met fin au programme
		}
    }
	
}
