package sample

import kotlinx.coroutines.runBlocking
import sample.generator.RandomNeighbourhoodSelector
import sample.generator.SimpleWorldNetworkGenerator
import sample.solver.CeaSolverImpl

fun main() {

    val networkGenerator = SimpleWorldNetworkGenerator(RandomNeighbourhoodSelector(), 0.2, 16);
    val network = networkGenerator.createNetwork()
    val ceaSolver = CeaSolverImpl(network)

    for (i in 1..100){
        runBlocking { ceaSolver.nextGeneration() }
        println("Generation $i: node ${ceaSolver.getBestMatchedNode()}, value ${ceaSolver.getCurrentValue()}")
    }
}