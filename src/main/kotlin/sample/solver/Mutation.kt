package sample.solver

import sample.model.NumericIndividual
import kotlin.random.Random

class Mutation : Operator<NumericIndividual> {

    override fun execute(node: NumericIndividual): NumericIndividual {
        node.value = Random.nextDouble(0.5, 1.5) * node.value
        return node
    }
}