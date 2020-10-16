package menu;

import java.sql.SQLException;
import java.util.Scanner;

import dao.factory.DAOFactory;
import metier.Produit;

public class MenuProduit {
	
	private static Scanner sc = new Scanner(System.in);
	
	private static int choixMenu;
	
	public static void mySQLProduit() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) un produit ou obtenir la liste de toutes les produits existants(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement le nom, la description, le tarif, le visuel et l'id de la categorie :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nom = sc.nextLine();
				String desc = sc.nextLine();
				double tarif = sc.nextDouble();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String visuel = sc.nextLine();
				int idCateg = sc.nextInt();
				
				Produit produitAjout = new Produit(nom, desc, tarif, visuel, idCateg);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getProduitDAO().create(produitAjout);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();		//Recuperation du choix de l'utilisateur (choix de continuer ou quitter)
					if(choixMenu < 1 || choixMenu > 2)		//On s'assure que l'utilisateur rentre un chiffre valide
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 2:
				System.out.println("Rentrez respectivement le numero du produit a modifier, le nom, la description, le tarif, le visuel et l'id de la categorie :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nomModif = sc.nextLine();
				String descModif = sc.nextLine();
				double tarifModif = sc.nextDouble();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String visuelModif = sc.nextLine();
				int idCategModif = sc.nextInt();

				Produit produitModif = new Produit(idModif, nomModif, descModif, tarifModif, visuelModif, idCategModif);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getProduitDAO().update(produitModif);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 3:
				System.out.print("Rentrez le numero du produit a supprimer : ");
				
				int idSupp = sc.nextInt();

				Produit produitSupp = new Produit(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getProduitDAO().delete(produitSupp);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 4:
				System.out.print("Rentrez l'id du produit que vous voulez voir : ");
				
				int idProduit = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getProduitDAO().getById(idProduit));
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
				
				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 5:
				System.out.println("Affichage de tous les produits :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getProduitDAO().findAll());
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
				
				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			default:
				System.out.print("Veuillez entrer un numero valide : ");
			}	
		}while(choixMenu != 2);
		Menu.changerTableMySQL(); 	//On demande a l'utilisateur s'il veut changer de table
	}
	
	public static void listeMemoireProduit() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) un produit ou obtenir la liste de toutes les produits existants(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement le nom, la description, le tarif, le visuel et l'id de la categorie :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nom = sc.nextLine();
				String desc = sc.nextLine();
				double tarif = sc.nextDouble();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String visuel = sc.nextLine();
				int idCateg = sc.nextInt();
				
				Produit produitAjout = new Produit(nom, desc, tarif, visuel, idCateg);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().create(produitAjout);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();		//Recuperation du choix de l'utilisateur (choix de continuer ou quitter)
					if(choixMenu < 1 || choixMenu > 2)		//On s'assure que l'utilisateur rentre un chiffre valide
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 2:
				System.out.println("Rentrez respectivement le numero du produit a modifier, le nom, la description, le tarif, le visuel et l'id de la categorie :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nomModif = sc.nextLine();
				String descModif = sc.nextLine();
				double tarifModif = sc.nextDouble();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String visuelModif = sc.nextLine();
				int idCategModif = sc.nextInt();

				Produit produitModif = new Produit(idModif, nomModif, descModif, tarifModif, visuelModif, idCategModif);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().update(produitModif);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 3:
				System.out.print("Rentrez le numero du produit a supprimer : ");
				
				int idSupp = sc.nextInt();

				Produit produitSupp = new Produit(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().delete(produitSupp);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 4:
				System.out.print("Rentrez l'id du produit que vous voulez voir : ");
				
				int idProduit = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().getById(idProduit));
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
				
				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			case 5:
				System.out.println("Affichage de tous les produits :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getProduitDAO().findAll());
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
				
				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1 || choixMenu > 2);
				
				break;
				
			default:
				System.out.print("Veuillez entrer un numero valide : ");
			}	
		}while(choixMenu != 2);
		Menu.changerTableListeMemoire();  	//On demande a l'utilisateur s'il veut changer de table
	}
	
	public static void verificationMenu() {
		do {
			choixMenu = sc.nextInt();		//Recuperation du choix de l'utilisateur (choix de continuer ou quitter)
			
			if(choixMenu < 1 || choixMenu > 2)		//On s'assure que l'utilisateur rentre un chiffre valide
			{ 
				System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
			}
		}while(choixMenu < 1 || choixMenu > 2);
	}
}
