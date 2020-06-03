package sample.generator

import sample.model.Network
import sample.model.NumericIndividual
import sample.model.NumericNetwork
import kotlin.random.Random

class ScaleFreeNetworkGenerator(
    private val neighbourhoodSelector: NeighbourhoodSelector<NumericIndividual>,
    private val m : Int,
    private val k : Int) : NetworkGenerator<NumericIndividual> {

    private val minNodeCount = 1000
    private val maxNodeCount = 2000
    private val minStartValue = 0.0
    private val maxStartValue = 1.0

    override fun createNetwork(): Network<NumericIndividual> {
        val nodesNumber = Random.nextInt(minNodeCount, maxNodeCount);
        var nodeId = 0;
        val nodes = generateSequence { NumericIndividual(nodeId++, Random.nextDouble(minStartValue,maxStartValue)) }
            .take(nodesNumber).toList()
        val nodesMap = nodes.map { node -> node.getId() to node }.toMap()
        var edgeMatrix = constructEdgeMatrix(nodesNumber, m)
        val neighbourhoods = nodes
            .map { node -> node.getId() to neighbourhoodSelector.createNeighbourhood(edgeMatrix, nodesMap, node) }.toMap()
        return NumericNetwork(nodesMap, edgeMatrix , neighbourhoods)
    }

    private fun constructEdgeMatrix(n : Int, m : Int) : Array<Array<Int>> {
        var matrix = zeros(n);
        var totalDegree = 0
        var degreeMap : MutableMap<Int,Int> = mutableMapOf()
        for (i in 0 until m) {
            for (j in 0 until m) {
                if(i != j) {
                    createEdge(matrix, i, j)
                    updateDegree(degreeMap, i)
                    updateDegree(degreeMap, j)
                    totalDegree += 2
                }
            }
        }

        val dim = matrix.size-1
        for (i in m..dim) {
            for (j in 0 until i) {
                val k = degreeMap[i]!!
                val p = k / totalDegree
                if (Random.nextDouble() < p) {
                    createEdge(matrix, i, j)
                    updateDegree(degreeMap, i)
                    updateDegree(degreeMap, j)
                    totalDegree += 2
                }
            }
        }
        return matrix;
    }

    private fun updateDegree(degreeMap: MutableMap<Int, Int>, i: Int) {
        if (!degreeMap.containsKey(i)) {
            degreeMap[i] = 0
        }
        degreeMap[i] = degreeMap[i]!! + 1
    }

    private fun createEdge(matrix: Array<Array<Int>>, i: Int, j: Int) {
        matrix[i][j] = 1
        matrix[j][i] = 1
    }

    private fun zeros(dim: Int) : Array<Array<Int>> {
        var matrix = arrayOf<Array<Int>>()
        for (i in 0..dim-1) {
            var array = arrayOf<Int>()
            for (j in 0..dim-1) {
                array += 0
            }
            matrix += array
        }
        return matrix
    }

}