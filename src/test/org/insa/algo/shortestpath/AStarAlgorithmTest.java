package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.AbstractSolution.Status;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;

import org.insa.graph.Arc;
import org.insa.graph.Path;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.RoadInformation;

public class AStarAlgorithmTest {
	
	//Arc inspector
	List<ArcInspector> filters = ArcInspectorFactory.getAllFilters();

    // Small graph use for tests
    private static Graph graph;

    // List of nodes
    private static Node[] nodes;

    // List of arcs in the graph, a2b is the arc from node A (0) to B (1).
    @SuppressWarnings("unused")
    private static Arc a2b, a2c, a2e, b2c, c2d_1, c2d_2, c2d_3, c2a, d2a, d2e, e2d;

    // Some paths...
    @SuppressWarnings("unused")
    private static Path emptyPath, singleNodePath, shortPath, longPath, loopPath, longLoopPath,
            invalidPath;

    @BeforeClass
    public static void initAll() throws IOException {

        // 10 and 20 meters per seconds
        RoadInformation speed10 = new RoadInformation(RoadType.MOTORWAY, null, true, 36, ""),
                speed20 = new RoadInformation(RoadType.MOTORWAY, null, true, 72, "");

        // Create nodes
        nodes = new Node[6];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = new Node(i, null);
        }

        // Add arcs...
        a2b = Node.linkNodes(nodes[0], nodes[1], 10, speed10, null);
        a2c = Node.linkNodes(nodes[0], nodes[2], 15, speed10, null);
        a2e = Node.linkNodes(nodes[0], nodes[4], 15, speed20, null);
        b2c = Node.linkNodes(nodes[1], nodes[2], 10, speed10, null);
        c2d_1 = Node.linkNodes(nodes[2], nodes[3], 20, speed10, null);
        c2d_2 = Node.linkNodes(nodes[2], nodes[3], 10, speed10, null);
        c2d_3 = Node.linkNodes(nodes[2], nodes[3], 15, speed20, null);
        d2a = Node.linkNodes(nodes[3], nodes[0], 15, speed10, null);
        d2e = Node.linkNodes(nodes[3], nodes[4], 22.8f, speed20, null);
        e2d = Node.linkNodes(nodes[4], nodes[0], 10, speed10, null);

        graph = new Graph("ID", "", Arrays.asList(nodes), null);

        emptyPath = new Path(graph, new ArrayList<Arc>());
        singleNodePath = new Path(graph, nodes[1]);
        shortPath = new Path(graph, Arrays.asList(new Arc[] { a2b, b2c, c2d_1 }));
        longPath = new Path(graph, Arrays.asList(new Arc[] { a2b, b2c, c2d_1, d2e }));
        loopPath = new Path(graph, Arrays.asList(new Arc[] { a2b, b2c, c2d_1, d2a }));
        longLoopPath = new Path(graph,
                Arrays.asList(new Arc[] { a2b, b2c, c2d_1, d2a, a2c, c2d_3, d2a, a2b, b2c }));
        invalidPath = new Path(graph, Arrays.asList(new Arc[] { a2b, c2d_1, d2e }));

    }
	
	
	@Test
	public void testCheminNull () throws Exception {
		System.out.println("#### ----- Tests de validité ----- ####") ;
		System.out.println("---- Test chemin null ----") ;
		
		String map = "/home/jonathan/Documents/begrapheMahrayeAndrieu/toulouse.mapgr" ;
		
		AStarScenarioTest test = new AStarScenarioTest () ;
		
		int destination = 0 ; 
		int origine = 0;
		int mode = 0;
		
		test.testScenario(map, mode, origine, destination);
		
		System.out.println();
	}
	
	@Test
	public void testOracleMP_Time () throws Exception {	//Test sur carte Midi-Pyrénées 
		System.out.println("#### ----- Tests de validité ----- ####") ;
		System.out.println("---- Test avec Oracle => Time Midi-Pyrénées ----") ;
		
		String map = "/home/jonathan/Documents/begrapheMahrayeAndrieu/midi-pyrenees.mapgr" ;
		
		AStarScenarioTest test = new AStarScenarioTest () ;
		
		int destination = 103937 ; 
		int origine = 628548;
		int mode = 0;
		
		test.testScenario(map, mode, origine, destination);
		
		System.out.println();
	}
	
	@Test
	public void testOracleMP_Length () throws Exception {	//Test sur carte Midi-Pyrénées 
		System.out.println("#### ----- Tests de validité ----- ####") ;
		System.out.println("---- Test avec Oracle => Lenght Midi-Pyrénées ----") ;
		
		String map = "/home/jonathan/Documents/begrapheMahrayeAndrieu/midi-pyrenees.mapgr" ;
		
		AStarScenarioTest test = new AStarScenarioTest () ;
		
		int destination = 103937 ; 
		int origine = 628548;
		int mode = 1;
		
		test.testScenario(map, mode, origine, destination);
		
		System.out.println();
	}
	
	@Test
	public void testOracleTLS_Time () throws Exception {	//Test sur carte Midi-Pyrénées 
		System.out.println("#### ----- Tests de validité ----- ####") ;
		System.out.println("---- Test avec Oracle => Time Toulouse ----") ;
		
		String map = "/home/jonathan/Documents/begrapheMahrayeAndrieu/toulouse.mapgr" ;
		
		AStarScenarioTest test = new AStarScenarioTest () ;
		
		int destination = 15427 ; 
		int origine = 16938;
		int mode = 0;
		
		test.testScenario(map, mode, origine, destination);
		
		System.out.println();
	}
	
	@Test
	public void testOracleTLS_Length () throws Exception {	//Test sur carte Midi-Pyrénées 
		System.out.println("#### ----- Tests de validité ----- ####") ;
		System.out.println("---- Test avec Oracle => Lenght Toulouse ----") ;
		
		String map = "/home/jonathan/Documents/begrapheMahrayeAndrieu/toulouse.mapgr" ;
		
		AStarScenarioTest test = new AStarScenarioTest () ;
		
		int destination = 15427 ; 
		int origine = 16938;
		int mode = 1;
		
		test.testScenario(map, mode, origine, destination);
		
		System.out.println();
	}
	
	@Test
	public void testOracleGUAD_Impossible () throws Exception {	//Test sur carte Midi-Pyrénées 
		System.out.println("#### ----- Tests de validité ----- ####") ;
		System.out.println("---- Test avec Oracle => Impossible (non connexe) ----") ;
		
		String map = "/home/jonathan/Documents/begrapheMahrayeAndrieu/guadeloupe.mapgr" ;
		
		AStarScenarioTest test = new AStarScenarioTest () ;
		
		int destination = 15308 ; 
		int origine = 13673;
		int mode = 0;
		
		test.testScenario(map, mode, origine, destination);
		
		System.out.println();
	}
	

}