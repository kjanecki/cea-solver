package sample.solver

import sample.model.Individual
import sample.model.Network
import sample.model.NumericIndividual

abstract class CeaSolver<T : Individual>(protected val network: Network<NumericIndividual>,
                                         protected var neighbourhoodOperator: NeighbourhoodOperator<NumericIndividual>,
                                         protected var operator: Operator<NumericIndividual>
) {

    abstract suspend fun nextGeneration()

    abstract fun getCurrentState() : Network<T>

    abstract fun getBestMatchedNode() : T

    abstract fun getBestMatchedNodes(count : Int) : List<T>

    abstract fun getCurrentValue() : Double
}