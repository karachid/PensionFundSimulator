package simulation.cdr.models;

import java.util.ArrayList;
import java.util.List;

public class Caisse {
	
	private double entrees;
	private double sortie;
	private List<Employe> employes;
	private List<Retraite> retraites;
	private List<Annee> annees = new ArrayList<Annee>();
	
	public double getEntrees() {
		return entrees;
	}
	public void setEntrees(double entrees) {
		this.entrees = entrees;
	}
	public double getSortie() {
		return sortie;
	}
	public void setSortie(double sortie) {
		this.sortie = sortie;
	}
	public List<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
	public List<Retraite> getRetraites() {
		return retraites;
	}
	public void setRetraites(List<Retraite> retraites) {
		this.retraites = retraites;
	}
	public List<Annee> getAnnees() {
		return annees;
	}
	public void setAnnees(List<Annee> annees) {
		this.annees = annees;
	}
	
}
