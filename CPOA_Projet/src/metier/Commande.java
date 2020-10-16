package metier;
import java.time.*;
import java.util.HashMap;

public class Commande {
	private int idCommande;
	private int idClient;
	private LocalDate date;
	
	HashMap<Produit, LigneCommande> listeLigneCommande = new HashMap<Produit, LigneCommande>();
	
	//Constructeur pour la methode delete de MySQLCommandeDAO
	public Commande(int id) {
		super();
		this.setIdCommande(id);
	}
			
	//Constructeur pour la methode create de MySQLCommandeDAO
	public Commande(LocalDate date, int idClient, HashMap<Produit, LigneCommande> listeLigneCommande) {
		super();
		this.setDate(date);
		this.setIdClient(idClient);
		this.setListeCommande(listeLigneCommande);
	}
	
	public Commande(int idCommande, LocalDate date, int idClient, HashMap<Produit, LigneCommande> listeLigneCommande) {
		super();
		this.setIdCommande(idCommande);
		this.setDate(date);
		this.setIdClient(idClient);
		this.setListeCommande(listeLigneCommande);
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		if(idCommande <= 0)
			throw new IllegalArgumentException("Id de la commande inferieur ou egal a 0 !");
		else 
			this.idCommande = idCommande;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		if(idClient <= 0)
			throw new IllegalArgumentException("Id du client inferieur ou egal a 0 !");
		else 
			this.idClient = idClient;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public HashMap<Produit, LigneCommande> getListeCommande() {
		return listeLigneCommande;
	}

	public void setListeCommande(HashMap<Produit, LigneCommande> listeCommande) {
		this.listeLigneCommande = listeCommande;
	}

	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", idClient=" + idClient + ", date=" + date + ", listeLigneCommande=" + listeLigneCommande + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (idCommande != other.idCommande)
			return false;
		return true;
	}
	
	/*
	- conversion d'une date MySQL (java.sql.Date) vers LocalDate :
		maDateSQL.toLocalDate()

	- conversion d'une LocalDate en date MySQL
    	java.sql.Date.valueOf(maLocalDate)

    - saisie d'une date : sous forme de chaine, puis utilisation d'un formateur de date
    	DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    	LocalDate dateDebut = LocalDate.parse(maStringDate, formatage);

    - affichage d'une date LocalDate :
    	formatage.format(maLocalDate)
    */
	
}
