package simulation.cdr.business;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import simulation.cdr.generator.Generator;
import simulation.cdr.models.Adherent;
import simulation.cdr.models.Annee;
import simulation.cdr.models.Caisse;
import simulation.cdr.models.Echantillon;
import simulation.cdr.models.Employe;
import simulation.cdr.models.Retraite;

public class SimulerAnnee {
	
	/*
	 Progress indicators
	 * */
	@FXML final  ProgressIndicator  progIndSimple = new ProgressIndicator(0);	
	/*
	 Fonction de la loi uniforme sur un intervalle
	 * */
	int getValueFromInterval(int min, int max){
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	/*
	 Fonction qui retourne le salaire actuel en fonction d'une valeur aléatoire générée
	 * */
	public double salaireActuel(double alea) {

		if(alea>=0 & alea<0.05) {	
			return (double)getValueFromInterval(30000, 40000);
		}
		else if(alea>=0.05 & alea<0.15) {
			return (double)getValueFromInterval(20000, 29999);
		}
		else if(alea>=0.15 & alea<0.25) {
			return (double)getValueFromInterval(15000, 19999);
		}
		else if(alea>=0.25 & alea<0.40) {
			return (double)getValueFromInterval(10000, 14999);
		}
		else if(alea>=0.40 & alea<0.60) {
			return (double)getValueFromInterval(7500, 9999);
		}
		else if(alea>=0.60 & alea<0.80) {
			return (double)getValueFromInterval(5000, 6999);
		}
		else if(alea>=0.80 & alea<=1.0) {
			return (double)getValueFromInterval(3000, 4999);
		}
		return 0;
	}
	
	/*
	 Fonction qui renvoie l'age actuel en fontion d'un aléa passé en paramètre
	 * */
	public int ageActuel(double alea) {
		if(alea>=0 & alea<=0.40) {
			return getValueFromInterval(51,60);
		}
		else if(alea>0.40 & alea<0.70) {
			return getValueFromInterval(41,50);
		}
		else if(alea>=0.70 & alea<0.90) {
			return getValueFromInterval(31,40);
		}
		else if(alea>=0.90 & alea<=1.0) {
			return getValueFromInterval(18,30);
		}
		return 0;
	}
	
	/*
	 Fonction qui renvoie l'age à l'Embauche en fonction d'un alea passé comme paramètre
	 * */
	public int ageEmbauche(double alea) {
		if(alea>=0 & alea <0.05) {
			return getValueFromInterval(18,20);
		}
		else if(alea>=0.05 & alea<0.25) {
			return getValueFromInterval(21,23);
		}
		else if(alea>=0.25 & alea<=0.50) {
			return getValueFromInterval(24,26);
		}
		else if(alea>0.50 & alea<0.75) {
			return getValueFromInterval(27,30);
		}
		else if(alea>=0.75 & alea<0.95) {
			return getValueFromInterval(31,35);
		}
		else if(alea>=0.95 & alea<=1) {
			return getValueFromInterval(36,40);
		}
		return 0;
	}
	
	/*
	 Fonction qui renvoie le salaire à l'embauche en fonction d'une valeur aléatoire
	 * */
	public double salaireEmbauche(double alea) {
		if(alea>=0 & alea<0.05) {
			return getValueFromInterval(24000,32000);
		}
		else if(alea>=0.05 & alea<0.10) {
			return getValueFromInterval(16000,23999);
		}
		else if(alea>=0.10 & alea<0.20) {
			return getValueFromInterval(12000,15999);
		}
		else if(alea>=0.20 & alea<=0.40) {
			return getValueFromInterval(8000,11999);
		}
		else if(alea>0.40 & alea<0.60) {
			return getValueFromInterval(6000,7999);
		}
		else if(alea>=0.60 & alea<0.80) {
			return getValueFromInterval(4000,5999);
		}
		else if(alea>=0.80 & alea<=10) {
			return getValueFromInterval(2400,3999);
		}
		return 0;
	}
	
	/*
	 Fonction qui génère dix milles employés au début de la simulation 
	 * */
	public List<Employe> generateDixMilleEmployes(int iX, int iY, int iZ) {
		int ix=iX, iy=iY, iz=iZ, i=0;
		List<Employe> employes = new ArrayList<Employe>();
		Employe emp;
		
		while(i<10000) {
			emp = new Employe();
			emp.setSalaireActuel(salaireActuel(Generator.alea(ix, iy, iz)));
			ix+=5;iy+=5;iz+=5;
			emp.setAgeActuel(ageActuel(Generator.alea(ix, iy, iz)));
			ix+=5;iy+=5;iz+=5;
			emp.setSalaireEmbauche(salaireEmbauche(Generator.alea(ix, iy, iz)));
			ix+=5;iy+=5;iz+=5;
			emp.setAgeEmbauche(ageEmbauche(Generator.alea(ix, iy, iz)));	
			if(emp.getAgeEmbauche()<emp.getAgeActuel() && emp.getSalaireActuel()>emp.getSalaireEmbauche()) {
				employes.add(emp);
				i++;
			}
			ix+=5;iy+=5;iz+=5;
		}
		return employes;
	}
	
//	public List<Employe> generateDixMilleEmployes(int iX, int iY, int iZ) {
//		int ix=iX, iy=iY, iz=iZ;
//		List<Employe> employes = new ArrayList<Employe>();
//		Employe emp;
//		
//		for(int i=0;i<10000;i++) {
//			emp = new Employe();
//			emp.setSalaireActuel(salaireActuel(Generator.alea(ix, iy, iz)));
//			ix+=5;iy+=5;iz+=5;
//			emp.setAgeActuel(ageActuel(Generator.alea(ix, iy, iz)));
//			ix+=5;iy+=5;iz+=5;
//			emp.setSalaireEmbauche(salaireEmbauche(Generator.alea(ix, iy, iz)));
//			ix+=5;iy+=5;iz+=5;
//			emp.setAgeEmbauche(ageEmbauche(Generator.alea(ix, iy, iz)));
//			employes.add(emp);
//		}
//		return employes;
//	}
	
	/*
	 Fonction générant mille retraites au début de la simulation
	 * */
	public List<Retraite> generateMilleRetraites(int iX, int iY, int iZ, int ageRetraite){
		int ix=iX,iy=iY,iz=iZ;
		List<Retraite> retraites = new ArrayList<Retraite>();
		Retraite ret;
		for(int i=0;i<1000;i++) {
			ret = new Retraite();
			ret.setAgeEmbauche(ageEmbauche(Generator.alea(ix, iy, iz)));
			//ix+=10;iy+=10;iz+=10;
			ret.setDernierSalaire(salaireActuel(Generator.alea(ix, iy, iz)));
			//ix+=10;iy+=10;iz+=10;
			ret.setSalaireRetraite(((ageRetraite-ret.getAgeEmbauche())*2.5)/100*ret.getDernierSalaire());
			retraites.add(ret);
			//ix+=10;iy+=10;iz+=10;
		}
		return retraites;
	}
	
//	public void cotisation(Caisse caisse) {
//		for(int i=0;i<caisse.getEmployes().size();i++) {
//			if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=5000) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.05));
//			}
//			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>5000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=7500) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.06));
//			}
//			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>7500 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=10000) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.07));
//			}
//			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>10000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=40000) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.10));
//			}
//		}
//		for(int i=0;i<caisse.getRetraites().size();i++) {
//			
//		}
//	}
//	
//	public void cotisationMajoree(Caisse caisse) {
//		for(int i=0;i<caisse.getEmployes().size();i++) {
//			if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=5000) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.07));
//			}
//			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>5000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=7500) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.08));
//			}
//			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>7500 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=10000) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.09));
//			}
//			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>10000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=40000) {
//				caisse.setEntrees(caisse.getEntrees()+(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.12));
//			}
//		}
//		for(int i=0;i<caisse.getRetraites().size();i++) {
//			
//		}
//	}
	
	public void cotisation(Caisse caisse, int annee, int mois) {
		for(int i=0;i<caisse.getEmployes().size();i++) {
			if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=5000) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.05);
			}
			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>5000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=7500) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.06);
			}
			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>7500 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=10000) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.07);
			}
			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>10000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=40000) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.10);
			}
		}
		for(int i=0;i<caisse.getRetraites().size();i++) {
			caisse.getAnnees().get(annee).getLesMois().get(mois).setSortie(caisse.getAnnees().get(annee).getLesMois().get(mois).getSortie()+((Retraite)caisse.getRetraites().get(i)).getSalaireRetraite());
		}
	}
	
	public void cotisationMajoree(Caisse caisse, int annee, int mois) {
		for(int i=0;i<caisse.getEmployes().size();i++) {
			if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=5000) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.07);
			}
			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>5000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=7500) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.08);
			}
			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>7500 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=10000) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.09);
			}
			else if(((Employe)caisse.getEmployes().get(i)).getSalaireActuel()>10000 & ((Employe)caisse.getEmployes().get(i)).getSalaireActuel()<=40000) {
				caisse.getAnnees().get(annee).getLesMois().get(mois).setEntree(caisse.getAnnees().get(annee).getLesMois().get(mois).getEntree()+((Employe)caisse.getEmployes().get(i)).getSalaireActuel()*0.12);
			}
		}
		for(int i=0;i<caisse.getRetraites().size();i++) {
			caisse.getAnnees().get(annee).getLesMois().get(mois).setSortie(caisse.getAnnees().get(annee).getLesMois().get(mois).getSortie()+((Retraite)caisse.getRetraites().get(i)).getSalaireRetraite());
		}
	}

	/*
	 Ajout des années simiulées à la caisse
	 * */
	public void ajouterAnnees(Caisse caisse, int nbreAnnee) {
		for(int i=0;i<nbreAnnee;i++) {
			caisse.getAnnees().add(new Annee(2017+i));
		}
	}
	
	/*
	 Fonction retournant le nombre d'employés à recruter chaque année
	 * */
	public int nbreEmployeRecrutes(int min, int max, int iX, int iY, int iZ) {
		int range = (max - min) + 1;     
		   return (int)(Generator.alea(iX, iY, iZ) * range) + min;
	}
	
	/*
	 Fonction incrémentant l'age actuel de l'ensemble des employés
	 * */
	public void miseAJourAgeActuelEmp(Caisse caisse) {
	for(int i=0;i<caisse.getEmployes().size();i++) {
			(caisse.getEmployes().get(i)).setAgeActuel((caisse.getEmployes().get(i)).getAgeActuel()+1);
		}
	}
	
	/*
	 Supprimer l'employé qui devient retraité et le remplacer par un objet retraité
	 * */
	public void convertEmptoRet(Caisse caisse, int ageRetraite) {
		Retraite ret;
		for(int i=0;i<caisse.getEmployes().size();i++) {
			if(caisse.getEmployes().get(i).getAgeActuel()+1==ageRetraite) {
				 ret = new Retraite();
				 ret.setAgeEmbauche(caisse.getEmployes().get(i).getAgeEmbauche());
				 ret.setDernierSalaire(caisse.getEmployes().get(i).getSalaireActuel());
				 ret.setSalaireRetraite(((ageRetraite-ret.getAgeEmbauche())*2.5)/100*ret.getDernierSalaire());
				 caisse.getRetraites().add(ret);
				 caisse.getEmployes().remove(i);
			}
		}
	}
	
	public int getNouveauxRetraites(Caisse c, int ageRetraite) {
		int cmp=0;
		for(int i=0;i<c.getEmployes().size();i++) {
			if(c.getEmployes().get(i).getAgeActuel()+1==ageRetraite) {
				cmp++;
			}
		}
		return cmp;
	}
	
