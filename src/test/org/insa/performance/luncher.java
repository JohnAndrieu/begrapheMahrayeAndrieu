package org.insa.performance;

import org.junit.Test;

public class luncher {
	
	@Test
	public void testsPerformance() {
		
		String fichierLecture = "/home/jonathan/Documents/begrapheMahrayeAndrieu/"
				+ "src/test/org/insa/midi-pyrenees_temps_100_data.csv";
		String fichierEcriture = "/home/jonathan/Documents/begrapheMahrayeAndrieu/"
				+ "src/test/org/insa/res_midi-pyrenees_temps_100_data.csv";
		
		testPerformance test1Temps = new testPerformance();
		test1Temps.run(fichierLecture, fichierEcriture, 0);
		
		testPerformance test1Distance = new testPerformance();
		test1Distance.run(fichierLecture, fichierEcriture, 1);
		
	}

}