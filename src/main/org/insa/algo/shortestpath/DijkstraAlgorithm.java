package org.insa.algo.shortestpath;

import java.util.ArrayList;
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
        
        //on récupère les nodes du graph
        List<Node> nodes = new ArrayList <Node> ();
        nodes = graph.getNodes() ;
        
        //mettre un label à tous les noeuds
        for(int i = 0 ; i < nbNodes ; i++) {
        	new Label (nodes.get(i),null,false,Double.POSITIVE_INFINITY) ;
        }
        
        //on met l'origine à 0
        Node origin = data.getOrigin() ;
        new Label (origin,null,false,0);
        
        //on crée un tas de label
        BinaryHeap<Label> LabelHeap = new BinaryHeap <Label> () ;

        
        
        
        return solution;
    }

}
