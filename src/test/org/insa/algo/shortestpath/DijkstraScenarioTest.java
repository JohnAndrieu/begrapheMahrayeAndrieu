package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

import org.junit.Test;

public class DijkstraScenarioTest {
	
	
	@Test
	// modeEvaluation : 0 = temps, 1 = distance
	public void testScenario(String mapName, int modeEvaluation, int origine, int destination) throws Exception {

		// Create a graph reader.
		GraphReader reader = new BinaryGraphReader(
				new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

		// Read the graph.
		Graph graph = reader.read();
		
		ArcInspector arcInspectorDijkstra;
		
		if (modeEvaluation == 0) { //Temps
			System.out.println("Mode => Temps");
			arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);
		} else { //Distance
			System.out.println("Mode => Distance");
			arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);
		}
		
		System.out.println("Origine => " + origine);
		System.out.println("Destination => " + destination);
		
		if(origine==destination) {
			System.out.println("Origine et Destination identiques");
			System.out.println("Cout solution = 0");
			
		} else {
			
			ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);

			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			
			// Recuperation des solutions de Bellman et Dijkstra pour comparer 
			System.out.println("Test : Run Dijkstra");
			ShortestPathSolution solD = D.run();
			System.out.println("Test : Run Bellman Ford");
			ShortestPathSolution solB = B.run();
			
			if (solD.getPath() == null) {
				assertEquals(solB.getPath(), solD.getPath());
				System.out.println("Pas de chemins trouvé");
			}
			// Un plus court chemin trouve 
			else {
				
				double coutD;	//cout selon Dijkstra
				double coutB;	//cout selon Bellman
				
				if(modeEvaluation == 0) { //Temps
					//Calcul du cout de la solution 
					coutD = solD.getPath().getMinimumTravelTime();
					coutB = solB.getPath().getMinimumTravelTime();
				} else { //Distance
					coutD = solD.getPath().getLength();
					coutB = solB.getPath().getLength();
				}
				
				assertEquals(coutB, coutD, 0.001); //Comparaison
				System.out.println("Cout solution: " + coutD);
				
			}
			
		}
		
	}
	
		
}
