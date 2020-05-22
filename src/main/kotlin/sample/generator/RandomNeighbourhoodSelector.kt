package sample.generator

import sample.model.Edge
import sample.model.NumericIndividual
import sample.model.NumericNeighbourhood
import kotlin.random.Random

class RandomNeighbourhoodSelector : NeighbourhoodSelector<NumericIndividual> {

    override fun createNeighbourhood(edgeMatrix : Array<Array<Int>>,
                                     nodes: Map<Int, NumericIndividual>,
                                     node: NumericIndividual): NumericNeighbourhood {
        var individuals = emptyList<NumericIndividual>().toMutableList();
        for (i in 0 until edgeMatrix.size) {
            if(edgeMatrix[node.getId()][i] != 0) {
                individuals.add(nodes[i] ?: error("Node not found"))
            }
        }
        val shuffled = individuals.shuffled()
        val neighbourhood = NumericNeighbourhood()
        if (shuffled.isNotEmpty()) {
            val random = Random.nextInt(0, shuffled.size)
            neighbourhood.individuals.addAll(shuffled.take(random))
        }
        return neighbourhood
    }

}