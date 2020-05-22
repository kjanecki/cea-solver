package sample.solver

import sample.model.NumericIndividual
import kotlin.random.Random

class Mutation : Operator<NumericIndividual> {
    override fun execute(node: NumericIndividual): NumericIndividual {
        return NumericIndividual(node.getId(), Random.nextDouble() * node.value)
    }
}