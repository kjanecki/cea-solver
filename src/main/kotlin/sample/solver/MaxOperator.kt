package sample.solver

import sample.model.Neighbourhood
import sample.model.NumericIndividual

class MaxOperator : NeighbourhoodOperator<NumericIndividual> {

    override fun execute(neighbourhood: Neighbourhood<NumericIndividual>): AbstractResult {
        return NumericResult(neighbourhood.getNodes().map { node -> node.getNumericValue()}.max()
            ?: throw NullPointerException("Expression 'max' must not be null"))
    }


}