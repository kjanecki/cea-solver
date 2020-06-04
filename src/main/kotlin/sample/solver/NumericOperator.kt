package sample.solver

import sample.model.NumericIndividual
import kotlin.random.Random

class NumericOperator : Operator<NumericIndividual> {

    private val lowerLimit = 0.5
    private val upperLimit = 1.5

    override fun mutation(node: NumericIndividual): NumericIndividual {
        node.setNumericValue(Random.nextDouble(lowerLimit, upperLimit) * node.getNumericValue())
        return node
    }

    override fun fitness(node: NumericIndividual): Double{
        val x = node.getNumericValue()//x ~ 3.6, y ~ 138
        return ((-1) * Math.pow(x, 4.0)) + Math.pow(x, 3.0) + (20 * Math.pow(x, 2.0))
    }
}