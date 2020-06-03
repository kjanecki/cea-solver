package sample.generator

import sample.model.NumericIndividual
import sample.model.NumericNeighbourhood
import java.util.*

class DistanceThresholdNeighbourhoodSelector(private val threshold: Int) : NeighbourhoodSelector<NumericIndividual> {

    override fun createNeighbourhood(edgeMatrix : Array<Array<Int>>,
                                     nodes: Map<Int, NumericIndividual>,
                                     node: NumericIndividual): NumericNeighbourhood {
        var set = emptySet<Int>().toMutableSet();
        val queue : Queue<Int> = LinkedList()
        queue.add(node.getId())
        var i = 0;
        while(i <= threshold) {
            val neighbours = emptyList<Int>().toMutableList()
            while (!queue.isEmpty()) {
                val nodeId = queue.remove()
                if(!set.contains(nodeId)) {
                    set.add(nodeId);
                    for (j in 0 until edgeMatrix.size) {
                        if(edgeMatrix[nodeId][j] != 0) {
                            neighbours.add(j)
                        }
                    }
                }
            }
            queue.addAll(neighbours)
            i++
        }
        val neighbourhood = NumericNeighbourhood()
        neighbourhood.individuals.addAll(set.map { nodeId -> nodes[nodeId] ?: error("Node nod exists: $nodeId") })
        return neighbourhood
    }

}