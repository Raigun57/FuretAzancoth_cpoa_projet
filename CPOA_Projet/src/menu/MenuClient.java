package menu;

import java.sql.SQLException;
import java.util.Scanner;

import dao.factory.DAOFactory;
import metier.Client;

public class MenuClient {

	private static Scanner sc = new Scanner(System.in);
	
	private static int choixMenu;
	
	public static void mySQLClient() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) un client ou obtenir la liste de toutes les clients existants(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement le nom et le prenom :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nom = sc.nextLine();
				String prenom = sc.nextLine();

				Client clientAjout = new Client(nom, prenom);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getClientDAO().create(clientAjout);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();		//Recuperation du choix de l'utilisateur (choix de continuer ou quitter)
					if(choixMenu < 1 || choixMenu > 2)		//On s'assure que l'utilisateur rentre un chiffre valide
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1  || choixMenu > 2);
				
				break;
				
			case 2:
				System.out.println("Rentrez respectivement le numero du client a modifier, le nom et le prenom :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nomModif = sc.nextLine();
				String prenomModif = sc.nextLine();

				Client clientModif = new Client(idModif, nomModif, prenomModif);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getClientDAO().update(clientModif);
				
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
				System.out.print("Rentrez le numero du client a supprimer : ");
				
				int idSupp = sc.nextInt();
				
				Client clientSupp = new Client(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getClientDAO().delete(clientSupp);

				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1  || choixMenu > 2);
				
				break;
				
			case 4:
				System.out.print("Rentrez l'id du client que vous voulez voir : ");
				
				int idClient = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getClientDAO().getById(idClient));
				
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
				System.out.println("Affichage de tous les clients :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getClientDAO().findAll());
				
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
	
	public static void listeMemoireCategorie() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) un client ou obtenir la liste de toutes les clients existants(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement le nom et le prenom :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nom = sc.nextLine();
				String prenom = sc.nextLine();

				Client clientAjout = new Client(nom, prenom);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().create(clientAjout);
				
				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();		//Recuperation du choix de l'utilisateur (choix de continuer ou quitter)
					if(choixMenu < 1 || choixMenu > 2)		//On s'assure que l'utilisateur rentre un chiffre valide
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1  || choixMenu > 2);
				
				break;
				
			case 2:
				System.out.println("Rentrez respectivement le numero du client a modifier, le nom et le prenom :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String nomModif = sc.nextLine();
				String prenomModif = sc.nextLine();

				Client clientModif = new Client(idModif, nomModif, prenomModif);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().update(clientModif);
				
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
				System.out.print("Rentrez le numero du client a supprimer : ");
				
				int idSupp = sc.nextInt();
				
				Client clientSupp = new Client(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().delete(clientSupp);

				System.out.print("Voulez vous continuer(1) ou quitter(2) ? ");

				do
				{
					choixMenu = sc.nextInt();
					if(choixMenu < 1 || choixMenu > 2)
					{
						System.out.print("Veuillez rentrer un entier entre 1 et 2 : ");
					}
				}while(choixMenu < 1  || choixMenu > 2);
				
				break;
				
			case 4:
				System.out.print("Rentrez l'id du client que vous voulez voir : ");
				
				int idClient = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().getById(idClient));
				
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
				System.out.println("Affichage de tous les clients :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getClientDAO().findAll());
				
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