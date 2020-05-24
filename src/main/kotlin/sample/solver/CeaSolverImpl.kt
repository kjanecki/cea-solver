package sample.solver

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import sample.model.Network
import sample.model.NumericIndividual
import kotlin.random.Random

class CeaSolverImpl(val network: Network<NumericIndividual>) : CeaSolver<NumericIndividual> {

    private var neighbourhoodOperator : NeighbourhoodOperator<NumericIndividual> = SumOperator()
    private var mutation : Operator<NumericIndividual> = Mutation()
    private var bestNode : NumericIndividual = network.getNode(0) ?: error("Empty node list")
    var bestValue : AbstractResult = NumericResult(Double.MIN_VALUE)
    private val mutex = Mutex()

    override suspend fun nextGeneration() {
        val jobs = network.getNodes().map { node ->
            GlobalScope.async {
                computeNeighbourhoodOperation(node)
            }
        }
        jobs.forEach { job -> job.await() }
        network.getNodes().forEach { node ->
            if(Random.nextDouble() < 0.25) {
                mutation.execute(node)
            }
        }
    }

    private suspend fun computeNeighbourhoodOperation(node : NumericIndividual) {
        val result = neighbourhoodOperator.execute(network.getNeighbourhood(node.getId()) ?: error("ERROR"))
        mutex.withLock {
            if(result.compareTo(bestValue) > 0){
                bestNode = node
                bestValue = result
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