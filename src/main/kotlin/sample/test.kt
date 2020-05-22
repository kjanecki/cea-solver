package sample

import kotlin.random.Random

fun main() {
    val matrix = constructEdgeMatrix(10, 2, 0.2);
    val dim = matrix.size - 1
    for (i in 0..dim) {
        for (j in 0..dim) {
            print(matrix[i][j])
            print(" ")
        }
        println()
    }
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

private fun constructRingLattice(n : Int, k : Int) : Array<Array<Int>> {
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

private fun constructEdgeMatrix(n : Int, k : Int, p : Double) : Array<Array<Int>> {
    var matrix = constructRingLattice(n, k)
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
                } while (piv == i || matrix[i][piv] == 1 || piv == j)

                matrix[i][j] = 0
                matrix[j][i] = 0
                matrix[i][piv] = 1
                matrix[piv][i] = 1
            }
        }
    }
    return matrix;
}