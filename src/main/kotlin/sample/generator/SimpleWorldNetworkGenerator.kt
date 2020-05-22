package sample.generator

import sample.model.Edge
import sample.model.Network
import sample.model.NumericIndividual
import sample.model.NumericNetwork
import kotlin.random.Random

class SimpleWorldNetworkGenerator(val neighbourhoodSelector: NeighbourhoodSelector<NumericIndividual>) : NetworkGenerator<NumericIndividual> {

    override fun createNetwork(): Network<NumericIndividual> {
        val nodesNumber = Random.nextInt(100, 200);
        var nodeId = 1;
        val nodes = generateSequence { NumericIndividual(nodeId++, Random.nextDouble()) }
            .take(nodesNumber).toList()
        val nodesMap = nodes.map { node -> node.getId() to node }.toMap()
        val edgeSet = emptySet<Pair<Int, Int>>().toMutableSet()
        for (i in 0..nodesNumber/2) {
            val r1 = Random.nextInt(1, nodesNumber)
            val r2 = Random.nextInt(1, nodesNumber)
            if(r1 != r2){
                val pair = Pair(r1, r2)
                if(!edgeSet.contains(pair)){
                    edgeSet.add(pair);
                }
            }
        }
        val edges = edgeSet.map { pair ->
            Edge(nodesMap[pair.first] ?: error(""), nodesMap[pair.second] ?: error(""), false, Random.nextDouble())}
            .toList()
        val neighbourhoods = nodes
            .map { node -> node.getId() to neighbourhoodSelector.createNeighbourhood(edges, node) }.toMap()
        return NumericNetwork(nodesMap, edges, neighbourhoods)
    }
}