package menu;

import java.sql.SQLException;
import java.util.Scanner;

import dao.factory.DAOFactory;
import metier.Categorie;

public class MenuCategorie {
	
	private static Scanner sc = new Scanner(System.in);
	
	private static int choixMenu;
	
	public static void mySQLCategorie() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) une categorie ou obtenir la liste de toutes les categorie existantes(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement le titre et le visuel :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String titre = sc.nextLine();
				String visuel = sc.nextLine();
				
				Categorie categAjout = new Categorie(titre, visuel);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().create(categAjout);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
	
				verificationMenu();
				
				break;
				
			case 2:
				System.out.println("Rentrez respectivement le numero de la categorie a modifier, le titre et le visuel :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String titreModif = sc.nextLine();
				String visuelModif = sc.nextLine();
				
				Categorie categModif = new Categorie(idModif, titreModif, visuelModif);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().update(categModif);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
	
				verificationMenu();
				
				break;
				
			case 3:
				System.out.print("Rentrez le numero de la categorie a supprimer : ");
				
				int idSupp = sc.nextInt();
				
				Categorie categSupp = new Categorie(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().delete(categSupp);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
	
				verificationMenu();
				
				break;
				
			case 4:
				System.out.print("Rentrez l'id de la categorie que vous voulez voir : ");
				
				int idCateg = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().getById(idCateg));
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
				
				verificationMenu();
				
				break;
				
			case 5:
				System.out.println("Affichage de toutes les categories :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCategorieDAO().findAll());
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");
				
				verificationMenu();
				
				break;
				
			default:
				System.out.print("Veuillez entrer un numero valide : ");
			}	
		}while(choixMenu != 2);
		Menu.changerTableMySQL(); 	//On demande a l'utilisateur s'il veut changer de table
	}
	
	public static void listeMemoireCategorie() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) une categorie ou obtenir la liste de toutes les categorie existantes(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement le titre et le visuel :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String titre = sc.nextLine();
				String visuel = sc.nextLine();
				
				Categorie categAjout = new Categorie(titre, visuel);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().create(categAjout);
				
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
				System.out.println("Rentrez respectivement le numero de la categorie a modifier, le titre et le visuel :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String titreModif = sc.nextLine();
				String visuelModif = sc.nextLine();
				
				Categorie categModif = new Categorie(idModif, titreModif, visuelModif);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().update(categModif);
				
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
				System.out.print("Rentrez le numero de la categorie a supprimer : ");
				
				int idSupp = sc.nextInt();
				
				Categorie categSupp = new Categorie(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().delete(categSupp);
				
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
				System.out.print("Rentrez l'id de la categorie que vous voulez voir : ");
				
				int idCateg = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().getById(idCateg));
				
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
				System.out.println("Affichage de toutes les categories :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCategorieDAO().findAll());
				
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