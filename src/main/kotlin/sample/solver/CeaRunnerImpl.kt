package sample.solver

import kotlinx.coroutines.runBlocking
import sample.config.AppConfig
import sample.model.NumericIndividual
import java.io.FileWriter
import java.io.IOException

class CeaRunnerImpl(config: AppConfig) : CeaRunner(config) {

    override fun run(generations: Int) {
        val operator = config.getOperator()
        val neighbourhoodOperator = config.getNeighbourhoodOperator()
        val neighbourhoodSelector = config.getNeighbourhoodSelector()
        val networkGenerator = config.getNetworkGenerator(neighbourhoodSelector)

        val network = networkGenerator.createNetwork()
        val ceaSolver = config.getSolver(network, neighbourhoodOperator, operator)

        var everBestIteration = 0
        var everBestNode : NumericIndividual = network.getNode(0) ?: error("Empty node list")
        var everBestArg : Double = Double.MIN_VALUE
        var everBestValue : Double = Double.MIN_VALUE

        val dataArg = mutableListOf<String>()
        val dataValue = mutableListOf<String>()
        for (i in 1..generations){
            runBlocking { ceaSolver.nextGeneration() }
            val bestNode = ceaSolver.getBestMatchedNode()
            val bestArg = ceaSolver.getCurrentArg()
            val bestValue = ceaSolver.getCurrentValue()
            println("Generation $i: node $bestNode, value $bestValue")
            dataArg.add(bestArg.toString())
            dataValue.add(bestValue.toString())
            if(bestValue.compareTo(everBestValue) > 0) {
                everBestIteration = i
                everBestNode = bestNode
                everBestArg = bestArg
                everBestValue = bestValue
            }
        }
        println()
        println("Best value in generation $everBestIteration: for argument $everBestArg, value $everBestValue")

        writeResultsToCsv(dataArg,dataValue)

    }

    override fun writeResultsToCsv(dataArg: MutableList<String>, dataValue: MutableList<String>) {
        var fileWriter: FileWriter? = null

        try {
            fileWriter = FileWriter("test.csv")
            fileWriter.append("generation_id,arg,value\n");
            for ((i, value) in dataValue.withIndex()) {
                fileWriter.append("${i+1},${dataArg[i]},$value\n");
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
}