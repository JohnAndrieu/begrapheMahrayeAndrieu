package org.insa.algo.shortestpath;
import java.util.*;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	protected int nbSommetsVisites;
	protected int nbSommets;
	
	public DijkstraAlgorithm(ShortestPathData data) {
		super(data);
		this.nbSommetsVisites = 0;
	}

	@Override
	protected ShortestPathSolution doRun() {
		boolean fin = false;
		ShortestPathData data = getInputData();
		Graph graph = data.getGraph();
		int sizeGraph = graph.size();
		List<Node> nodeGraph = new ArrayList <Node>();
		nodeGraph = graph.getNodes() ;
		
		ShortestPathSolution solution = null;

		/* HashMap de Labels */
		HashMap<Integer, Label> hmap = new HashMap<Integer, Label>(); 
		
		Iterator<Node> nodeIt = nodeGraph.iterator();
        while(nodeIt.hasNext()) {
        	Node nodeCurrent = nodeIt.next();
        	if(nodeCurrent.equals(data.getOrigin())) {} else {
        	hmap.put(nodeCurrent.getId(),new Label (nodeCurrent)) ;
        	}
        }

		/* Tas de Labels */
		BinaryHeap<Label> tas = new BinaryHeap<Label>();

		/* Tableau des prédecesseurs */
		Arc[] predecessorArcs = new Arc[sizeGraph];


		/* Ajout du sommet de départ */
		Label deb = new Label(data.getOrigin());
		hmap.put(deb.getNode().getId(), deb);
		tas.insert(deb);
		deb.setInTas();
		deb.setCost(0);

		/* Notifie les observateurs du premier évènement (départ de l'origine) */
		notifyOriginProcessed(data.getOrigin());

		/* Tant qu'il existe des sommets non marqués */
		while(!tas.isEmpty() && !fin){      	

			Label current= tas.deleteMin();
			/* On indique aux observateurs que le Node a été marqué */
			notifyNodeMarked(current.getNode());
			current.setMark();
			/* Quand on a atteint la destination, on s'arrête */
			if (current.getNode() == data.getDestination()) {
				fin = true;
			}
			/* Parcours des successeurs du sommet courant */
			Iterator<Arc> arc = current.getNode().getSuccessors().iterator();
			while (arc.hasNext()) {
				Arc arcIter = arc.next();

				// On vérifie que l'on peut réellement prendre cet arc
				if (!data.isAllowed(arcIter)) {
					continue;
				}

				Node successeur = arcIter.getDestination();

				/* On recupere le label correspondant au noeud dans le tableau de labels */
				Label successeurLabel = hmap.get(successeur.getId());

				/* Si le successeur n'est pas encore marqué */
				if (!successeurLabel.getMark()) {
					/* Si on obtient un meilleur coût */
					/* Alors on le met à jour */

					if(successeurLabel.getTotalCost() > (current.getCost()+data.getCost(arcIter)) ) {

						successeurLabel.setCost(current.getCost()+(float)data.getCost(arcIter));
						successeurLabel.setFather(arcIter); 
						/* Si le label est déjà dans le tas */
						/* Alors on met à jour sa position dans le tas */
						if(successeurLabel.getInTas()) {
							tas.remove(successeurLabel);
						}
						/* Sinon on l'ajoute dans le tas */
						else {
							successeurLabel.setInTas();
						}
						tas.insert(successeurLabel);
						predecessorArcs[arcIter.getDestination().getId()] = arcIter;
					}
				}

			}
		}

		// Destination has no predecessor, the solution is infeasible...
		if (predecessorArcs[data.getDestination().getId()] == null) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else {

			// The destination has been found, notify the observers.
			notifyDestinationReached(data.getDestination());

			// Create the path from the array of predecessors...
			ArrayList<Arc> arcs = new ArrayList<>();
			Arc arc = predecessorArcs[data.getDestination().getId()];

			while (arc != null) {
				arcs.add(arc);
				arc = predecessorArcs[arc.getOrigin().getId()];
			}

			// Reverse the path...
			Collections.reverse(arcs);

			// Create the final solution.
			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));

		}

		return solution;
	}
	
	/* Retourne le nombre de sommets visités */
	public int getNbSommetsVisites() {
		return this.nbSommetsVisites;
	}

}