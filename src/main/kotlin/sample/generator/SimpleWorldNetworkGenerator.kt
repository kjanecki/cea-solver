package sample.generator

import sample.model.Network
import sample.model.NumericIndividual
import sample.model.NumericNetwork
import kotlin.random.Random

class SimpleWorldNetworkGenerator(
    private val neighbourhoodSelector: NeighbourhoodSelector<NumericIndividual>,
    private val p : Double,
    private val k : Int) : NetworkGenerator<NumericIndividual> {

    override fun createNetwork(): Network<NumericIndividual> {
        val nodesNumber = Random.nextInt(1000, 2000);
        var nodeId = 0;
        val nodes = generateSequence { NumericIndividual(nodeId++, Random.nextDouble()) }
            .take(nodesNumber).toList()
        val nodesMap = nodes.map { node -> node.getId() to node }.toMap()
        var edgeMatrix = constructEdgeMatrix(nodesNumber)
        val neighbourhoods = nodes
            .map { node -> node.getId() to neighbourhoodSelector.createNeighbourhood(edgeMatrix, nodesMap, node) }.toMap()
        return NumericNetwork(nodesMap, edgeMatrix , neighbourhoods)
    }

    private fun constructEdgeMatrix(n : Int) : Array<Array<Int>> {
        var matrix = constructRingLattice(n)
        val dim = matrix.size-1
        for (i in 0..dim) {
            for (i2 in i+1..i+k) {
                var j = i2
                if (j > dim) {
                    j -= matrix.size
                }
                if (Random.nextDouble() < p) {
                    var piv : Int
                    do {
                        piv = Random.nextInt(0, dim)
                    } while (piv == i || matrix[i][piv] != 0 || piv == j)
                    val value = matrix[i][j]
                    matrix[i][j] = 0
                    matrix[j][i] = 0
                    matrix[i][piv] = value
                    matrix[piv][i] = value
                }
            }
        }
        return matrix;
    }

    private fun constructRingLattice(n : Int) : Array<Array<Int>> {
        var matrix = zeros(n);
        val dim = matrix.size-1
        for (i in 0..dim) {
            val j = i;
            for (pivot in 1..k) {
                var left = j - pivot
                if(left < 0) {
                    left += matrix.size
                }
                var right = j + pivot
                if(right > dim) {
                    right -= matrix.size
                }
                matrix[i][left] = 1
                matrix[i][right] = 1
            }
        }
        return matrix;
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

    private fun printMatrix(matrix: Array<Array<Int>>) {
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix.size) {
                print(matrix[i][j])
                print(" ")
            }
            println()
        }
    }
}