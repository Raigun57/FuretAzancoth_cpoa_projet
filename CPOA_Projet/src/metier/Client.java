package metier;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private int numero;
	private String rue;
	private int codePostal;
	private String ville;
	private String pays;

	// Constructeur pour la methode delete de MySQLClientDAO
	public Client(int id) {
		super();
		this.setId(id);
	}

	// Constructeur pour la methode create de MySQLClientDAO
	public Client(String nom, String prenom) {
		super();
		this.setNom(nom);
		this.setPrenom(prenom);
	}

	public Client(int id, String nom, String prenom) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
	}

	// Constructeur pour l'interface graphique
	public Client(String nom, String prenom, String identifiant, String mdp, int numero, String rue, int codePostal,
			String ville, String pays) {
		super();
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMdp(mdp);
		this.setNumero(numero);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setPays(pays);
	}

	// Constructeur pour l'interface graphique
	public Client(int id, String nom, String prenom, String identifiant, String mdp, int numero, String rue,
			int codePostal, String ville, String pays) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMdp(mdp);
		this.setNumero(numero);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setPays(pays);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id <= 0)
			throw new IllegalArgumentException("Id inferieur ou egal a 0 !");
		else
			this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (nom == null || nom.trim().length() == 0) {
			throw new IllegalArgumentException("Nom du client vide !");
		}
		this.nom = nom.trim();
	}

	public String getPrenom() {
		return prenom;
	}

	// Pour la recherche de client par nom prenom
	public String getNomPrenom() {
		return nom + " " + prenom;
	}

	// Pour la recherche de client par prenom nom
	public String getPrenomNom() {
		return prenom + " " + nom;
	}

	public void setPrenom(String prenom) {
		if (prenom == null || prenom.trim().length() == 0) {
			throw new IllegalArgumentException("Prenom du client vide !");
		}
		this.prenom = prenom.trim();
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		if (identifiant == null || identifiant.trim().length() == 0) {
			throw new IllegalArgumentException("Identifiant du client vide !");
		}
		this.identifiant = identifiant.trim();
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		if (mdp == null || mdp.trim().length() == 0) {
			throw new IllegalArgumentException("Mot de passe du client vide !");
		}
		this.mdp = mdp.trim();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		if (numero <= 0) {
			throw new IllegalArgumentException("Numero du client inferieur a 0 !");
		}
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		if (rue == null || rue.trim().length() == 0) {
			throw new IllegalArgumentException("Rue du client vide !");
		}
		this.rue = rue.trim();
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		if (codePostal <= 0) {
			throw new IllegalArgumentException("Code postal inferieur a 0 !");
		}
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		if (ville == null || ville.trim().length() == 0) {
			throw new IllegalArgumentException("Ville du client vide !");
		}
		this.ville = ville.trim();
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		if (pays == null || pays.trim().length() == 0) {
			throw new IllegalArgumentException("Pays du client vide !");
		}
		this.pays = pays.trim();
	}

	@Override
	public String toString() {
		return prenom.concat(" " + nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
