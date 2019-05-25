package org.insa.algo.shortestpath;

import org.insa.algo.utils.Label;
import org.insa.algo.utils.LabelStar;
import org.insa.graph.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    /*
     * redifinition de Newllb: fonction permettant de
     * créer un nouveau label. Changement: On créer un labelStar
     * au lieu de label classique.
     */
    protected Label Newlb(Node node, ShortestPathData data){
    	LabelStar newlb;
    	newlb= new LabelStar(node,data);
    	return newlb;
    }

}
