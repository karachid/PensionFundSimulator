package application;

import java.util.List;

import simulation.cdr.business.SimulerAnnee;
import simulation.cdr.models.Employe;

public class Test {

	public static void main(String[] args) {
		SimulerAnnee sm = new SimulerAnnee();
		List<Employe> list = sm.generateDixMilleEmployes(12, 15482, 212);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}

	}

}
