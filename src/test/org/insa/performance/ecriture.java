package org.insa.performance;

import java.io.FileWriter;
import java.util.ArrayList;

/* Ecriture dans le fichier résultat des performances des algorithmes */

public class ecriture {
	
	public ecriture (String fileWrite, String mapName, ArrayList<resTestPerformance> resultatPerf) {
		
	String espace = " ";
	String retour = "\n" ;
	String fileEnTete = "Carte, Origine, Destination, Temps CPU Dijkstra (en ms), "
			+ "Nb Sommets Dijkstra, Nb iter Dijkstra, Temps CPU A* (en ms), Nb Sommets AStar, Nb iter AStar"; // En tete du fichier
		
	try{
		FileWriter fileWriter = new FileWriter("/home/jonathan/Documents/begrapheMahrayeAndrieu/src/test/org/insa/"+fileWrite); // définir l'arborescence
		fileWriter.append(fileEnTete); //on écrit l'en-tete 
		fileWriter.append(retour); // forcer le passage à la ligne
		
		for (resTestPerformance ligne : resultatPerf) {
			fileWriter.append(mapName);
			fileWriter.append(espace);  // écrire une ligne dans le fichier resultat.txt
			fileWriter.append(String.valueOf(ligne.getOrigine()));
	 		fileWriter.append(espace);
	 		fileWriter.append(String.valueOf(ligne.getDestination()));
	 		fileWriter.append(espace);
	 		fileWriter.append(String.valueOf(ligne.getTempsExecutionDijkstra()));
	 		fileWriter.append(espace);
	 		fileWriter.append(String.valueOf(ligne.getNbSommetsVisitesDijkstra()));
	 		fileWriter.append(espace);
	 		fileWriter.append(String.valueOf(ligne.getNbIterDijkstra()));
	 		fileWriter.append(espace);
	 		fileWriter.append(String.valueOf(ligne.getTempsExecutionAStar()));
	 		fileWriter.append(espace);
	 		fileWriter.append(String.valueOf(ligne.getNbSommetsVisitesAStar()));
	 		fileWriter.append(espace);
	 		fileWriter.append(String.valueOf(ligne.getNbIterAStar()));
			fileWriter.append(retour); // forcer le passage à la ligne
		}
		fileWriter.flush();
		fileWriter.close(); // fermer le fichier à la fin des traitements
		System.out.println("Ecriture effectuée");	
	} 
	catch (Exception e) {
		System.out.println(e.getMessage()) ;
	}
	
	}

}