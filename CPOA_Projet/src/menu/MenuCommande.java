package menu;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.factory.DAOFactory;
import metier.Commande;

public class MenuCommande {

	private static Scanner sc = new Scanner(System.in);
	
	private static int choixMenu;
	
	private static DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static void mySQLCommande() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) une commande ou obtenir la liste de toutes les commandes existantes(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement la date de commande et l'id du client :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String stringCommandeAjout = sc.nextLine();
				LocalDate dateCommandeAjout = LocalDate.parse(stringCommandeAjout, formatage);
				int idClientAjout = sc.nextInt();
				
				Commande commandeAjout = new Commande(dateCommandeAjout, idClientAjout, null);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCommandeDAO().create(commandeAjout);
				
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
				System.out.println("Rentrez respectivement le numero de la commande a modifier, la date de commande et le l'id du client :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String stringCommandeModif = sc.nextLine();
				LocalDate dateCommandeModif = LocalDate.parse(stringCommandeModif, formatage);
				int idClientModif = sc.nextInt();
				
				Commande commandeModif = new Commande(idModif, dateCommandeModif, idClientModif, null);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCommandeDAO().update(commandeModif);
				
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
				System.out.print("Rentrez le numero de la commande a supprimer : ");
				
				int idSupp = sc.nextInt();
				
				Commande commandeSupp = new Commande(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCommandeDAO().delete(commandeSupp);
				
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
				System.out.print("Rentrez l'id de la commande que vous voulez voir : ");
				
				int idCommande = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCommandeDAO().getById(idCommande));
				
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
				System.out.println("Affichage de toutes les commandes :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getCommandeDAO().findAll());
				
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
	
	public static void listeMemoireCommande() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) une commande ou obtenir la liste de toutes les commandes existantes(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement la date de commande et l'id du client :");
				
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String stringCommandeAjout = sc.nextLine();
				LocalDate dateCommandeAjout = LocalDate.parse(stringCommandeAjout, formatage);
				int idClientAjout = sc.nextInt();
				
				Commande commandeAjout = new Commande(dateCommandeAjout, idClientAjout, null);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().create(commandeAjout);
				
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
				System.out.println("Rentrez respectivement le numero de la commande a modifier, la date de commande et le l'id du client :");
				
				int idModif = sc.nextInt();
				sc.nextLine();		//On vide la ligne pour pouvoir en lire une autre
				String stringCommandeModif = sc.nextLine();
				LocalDate dateCommandeModif = LocalDate.parse(stringCommandeModif, formatage);
				int idClientModif = sc.nextInt();
				
				Commande commandeModif = new Commande(idModif, dateCommandeModif, idClientModif, null);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().update(commandeModif);
				
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
				System.out.print("Rentrez le numero de la commande a supprimer : ");
				
				int idSupp = sc.nextInt();
				
				Commande commandeSupp = new Commande(idSupp);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().delete(commandeSupp);
				
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
				System.out.print("Rentrez l'id de la commande que vous voulez voir : ");
				
				int idCommande = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().getById(idCommande));
				
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
				System.out.println("Affichage de toutes les commandes :");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getCommandeDAO().findAll());
				
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
