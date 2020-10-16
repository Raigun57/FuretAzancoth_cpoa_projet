package metier;

public class Categorie {

	private int id;
	private String titre;
	private String visuel;
	
	//Constructeur pour la methode delete de MySQLCategorieDAO
	public Categorie(int id) {
		this.setId(id);
	}
	
	//Constructeur pour la methode create de MySQLCategorieDAO
	public Categorie(String titre, String visuel) {
		this.setTitre(titre);
		this.setVisuel(visuel);
	}
	
	public Categorie(int id, String titre, String visuel) {
		this.setId(id);
		this.setTitre(titre);
		this.setVisuel(visuel);
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		if(id <= 0)
			throw new IllegalArgumentException("Id inferieur ou egal a 0 !");
		else 
			this.id = id;
		}
	
	public String getTitre() {
		return this.titre;
	}
	
	public void setTitre(String titre) {
		if (titre==null || titre.trim().length()==0) {
			throw new IllegalArgumentException("Titre de la categorie vide !");
		}
		this.titre = titre.trim();
	}
	
	public String getVisuel() {
		return this.visuel;
	}
	
	public void setVisuel(String visuel) {
		if (visuel==null || visuel.trim().length()==0) {
			throw new IllegalArgumentException("Visuel de la categorie vide !");
		}
		this.visuel = visuel.trim();
	}
	
	/*
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", titre=" + titre + ", visuel=" + visuel + "]";
	}
	*/
	
	@Override
	public String toString() {
		return titre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
