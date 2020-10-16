package metier;

public class Produit {
	private int id;
	private String nom;
	private String description;
	private double tarif;
	private String visuel;
	private int idCateg;
	
	//Constructeur pour la methode delete de MySQLProduitDAO
	public Produit(int id) {
		super();
		this.setId(id);
	}
		
	//Constructeur pour la methode create de MySQLProduitDAO
	public Produit(String nom, String description, double tarif, String visuel, int idCateg) {
		super();
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setVisuel(visuel);
		this.setIdCateg(idCateg);
	}
	
	public Produit(int id, String nom, String description, double tarif, String visuel, int idCateg) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setVisuel(visuel);
		this.setIdCateg(idCateg);
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
			throw new IllegalArgumentException("Nom du produit vide !");
		}
		this.nom = nom.trim();
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		if (description==null || description.trim().length()==0) {
			throw new IllegalArgumentException("Description du produit vide !");
		}
		this.description = description.trim();
	}
	
	public double getTarif() {
		return tarif;
	}
	
	public void setTarif(double tarif) {
		if(tarif < 0)
			throw new IllegalArgumentException("Tarif negatif !");
		else 
			this.tarif = tarif;
	}
	
	public String getVisuel() {
		return visuel;
	}
	
	public void setVisuel(String visuel) {
		if (visuel==null || visuel.trim().length()==0) {
			throw new IllegalArgumentException("Visuel du produit vide !");
		}
		this.visuel = visuel.trim();
	}
	
	public int getIdCateg() {
		return idCateg;
	}
	
	public void setIdCateg(int idCateg) {
		if(idCateg <= 0)
			throw new IllegalArgumentException("Id inferieur ou egal a 0 !");
		else 
			this.idCateg = idCateg;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", description=" + description + ", tarif=" + tarif + ", visuel="
				+ visuel + ", idCateg=" + idCateg + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
