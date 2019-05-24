package org.insa.algo;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class DijkstraTestScenario {

	public void testScenario(String nomMap, int type, int origine, int destination) throws Exception {
		   
   	 String mapName = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
   	
   	// Graph reader
   	GraphReader reader = new BinaryGraphReader(
               new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

		// Read the graph.
		Graph graph = reader.read();
	    		
		ArcInspector arcInspectorDijkstra ; 
		
		if(type == 0) {
			System.out.println("Mode choisi => Temps");
			arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);
		} else {
			System.out.println("Mode choisi => Distance");
			arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);
		}
		
		System.out.println("Origine choisi => " + origine);
		System.out.println("Destination choisi => " + destination);
		
		if(origine==destination) {
			System.out.println("Origine et Destination identiques");
			System.out.println("Cout solution: 0");
			
		} else {
		
	    ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);
	    		
	    DijkstraAlgorithm myDijkstra = new DijkstraAlgorithm(data);
	    
	    // On récupère la solution de l'algorithme
	    ShortestPathSolution solution = myDijkstra.run() ;
	    		
	    assertEquals(true, solution.getPath().isValid());
	   
		}
  
   }
	
}
