package sample

import kotlinx.coroutines.runBlocking
import sample.config.SimpleWorldRandomNeighbourhoodConfig
import sample.generator.DistanceThresholdNeighbourhoodSelector
import sample.generator.RandomNeighbourhoodSelector
import sample.generator.SimpleWorldNetworkGenerator
import sample.model.NumericIndividual
import sample.solver.CeaSolverImpl
import java.io.FileWriter
import java.io.IOException

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

    val data = mutableListOf<String>()
    for (i in 1..100){
        runBlocking { ceaSolver.nextGeneration() }
        val bestNode = ceaSolver.getBestMatchedNode()
        val bestValue = ceaSolver.getCurrentValue()
        println("Generation $i: node $bestNode, value $bestValue")
        data.add(bestValue.toString())
        if(bestValue.compareTo(everBestValue) > 0) {
            everBestIteration = i
            everBestNode = bestNode
            everBestValue = bestValue
        }
    }
    println()
    println("Best value in generation $everBestIteration: node $everBestNode, value $everBestValue")

    writeResultsToCsv(data)

}

private fun writeResultsToCsv(data: MutableList<String>) {
    var fileWriter: FileWriter? = null

    try {
        fileWriter = FileWriter("test.csv")
        fileWriter.append("generation_id,value\n");
        for ((i, value) in data.withIndex()) {
            fileWriter.append("${i+1},$value\n");
        }

    } catch (e: Exception) {
        println("Writing CSV error!")
        e.printStackTrace()
    } finally {
        try {
            fileWriter!!.flush()
            fileWriter.close()
        } catch (e: IOException) {
            println("Flushing/closing error!")
            e.printStackTrace()
        }
    }
}