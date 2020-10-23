package metier;

public class LigneCommande {
	private int idCommande;
	private int idProduit;
	private int quantite;
	private double tarifUnitaire;

	public LigneCommande(int idCommande) {
		super();
		this.setIdCommande(idCommande);
	}

	// Constructeur pour la methode delete de MySQLLigneCommandeDAO
	public LigneCommande(int idCommande, int idProduit) {
		super();
		this.setIdCommande(idCommande);
		this.setIdProduit(idProduit);
	}

	public LigneCommande(int idProduit, int quantite, double tarifUnitaire) {
		super();
		this.setIdProduit(idProduit);
		this.setQuantite(quantite);
		this.setTarifUnitaire(tarifUnitaire);
	}

	public LigneCommande(int idCommande, int idProduit, int quantite, double tarifUnitaire) {
		super();
		this.setIdCommande(idCommande);
		this.setIdProduit(idProduit);
		this.setQuantite(quantite);
		this.setTarifUnitaire(tarifUnitaire);
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		if (idCommande <= 0)
			throw new IllegalArgumentException("Id inferieur ou egal a 0 !");
		else
			this.idCommande = idCommande;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		if (idProduit <= 0)
			throw new IllegalArgumentException("Id inferieur ou egal a 0 !");
		else
			this.idProduit = idProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		if (quantite <= 0)
			throw new IllegalArgumentException("Quantite negative ou nulle !");
		else
			this.quantite = quantite;
	}

	public double getTarifUnitaire() {
		return tarifUnitaire;
	}

	public void setTarifUnitaire(double tarifUnitaire) {
		if (tarifUnitaire < 0)
			throw new IllegalArgumentException("Tarif negatif !");
		else
			this.tarifUnitaire = tarifUnitaire;
	}

	@Override
	public String toString() {
		return "LigneCommande [idCommande=" + idCommande + ", idProduit=" + idProduit + ", quantite=" + quantite
				+ ", tarifUnitaire=" + tarifUnitaire + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LigneCommande other = (LigneCommande) obj;
		if (idCommande != other.idCommande)
			return false;
		if (idProduit != other.idProduit)
			return false;
		return true;
	}

}
