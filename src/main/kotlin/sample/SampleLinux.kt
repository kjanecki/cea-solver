package sample

import kotlinx.coroutines.runBlocking
import sample.generator.RandomNeighbourhoodSelector
import sample.generator.SimpleWorldNetworkGenerator
import sample.model.NumericIndividual
import sample.solver.CeaSolverImpl

fun main() {

    val networkGenerator = SimpleWorldNetworkGenerator(RandomNeighbourhoodSelector(), 0.2, 16);
    val network = networkGenerator.createNetwork()
    val ceaSolver = CeaSolverImpl(network)

    //najlepszy wynik kiedykolwiek, w której iteracji i dla jakiego node'a
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