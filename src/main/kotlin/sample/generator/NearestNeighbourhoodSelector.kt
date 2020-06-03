package sample.generator

import sample.model.NumericIndividual
import sample.model.NumericNeighbourhood

class NearestNeighbourhoodSelector : NeighbourhoodSelector<NumericIndividual> {

    override fun createNeighbourhood(edgeMatrix : Array<Array<Int>>,
                                     nodes: Map<Int, NumericIndividual>,
                                     node: NumericIndividual): NumericNeighbourhood {
        var individuals = emptyList<NumericIndividual>().toMutableList();
        for (i in 0 until edgeMatrix.size) {
            if(edgeMatrix[node.getId()][i] != 0) {
                individuals.add(nodes[i] ?: error("Node not found"))
            }
        }
        val neighbourhood = NumericNeighbourhood()
        neighbourhood.individuals.addAll(individuals)
        return neighbourhood
    }

}