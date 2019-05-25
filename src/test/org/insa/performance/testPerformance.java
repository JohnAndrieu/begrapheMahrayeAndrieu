package org.insa.performance;

import java.util.ArrayList;
import java.util.Iterator;

/* Lancement des algorithmes et récupération des performances 
 * pour les écrires dans le fichier résultat via la classe ecriture */

public class testPerformance {
	
	private ArrayList<resTestPerformance> listeResultatPerformance;
	private int origine;
	private int destination;
	private float tempsExecutionDijkstra;
	private float tempsExecutionAStar;
	private int nbSommetsDijsktra;
	private int nbSommetsAStar;
	
	public testPerformance() {
		this.listeResultatPerformance = new ArrayList<resTestPerformance>();
	}
	
	public void run (String fichierLecture, String fichierEcriture, int mode){
		
		/* LECTURE */
		
		lecture lect = new lecture(fichierLecture);
		
		/* INITIALISATION */
		
		String carte = lect.getMapName() ;
		Iterator<Integer> origineIter = lect.getListeOrigine().iterator();
		Iterator<Integer> destinationIter = lect.getListeDestination().iterator();
		
		/* LANCEMENT DES ALGORITHMES */
		
		while(origineIter.hasNext()) {
			int origine = origineIter.next() ;
			int destination = destinationIter.next() ;
			
			resTestPerformance res = new resTestPerformance ("/home/jonathan/Documents/begrapheMahrayeAndrieu/"+carte
					, mode, origine, destination);
				
			this.listeResultatPerformance.add(res);
			
		}
		
		/* ECRITURE */
		
		new ecriture (fichierEcriture, carte, this.listeResultatPerformance);
		
	}
	
	public int getOrigine() {
		return this.origine;
	}
	
	public int getDestination() {
		return this.destination;
	}
	
	public float getTempsExecutionDijkstra() {

		return this.tempsExecutionDijkstra;
	}
	
	public float getTempsExecutionAStar() {
		return this.tempsExecutionAStar;
	}
	
	public int getNbSommetsVisitesDijkstra() {
		return this.nbSommetsDijsktra;
	}
	
	public int getNbSommetsVisitesAStar() {
		return this.nbSommetsAStar;
	}
	
}