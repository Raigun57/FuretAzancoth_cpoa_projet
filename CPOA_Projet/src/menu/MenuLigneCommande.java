package menu;

import java.sql.SQLException;
import java.util.Scanner;

import dao.factory.DAOFactory;
import metier.LigneCommande;

public class MenuLigneCommande {
	
	private static Scanner sc = new Scanner(System.in);
	
	private static int choixMenu;
	
	public static void mySQLLigneCommande() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) une ligne de commande ou obtenir la liste de toutes les lignes de commandes existantes(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement l'id de la commande, l'id du produit, la quantite et le tarif unitaire :");
				
				int idCommandeAjout = sc.nextInt();
				int idProduitAjout = sc.nextInt();
				int quantiteAjout = sc.nextInt();
				double idClientAjout = sc.nextDouble();
				
				LigneCommande ligneCommandeAjout = new LigneCommande(idCommandeAjout, idProduitAjout, quantiteAjout, idClientAjout);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getLigneCommandeDAO().create(ligneCommandeAjout);
				
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
				System.out.println("Rentrez respectivement l'id de la commande et l'id du produit a modifier, la quantite et le tarif unitaire :");
				
				int idCommandeModif = sc.nextInt();
				int idProduitModif = sc.nextInt();
				int quantiteModif = sc.nextInt();
				double idClientModif = sc.nextDouble();
				
				LigneCommande ligneCommandeModif = new LigneCommande(idCommandeModif, idProduitModif, quantiteModif, idClientModif);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getLigneCommandeDAO().update(ligneCommandeModif);
				
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
				System.out.print("Rentrez le numero de la commande et le numero du produit a supprimer : ");
				
				int idCommandeSupp = sc.nextInt();
				int idProduitSupp = sc.nextInt();
				
				LigneCommande ligneCommandeSupp = new LigneCommande(idCommandeSupp, idProduitSupp);
				DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getLigneCommandeDAO().delete(ligneCommandeSupp);
				
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
				System.out.print("Rentrez l'id de la ligne de commande et l'id du produit que vous voulez voir : ");
				
				int idCommande = sc.nextInt();
				int idProduit = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getLigneCommandeDAO().getById(idCommande, idProduit));
				
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
				System.out.println("Affichage de toutes les lignes de commande");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.MYSQL).getLigneCommandeDAO().findAll());
				
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
	
	public static void listeMemoireLigneCommande() throws SQLException {
		do
		{
			System.out.println("Souhaitez-vous Ajouter(1), Modifier(2), Supprimer(3), Voir(4) une ligne de commande ou obtenir la liste de toutes les lignes de commandes existantes(5) ? (Entrez le numero correspondant a votre choix)");
			int choix = sc.nextInt(); 	//Recuperation du choix de l'utilisateur (choix de quel methode appeler)
			switch(choix)
			{
			case 1:
				System.out.println("Rentrez respectivement l'id de la commande, l'id du produit, la quantite et le tarif unitaire :");
				
				int idCommandeAjout = sc.nextInt();
				int idProduitAjout = sc.nextInt();
				int quantiteAjout = sc.nextInt();
				double idClientAjout = sc.nextDouble();
				
				LigneCommande ligneCommandeAjout = new LigneCommande(idCommandeAjout, idProduitAjout, quantiteAjout, idClientAjout);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().create(ligneCommandeAjout);
				
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
				System.out.println("Rentrez respectivement l'id de la commande et l'id du produit a modifier, la quantite et le tarif unitaire :");
				
				int idCommandeModif = sc.nextInt();
				int idProduitModif = sc.nextInt();
				int quantiteModif = sc.nextInt();
				double idClientModif = sc.nextDouble();
				
				LigneCommande ligneCommandeModif = new LigneCommande(idCommandeModif, idProduitModif, quantiteModif, idClientModif);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().update(ligneCommandeModif);
				
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
				System.out.print("Rentrez le numero de la commande et le numero du produit a supprimer : ");
				
				int idCommandeSupp = sc.nextInt();
				int idProduitSupp = sc.nextInt();
				
				LigneCommande ligneCommandeSupp = new LigneCommande(idCommandeSupp, idProduitSupp);
				DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().delete(ligneCommandeSupp);
				
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
				System.out.print("Rentrez l'id de la ligne de commande et l'id du produit que vous voulez voir : ");
				
				int idCommande = sc.nextInt();
				int idProduit = sc.nextInt();
				
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().getById(idCommande, idProduit));
				
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
				System.out.println("Affichage de toutes les lignes de commande");
				System.out.println(DAOFactory.getDAOFactory(dao.Persistance.ListeMemoire).getLigneCommandeDAO().findAll());
				
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
