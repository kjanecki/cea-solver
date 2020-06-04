package sample.solver

import sample.model.NumericIndividual
import kotlin.random.Random

class NumericOperator2 : Operator<NumericIndividual> {

    private val lowerLimit = -1.0
    private val upperLimit = 1.0

    override fun mutation(node: NumericIndividual): NumericIndividual {
        node.setNumericValue(Random.nextDouble(lowerLimit, upperLimit) + node.getNumericValue())
        return node
    }

    override fun fitness(node: NumericIndividual): Double{
        val x = node.getNumericValue()//x ~ 2.4, y ~ 40
        return ((-1) * Math.pow(x, 6.0)) + (10 * Math.pow(x, 4.0)) + Math.pow(x,3.0) - (20 * Math.pow(x, 2.0))
    }
}