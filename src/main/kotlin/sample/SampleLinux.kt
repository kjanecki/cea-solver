package sample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sample.generator.RandomNeighbourhoodSelector
import sample.generator.SimpleWorldNetworkGenerator
import sample.solver.CeaSolverImpl

fun main() {

    println("Start")

// Start a coroutine
    GlobalScope.launch {
        delay(1000)
        println("Hello")
    }

    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")


    val networkGenerator = SimpleWorldNetworkGenerator(RandomNeighbourhoodSelector());
    val network = networkGenerator.createNetwork()
    val ceaSolver = CeaSolverImpl(network)

    for (i in 1..100){
        ceaSolver.nextGeneration()
        print(ceaSolver.getBestMatchedNode())
    }

}