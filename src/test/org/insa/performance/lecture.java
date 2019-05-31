package org.insa.performance;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/* Lecture du fichier d'initialisation et initialisation des listes 
 * des sommets d'origines et de destinations */

public class lecture {
	private String mapName ;
	private int mode;
	private int nbChemin ;
	private ArrayList<Integer> listeOrigine;
	private ArrayList<Integer> listeDestination;

	public lecture(String fileName) {
		this.listeOrigine = new ArrayList<Integer>();
		this.listeDestination = new ArrayList<Integer>();
		this.LectureFichier(fileName);
	}

	public void LectureFichier (String fileName) {
		try {
			Scanner scan = new Scanner(new File(fileName));
			int origine;
			int destination;
			
			/* Recupere le nom de la carte */
			if (scan.hasNext()) {
				mapName = scan.nextLine();
				System.out.println("Carte => " + mapName);
			}
			
			if (scan.hasNext()) {
				mode = scan.nextInt();
				System.out.println("Mode => " + mode);
			}
			
			if (scan.hasNext()) {
				nbChemin = scan.nextInt();
				System.out.println("Nombre chemin => " + nbChemin);
			}
			
			/* Recupere les origines et destinations */
			while (scan.hasNextInt()) {
				origine = scan.nextInt();
				this.listeOrigine.add(origine);
				scan.hasNextInt();
				destination = scan.nextInt();
				this.listeDestination.add(destination);
			}
			scan.close();
		}
		catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}

	public String getMapName() {
		return this.mapName;
	}
	
	public int getMode() {
		return this.mode;
	}
	
	public int getnbChemin() {
		return this.nbChemin;
	}

	public ArrayList<Integer> getListeOrigine(){
		return this.listeOrigine;
	}

	public ArrayList<Integer> getListeDestination(){
		return this.listeDestination;
	}
}
