package sample.solver

import javafx.beans.binding.DoubleExpression
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import sample.model.Network
import sample.model.NumericIndividual
import kotlin.random.Random

class CeaSolverImpl(network: Network<NumericIndividual>,
                    neighbourhoodOperator: NeighbourhoodOperator<NumericIndividual>,
                    operator: Operator<NumericIndividual>)
    : CeaSolver<NumericIndividual>(network, neighbourhoodOperator, operator) {

    private var bestNode : NumericIndividual = network.getNode(0) ?: error("Empty node list")
    private var bestArg : Double = network.getNode(0)?.getNumericValue() ?: error("Empty node list")
    var bestValue : Double = (Double.MIN_VALUE)
    private val mutex = Mutex()

    private val mutationChance = 0.25

    override suspend fun nextGeneration() {
        bestNode = network.getNode(0) ?: error("Empty node list")
        bestValue = Double.MIN_VALUE

        val jobs = network.getNodes().map { node ->
            GlobalScope.async {
                evolveIfNeeded(node)
            }
        }
        jobs.forEach { job -> job.await() }
        network.getNodes().forEach { node ->
            if(Random.nextDouble() < mutationChance) {
                operator.mutation(node)
            }
        }
    }

    private suspend fun evolveIfNeeded(node : NumericIndividual) {
        var result : Double
        var arg : Double
        if(network.getNeighbourhood(node.getId())!!.getNodes().isEmpty()){
            arg = node.getNumericValue()
            result = operator.fitness(node)
        }
        else {
            result = neighbourhoodOperator.neighbourhoodFitness(network.getNeighbourhood(node.getId()) ?: error("ERROR"),operator)

            var mean = neighbourhoodOperator.neighbourhoodMean(network.getNeighbourhood((node.getId())) ?: error("ERROR"))
            arg = (mean as NumericResult).result
            node.setNumericValue(arg)
        }
        mutex.withLock {
            if(result.compareTo(bestValue) > 0){
                bestNode = node
                bestArg = arg
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

    override fun getCurrentArg() : Double {
        return bestArg
    }

    override fun getCurrentValue() : Double {
        return bestValue
    }
}