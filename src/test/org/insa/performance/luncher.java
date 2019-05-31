package org.insa.performance;

import org.junit.Test;

public class luncher {
	
	@Test
	public void testsPerformance() {
		
		String fichierLecture1 = "/home/jonathan/Documents/begrapheMahrayeAndrieu/"
				+ "src/test/org/insa/midi-pyrenees_100_data_temps.csv";
		String fichierEcriture1 = "res_midi-pyrenees_temps_100_data.csv";
		
		String fichierLecture2 = "/home/jonathan/Documents/begrapheMahrayeAndrieu/"
				+ "src/test/org/insa/midi-pyrenees_100_data_distance.csv";
		String fichierEcriture2 = "res_midi-pyrenees_distance_100_data.csv";
		
		testPerformance test1Temps = new testPerformance();
		test1Temps.run(fichierLecture1, fichierEcriture1);
		
		testPerformance test1Distance = new testPerformance();
		test1Distance.run(fichierLecture2, fichierEcriture2);
		
	}

}