//	public void deleteNouveauxRetraitesFromListEmploye(Caisse c, int ageRetraite) {
//		for(int i=0;i<c.getEmployes().size();i++) {
//			if(c.getEmployes().get(i).getAgeActuel()+1==ageRetraite) {
//				c.getEmployes().remove(i);
//			}
//		}
//	}
	
	/*
	 Fonction principale de la simulation faisant appel aux autres méthodes précedement définies,
	 Cette fonction adopte l'approche dite période fixe, en l'occurence le mois.
	 Nous explorons la totalité du système à chaque fin du mois.
	 * */
	public Caisse simuler(int iX, int iY, int iZ, int ageRetraite, int annee) {
		//Récupération des germes
		int ix=iX, iy=iY,iz=iZ;
		
		//Création d'un objet Caisse
		Caisse caisse = new Caisse();
		
		//Ajout de dix milles employés à la caisse
		caisse.setEmployes(generateDixMilleEmployes(iX, iY, iZ));
		
		//Ajout de mille retraités à la caisse
		caisse.setRetraites(generateMilleRetraites(iX, iY, iZ, ageRetraite));
		
		//Appel de la méthode ajout des années à la caisse
		ajouterAnnees(caisse, annee-((java.time.LocalDate.now()).getYear())+1);
		
		 //Boucle principale de la simulation !! -- Nous adoptrons une approche de simulation par période fixe.
		for(int i=0, year=2017;i<annee-((java.time.LocalDate.now()).getYear())+1;i++,year++) {
			
			progIndSimple.setProgress(i);
			
			
			/*
			 Augmentation de salaire tous les 5 ans, dernier a eu lieu en 2015
			 * */
			if(year%5==0) {
				augmenterSalaire(caisse);
			}
			
			caisse.getAnnees().get(i).setNouvRecrutes(nbreEmployeRecrutes(200, 400, ix, iy, iz));
			caisse.getAnnees().get(i).setNouvRetraites(getNouveauxRetraites(caisse, ageRetraite));
			if(i==0) {
				caisse.getAnnees().get(i).setNbreRetraites(caisse.getRetraites().size());
			}
			else {
				caisse.getAnnees().get(i).setNbreRetraites(caisse.getAnnees().get(i-1).getNbreRetraites()+caisse.getAnnees().get(i-1).getNouvRetraites());
			}
			if(i==0) {
				caisse.getAnnees().get(i).setNbreEmployes(caisse.getEmployes().size()+caisse.getAnnees().get(i).getNouvRecrutes());
			}
			else {
				caisse.getAnnees().get(i).setNbreEmployes(caisse.getAnnees().get(i-1).getNbreEmployes()-caisse.getAnnees().get(i-1).getNouvRetraites()+caisse.getAnnees().get(i).getNouvRecrutes());
			}
			
			System.out.println("nbre de employes recrutes = " + caisse.getAnnees().get(i).getNouvRecrutes());
			caisse.getEmployes().addAll(nouveauxRecrutes(caisse.getAnnees().get(i).getNouvRecrutes(), iX, iY, iZ));
			System.out.println("****************** Debut " +i+ "*********************");
			for(int j=0;j<12;j++) {
				cotisation(caisse, i, j);
			}
			
			miseAJourAgeActuelEmp(caisse);

			System.out.println(caisse.getAnnees().get(i).toString());
				
			System.out.println("Nbre employes = " + caisse.getAnnees().get(i).getNbreEmployes());
			System.out.println("Nbre retraites = " + caisse.getAnnees().get(i).getNbreRetraites());
			System.out.println("Nbre de retaites par an  : " +caisse.getAnnees().get(i).getNouvRetraites());
			
			System.out.println("****************** Fin " +i+ "*********************");
			ix=ix+5;
			iy=iy+5;
			iz=iz+5;
			
			
			convertEmptoRet(caisse, ageRetraite);
			
		}
		return caisse;
	}
	
	public Caisse simulerAgeVar(int iX, int iY, int iZ, int ageRetraite, int annee) {
		//Récupération des germes
		int ix=iX, iy=iY,iz=iZ;
		
		//Création d'un objet Caisse
		Caisse caisse = new Caisse();
		
		//Ajout de dix milles employés à la caisse
		caisse.setEmployes(generateDixMilleEmployes(iX, iY, iZ));
		
		//Ajout de mille retraités à la caisse
		caisse.setRetraites(generateMilleRetraites(iX, iY, iZ, ageRetraite));
		
		//Appel de la méthode ajout des années à la caisse
		ajouterAnnees(caisse, annee-((java.time.LocalDate.now()).getYear())+1);
		
		 //Boucle principale de la simulation !! -- Nous adoptrons une approche de simulation par période fixe.
		for(int i=0, year=2017;i<annee-((java.time.LocalDate.now()).getYear())+1;i++,year++) {
			
			System.out.println(year + " = " + ageRetraite);
			
			/*
			 Augmentation de salaire tous les 5 ans, dernier a eu lieu en 2015
			 * */
			if(year%5==0) {
				augmenterSalaire(caisse);
			}
			
			caisse.getAnnees().get(i).setNouvRecrutes(nbreEmployeRecrutes(200, 400, ix, iy, iz));
			caisse.getAnnees().get(i).setNouvRetraites(getNouveauxRetraites(caisse, ageRetraite));
			if(i==0) {
				caisse.getAnnees().get(i).setNbreRetraites(caisse.getRetraites().size());
			}
			else {
				caisse.getAnnees().get(i).setNbreRetraites(caisse.getAnnees().get(i-1).getNbreRetraites()+caisse.getAnnees().get(i-1).getNouvRetraites());
			}
			if(i==0) {
				caisse.getAnnees().get(i).setNbreEmployes(caisse.getEmployes().size()+caisse.getAnnees().get(i).getNouvRecrutes());
			}
			else {
				caisse.getAnnees().get(i).setNbreEmployes(caisse.getAnnees().get(i-1).getNbreEmployes()-caisse.getAnnees().get(i-1).getNouvRetraites()+caisse.getAnnees().get(i).getNouvRecrutes());
			}
			
			System.out.println("nbre de employes recrutes = " + caisse.getAnnees().get(i).getNouvRecrutes());
			caisse.getEmployes().addAll(nouveauxRecrutes(caisse.getAnnees().get(i).getNouvRecrutes(), iX, iY, iZ));
			System.out.println("****************** Debut " +i+ "*********************");
			for(int j=0;j<12;j++) {
				cotisationMajoree(caisse, i, j);
			}
			
			miseAJourAgeActuelEmp(caisse);

			System.out.println(caisse.getAnnees().get(i).toString());
				
			System.out.println("Nbre employes = " + caisse.getAnnees().get(i).getNbreEmployes());
			System.out.println("Nbre retraites = " + caisse.getAnnees().get(i).getNbreRetraites());
			System.out.println("Nbre de retaites par an  : " +caisse.getAnnees().get(i).getNouvRetraites());
			
			System.out.println("****************** Fin " +i+ "*********************");
			ix=ix+5;
			iy=iy+5;
			iz=iz+5;
			
			convertEmptoRet(caisse, ageRetraite);
			
			if(year==2017) {
				ageRetraite=62;
			}
			if(year==2019) {
				ageRetraite=65;
			}
			
		}
		return caisse;
	}
	
	
	public Echantillon simulerEchantillon(int iX, int iY, int iZ, int ageRetraite, int annee, int nbEchantillon) {
		int ix=iX, iy=iY,iz=iZ;
		Echantillon echantillon = new Echantillon();
		Caisse caisse;
		
		// Simulation de l'Echantillon
		for(int i=0;i<nbEchantillon;i++) {
			
			System.out.println("################################### Debut Echantillon  = " + i + " ######################################");
			
			caisse=new Caisse();
			
			//Ajout de dix milles employés à la caisse
			caisse.setEmployes(generateDixMilleEmployes(iX, iY, iZ));
			
			//Ajout de mille retraités à la caisse
			caisse.setRetraites(generateMilleRetraites(iX, iY, iZ, ageRetraite));
			
			//Appel de la méthode ajout des années à la caisse
			ajouterAnnees(caisse, annee-((java.time.LocalDate.now()).getYear())+1);
			
			for(int j=0, year=2017;j<annee-((java.time.LocalDate.now()).getYear())+1;j++,year++) {
				
				/*
				 Augmentation de salaire tous les 5 ans, dernier a eu lieu en 2015
				 * */
				if(year%5==0) {
					augmenterSalaire(caisse);
				}
				
				caisse.getAnnees().get(j).setNouvRecrutes(nbreEmployeRecrutes(200, 400, ix, iy, iz));
				caisse.getAnnees().get(j).setNouvRetraites(getNouveauxRetraites(caisse, ageRetraite));
				if(j==0) {
					caisse.getAnnees().get(j).setNbreRetraites(caisse.getRetraites().size());
				}
				else {
					caisse.getAnnees().get(j).setNbreRetraites(caisse.getAnnees().get(j-1).getNbreRetraites()+caisse.getAnnees().get(j-1).getNouvRetraites());
				}
				if(j==0) {
					caisse.getAnnees().get(j).setNbreEmployes(caisse.getEmployes().size()+caisse.getAnnees().get(j).getNouvRecrutes());
				}
				else {
					caisse.getAnnees().get(j).setNbreEmployes(caisse.getAnnees().get(j-1).getNbreEmployes()-caisse.getAnnees().get(j-1).getNouvRetraites()+caisse.getAnnees().get(j).getNouvRecrutes());
				}
				
				System.out.println("nbre de employes recrutes = " + caisse.getAnnees().get(j).getNouvRecrutes());
				
				caisse.getEmployes().addAll(nouveauxRecrutes(caisse.getAnnees().get(j).getNouvRecrutes(), iX, iY, iZ));
				
				System.out.println("****************** Debut " +j+ "*********************");
				for(int k=0;k<12;k++) {
					cotisation(caisse, j, k);
				}
				
				miseAJourAgeActuelEmp(caisse);	
				
			    System.out.println(caisse.getAnnees().get(j).toString());
			    
			    System.out.println("Nbre employes = " + caisse.getAnnees().get(j).getNbreEmployes());
				System.out.println("Nbre retraites = " + caisse.getAnnees().get(j).getNbreRetraites());
				System.out.println("Nbre de retaites par an  : " +caisse.getAnnees().get(j).getNouvRetraites());
				
				System.out.println("****************** Fin " +j+ "*********************");
				ix=ix+5;
				iy=iy+5;
				iz=iz+5;
				
				convertEmptoRet(caisse, ageRetraite);
				
			}
			System.out.println("################################### Fin Echantillon  = " + i + " ######################################");
			echantillon.getLesCaisses().add(caisse);
			ix=ix+5;
			iy=iy+5;
			iz=iz+5;
		}
		
		return echantillon;
	}
	
	public Echantillon simulerEchantillonAgeVar(int iX, int iY, int iZ, int ageRetraite, int annee, int nbEchantillon) {
		int ix=iX, iy=iY,iz=iZ;
		Echantillon echantillon = new Echantillon();
		Caisse caisse;
		
		// Simulation de l'Echantillon
		for(int i=0;i<nbEchantillon;i++) {
			
			System.out.println("################################### Debut Echantillon  = " + i + " ######################################");
			
			caisse=new Caisse();
			
			//Ajout de dix milles employés à la caisse
			caisse.setEmployes(generateDixMilleEmployes(iX, iY, iZ));
			
			//Ajout de mille retraités à la caisse
			caisse.setRetraites(generateMilleRetraites(iX, iY, iZ, ageRetraite));
			
			//Appel de la méthode ajout des années à la caisse
			ajouterAnnees(caisse, annee-((java.time.LocalDate.now()).getYear())+1);
			
			for(int j=0, year=2017;j<annee-((java.time.LocalDate.now()).getYear())+1;j++,year++) {
				
				System.out.println(year + " = " + ageRetraite);
				
				/*
				 Augmentation de salaire tous les 5 ans, dernier a eu lieu en 2015
				 * */
				if(year%5==0) {
					augmenterSalaire(caisse);
				}
				
				caisse.getAnnees().get(j).setNouvRecrutes(nbreEmployeRecrutes(200, 400, ix, iy, iz));
				caisse.getAnnees().get(j).setNouvRetraites(getNouveauxRetraites(caisse, ageRetraite));
				if(j==0) {
					caisse.getAnnees().get(j).setNbreRetraites(caisse.getRetraites().size());
				}
				else {
					caisse.getAnnees().get(j).setNbreRetraites(caisse.getAnnees().get(j-1).getNbreRetraites()+caisse.getAnnees().get(j-1).getNouvRetraites());
				}
				if(j==0) {
					caisse.getAnnees().get(j).setNbreEmployes(caisse.getEmployes().size()+caisse.getAnnees().get(j).getNouvRecrutes());
				}
				else {
					caisse.getAnnees().get(j).setNbreEmployes(caisse.getAnnees().get(j-1).getNbreEmployes()-caisse.getAnnees().get(j-1).getNouvRetraites()+caisse.getAnnees().get(j).getNouvRecrutes());
				}
				
				System.out.println("nbre de employes recrutes = " + caisse.getAnnees().get(j).getNouvRecrutes());
				
				caisse.getEmployes().addAll(nouveauxRecrutes(caisse.getAnnees().get(j).getNouvRecrutes(), iX, iY, iZ));
				
				System.out.println("****************** Debut " +j+ "*********************");
				for(int k=0;k<12;k++) {
					cotisationMajoree(caisse, j, k);
				}
				
				miseAJourAgeActuelEmp(caisse);	
				
			    System.out.println(caisse.getAnnees().get(j).toString());
			    
			    System.out.println("Nbre employes = " + caisse.getAnnees().get(j).getNbreEmployes());
				System.out.println("Nbre retraites = " + caisse.getAnnees().get(j).getNbreRetraites());
				System.out.println("Nbre de retaites par an  : " +caisse.getAnnees().get(j).getNouvRetraites());
				
				System.out.println("****************** Fin " +j+ "*********************");
				ix=ix+5;
				iy=iy+5;
				iz=iz+5;
				
				convertEmptoRet(caisse, ageRetraite);
				
				if(year==2017) {
					ageRetraite=62;
				}
				if(year==2019) {
					ageRetraite=65;
				}
				
			}
			ageRetraite=60;
			System.out.println("################################### Fin Echantillon  = " + i + " ######################################");
			echantillon.getLesCaisses().add(caisse);
			ix=ix+5;
			iy=iy+5;
			iz=iz+5;
		}
		
		return echantillon;
	}
	
	/*
	 Fonction permettant d'augmenter le salaire de tous les employés
	 * */
	public void augmenterSalaire(Caisse c) {
		for(int i=0;i<c.getEmployes().size();i++) {
			c.getEmployes().get(i).setSalaireActuel(c.getEmployes().get(i).getSalaireActuel()+c.getEmployes().get(i).getSalaireActuel()*0.1);
		}
	}
		
	
	public List<Employe> nouveauxRecrutes(int nbARecruter, int iX, int iY, int iZ){
		List<Employe> nouv = new ArrayList<Employe>();
		Employe emp;
		for(int i=0;i<nbARecruter; i++) {
			emp=new Employe();
			emp.setAgeActuel(ageActuel(Generator.alea(iX, iY, iZ)));
			emp.setAgeEmbauche(emp.getAgeActuel());
			emp.setSalaireActuel(salaireActuel(Generator.alea(iX, iY, iZ)));
			emp.setSalaireEmbauche(emp.getSalaireActuel());
			nouv.add(emp);
		}
		return nouv;
	}

}
