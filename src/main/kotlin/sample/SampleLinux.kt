package sample

import kotlinx.coroutines.runBlocking
import sample.config.SimpleWorldRandomNeighbourhoodConfig
import sample.generator.DistanceThresholdNeighbourhoodSelector
import sample.generator.RandomNeighbourhoodSelector
import sample.generator.SimpleWorldNetworkGenerator
import sample.model.NumericIndividual
import sample.solver.CeaSolverImpl

fun main() {

    val config = SimpleWorldRandomNeighbourhoodConfig()

    val operator = config.getOperator()
    val neighbourhoodOperator = config.getNeighbourhoodOperator()
    val neighbourhoodSelector = config.getNeighbourhoodSelector()
    val networkGenerator = config.getNetworkGenerator(neighbourhoodSelector)

    val network = networkGenerator.createNetwork()
    val ceaSolver = config.getSolver(network, neighbourhoodOperator, operator)

    var everBestIteration = 0
    var everBestNode : NumericIndividual = network.getNode(0) ?: error("Empty node list")
    var everBestValue : Double = Double.MIN_VALUE

    for (i in 1..100){
        runBlocking { ceaSolver.nextGeneration() }
        val bestNode = ceaSolver.getBestMatchedNode()
        val bestValue = ceaSolver.getCurrentValue()
        println("Generation $i: node $bestNode, value $bestValue")

        if(bestValue.compareTo(everBestValue) > 0) {
            everBestIteration = i
            everBestNode = bestNode
            everBestValue = bestValue
        }
    }
    println()
    println("Best value in generation $everBestIteration: node $everBestNode, value $everBestValue")
}