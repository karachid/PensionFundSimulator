package simulation.cdr.models;

import javafx.beans.property.SimpleIntegerProperty;

public class LineEchantillon {
	
	private final SimpleIntegerProperty numEchantillon;
	private final SimpleIntegerProperty Annee;
	private final SimpleIntegerProperty Entrees;
	private final SimpleIntegerProperty Sorties;
	private final SimpleIntegerProperty nbEmployes;
	private final SimpleIntegerProperty nouveauxEmployes;
	private final SimpleIntegerProperty nbRetraites;
	private final SimpleIntegerProperty nouveauxRetraites;
	
	public LineEchantillon(Integer numEchantillon, Integer annee, Integer entrees, Integer sorties, Integer nbEmployes,
			Integer nouveauxEmployes, Integer nbRetraites, Integer nouveauxRetraites) {
		super();
		this.numEchantillon = new SimpleIntegerProperty(numEchantillon);
		Annee = new SimpleIntegerProperty(annee);
		Entrees = new SimpleIntegerProperty(entrees);
		Sorties = new SimpleIntegerProperty(sorties);
		this.nbEmployes = new SimpleIntegerProperty(nbEmployes);
		this.nouveauxEmployes = new SimpleIntegerProperty(nouveauxEmployes);
		this.nbRetraites = new SimpleIntegerProperty(nbRetraites);
		this.nouveauxRetraites = new SimpleIntegerProperty(nouveauxRetraites);
	}
	
	public Integer getNumEchantillon() {
		return numEchantillon.get();
	}
	public Integer getAnnee() {
		return Annee.get();
	}
	public Integer getEntrees() {
		return Entrees.get();
	}
	public Integer getSorties() {
		return Sorties.get();
	}
	public Integer getNbEmployes() {
		return nbEmployes.get();
	}
	public Integer getNouveauxEmployes() {
		return nouveauxEmployes.get();
	}
	public Integer getNbRetraites() {
		return nbRetraites.get();
	}
	public Integer getNouveauxRetraites() {
		return nouveauxRetraites.get();
	}
	
	
	
}
