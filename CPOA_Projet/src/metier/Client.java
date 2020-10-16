package metier;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	
	//Constructeur pour la methode delete de MySQLClientDAO
	public Client(int id) {
		super();
		this.setId(id);
	}
	
	//Constructeur pour la methode create de MySQLClientDAO
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id <= 0)
			throw new IllegalArgumentException("Id inferieur ou egal a 0 !");
		else 
			this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (nom==null || nom.trim().length()==0) {
			throw new IllegalArgumentException("Nom du client vide !");
		}
		this.nom = nom.trim();
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		if (prenom==null || prenom.trim().length()==0) {
			throw new IllegalArgumentException("Prenom du client vide !");
		}
		this.prenom = prenom.trim();
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
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
