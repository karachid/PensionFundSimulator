package simulation.cdr.models;

public class Mois {
	
	private int mois;
	private double entree;
	private double sortie;

	public Mois(int mois, double entree, double sortie) {
		super();
		this.mois = mois;
		this.entree = entree;
		this.sortie = sortie;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public double getEntree() {
		return entree;
	}

	public void setEntree(double entree) {
		this.entree = entree;
	}

	public double getSortie() {
		return sortie;
	}

	public void setSortie(double sortie) {
		this.sortie = sortie;
	}

	@Override
	public String toString() {
		return "Mois [mois=" + mois + ", entree=" + entree + ", sortie=" + sortie + "]";
	}
	
}
