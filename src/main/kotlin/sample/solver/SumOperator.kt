package sample.solver

import sample.model.Neighbourhood
import sample.model.NumericIndividual
import java.lang.Math.pow

class SumOperator : NeighbourhoodOperator<NumericIndividual> {

    override fun execute(neighbourhood: Neighbourhood<NumericIndividual>): AbstractResult {
        return NumericResult(neighbourhood.getNodes()
            .map { node -> fitness(node.getNumericValue())}
            .sum())
    }

    private fun fitness(x : Double) : Double {
        return ((-1) * pow(x, 4.0)) + pow(x, 3.0) + (20 * pow(x, 2.0))
    }

}