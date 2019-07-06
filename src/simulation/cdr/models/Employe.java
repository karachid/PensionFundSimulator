package simulation.cdr.models;

public class Employe extends Adherent {
	
	private int ageActuel;
	private double salaireActuel;
	private double salaireEmbauche;
	
	
	
	public Employe() {
		super();
	}

	public Employe(int ageActuel, double salaireActuel, double salaireEmbauche) {
		super();
		this.ageActuel = ageActuel;
		this.salaireActuel = salaireActuel;
		this.salaireEmbauche = salaireEmbauche;
	}

	public int getAgeActuel() {
		return ageActuel;
	}

	public void setAgeActuel(int ageActuel) {
		this.ageActuel = ageActuel;
	}

	public double getSalaireActuel() {
		return salaireActuel;
	}

	public void setSalaireActuel(double salaireActuel) {
		this.salaireActuel = salaireActuel;
	}

	public double getSalaireEmbauche() {
		return salaireEmbauche;
	}

	public void setSalaireEmbauche(double salaireEmbauche) {
		this.salaireEmbauche = salaireEmbauche;
	}

	@Override
	public String toString() {
		return "Employe [ageActuel=" + ageActuel + ", salaireActuel=" + salaireActuel + ", salaireEmbauche="
				+ salaireEmbauche + " ageEmbauche=" + this.getAgeEmbauche() + "]";
	}
	
}
