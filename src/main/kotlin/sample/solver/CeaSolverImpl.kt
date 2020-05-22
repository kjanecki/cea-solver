package sample.solver

import sample.model.Network
import sample.model.NumericIndividual
import kotlin.random.Random

class CeaSolverImpl(val network: Network<NumericIndividual>) : CeaSolver<NumericIndividual> {

    private var neighbourhoodOperator : NeighbourhoodOperator<NumericIndividual> = SumOperator()
    private var operator : Operator<NumericIndividual> = Mutation()
    private var bestNode : NumericIndividual = network.getNode(0) ?: error("Empty node list")
    var bestValue : AbstractResult = NumericResult(Double.MIN_VALUE)

    override fun nextGeneration() {
        network.getNodes().forEach { node ->
            val result = neighbourhoodOperator.execute(network.getNeighbourhood(node.getId()) ?: error(""))
            if(result.compareTo(bestValue) > 0){
                bestNode = node
                bestValue = result
            }
        }
        network.getNodes().forEach { node ->
            if(Random.nextDouble() < 0.25) {
                operator.execute(node)
            }
        }
    }

    override fun getCurrentState(): Network<NumericIndividual> {
        return network
    }

    override fun getBestMatchedNode(): NumericIndividual {
        return bestNode
    }

    override fun getBestMatchedNodes(count: Int): List<NumericIndividual> {
        return listOf(bestNode)
    }

    fun getCurrentValue() : Double {
        return (bestValue as NumericResult).result
    }
}