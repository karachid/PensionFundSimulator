package simulation.cdr.models;

public class Retraite extends Adherent{
	
	private double salaireRetraite;
	private double dernierSalaire;
	
	public Retraite() {
		super();
	}

	public Retraite(double salaireRetraite, double dernierSalaire) {
		super();
		this.salaireRetraite = salaireRetraite;
		this.dernierSalaire = dernierSalaire;
	}

	public double getSalaireRetraite() {
		return salaireRetraite;
	}

	public void setSalaireRetraite(double salaireRetraite) {
		this.salaireRetraite = salaireRetraite;
	}

	public double getDernierSalaire() {
		return dernierSalaire;
	}

	public void setDernierSalaire(double dernierSalaire) {
		this.dernierSalaire = dernierSalaire;
	}

	@Override
	public String toString() {
		return "Retraite [salaireRetraite=" + salaireRetraite + ", dernierSalaire=" + dernierSalaire + " ageEmbauche=" + this.getAgeEmbauche() + "]";
	}
	
}
