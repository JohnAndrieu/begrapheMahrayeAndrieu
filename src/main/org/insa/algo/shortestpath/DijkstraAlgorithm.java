package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.Label;
import org.insa.graph.Graph;
import org.insa.graph.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        
        Graph graph = data.getGraph();

        final int nbNodes = graph.size();
        
        boolean test_mark = true ;
        
        //on récupère les nodes du graph
        List<Node> nodes = new ArrayList <Node> ();
        nodes = graph.getNodes() ;
        
        //on crée une hashmap
        HashMap<Integer, Label> hmap = new HashMap<Integer, Label>(); 
        
        //mettre un label à tous les noeuds
        for(int i = 0 ; i < nbNodes ; i++) {
        	hmap.put(i,new Label (nodes.get(i),null,false,Double.POSITIVE_INFINITY)) ;
        }
        
        //on met l'origine à 0
        Node origin = data.getOrigin() ;
        Label OriginLabel = new Label (origin,null,false,0);
        
        //on crée un tas de label
        BinaryHeap<Label> LabelHeap = new BinaryHeap <Label> () ;

        //on insere l'origine dans le tas
        LabelHeap.insert(OriginLabel);
        
        while(test_mark == true) {	
        	
        	
        	Label minLabel = LabelHeap.findMin() ;	//on a le min des label
        	minLabel.setMark(true); 
        	
        	//on vérifie s'il y a des sommets non marqués
        	for(int i = 0; i < nbNodes ; i++) {
        		if(hmap.get(i).getMark() == false) {
        			test_mark = true ;
        		}
        		else {
        			test_mark = false ;
        		}
        	}
        }
        
        return solution;
    }

}
