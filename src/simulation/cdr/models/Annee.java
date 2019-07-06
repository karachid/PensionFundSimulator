package simulation.cdr.models;

import java.util.ArrayList;
import java.util.List;

public class Annee {
	
	private int annee;
	private List<Mois> lesMois = new ArrayList<Mois>();
	private int nbreEmployes;
	private int nbreRetraites;
	private int nouvRecrutes;
	private int nouvRetraites;
	
	public Annee(int annee) {
		this.annee=annee;
		lesMois.add(new Mois(1, 0, 0));
		lesMois.add(new Mois(2, 0, 0));
		lesMois.add(new Mois(3, 0, 0));
		lesMois.add(new Mois(4, 0, 0));
		lesMois.add(new Mois(5, 0, 0));
		lesMois.add(new Mois(6, 0, 0));
		lesMois.add(new Mois(7, 0, 0));
		lesMois.add(new Mois(8, 0, 0));
		lesMois.add(new Mois(9, 0, 0));
		lesMois.add(new Mois(10, 0, 0));
		lesMois.add(new Mois(11, 0, 0));
		lesMois.add(new Mois(12, 0, 0));
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public List<Mois> getLesMois() {
		return lesMois;
	}

	public void setLesMois(List<Mois> lesMois) {
		this.lesMois = lesMois;
	}

	public int getNbreEmployes() {
		return nbreEmployes;
	}

	public void setNbreEmployes(int nbreEmployes) {
		this.nbreEmployes = nbreEmployes;
	}

	public int getNbreRetraites() {
		return nbreRetraites;
	}

	public void setNbreRetraites(int nbreRetraites) {
		this.nbreRetraites = nbreRetraites;
	}

	public int getNouvRecrutes() {
		return nouvRecrutes;
	}

	public void setNouvRecrutes(int nouvRecrutes) {
		this.nouvRecrutes = nouvRecrutes;
	}

	public int getNouvRetraites() {
		return nouvRetraites;
	}

	public void setNouvRetraites(int nouvRetraites) {
		this.nouvRetraites = nouvRetraites;
	}

	@Override
	public String toString() {
		return "Annee [annee=" + annee + ", lesMois=" + lesMois + "]";
	}
	
}
