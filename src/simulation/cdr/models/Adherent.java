package simulation.cdr.models;

public class Adherent {
	
	private int ageEmbauche;

	public int getAgeEmbauche() {
		return ageEmbauche;
	}

	public void setAgeEmbauche(int ageEmbauche) {
		this.ageEmbauche = ageEmbauche;
	}

	@Override
	public String toString() {
		return "Adherent [ageEmbauche=" + ageEmbauche + "]";
	}

}
