package sample.solver

import sample.model.Individual
import sample.model.Neighbourhood

interface NeighbourhoodOperator<T : Individual> {

    fun execute(neighbourhood: Neighbourhood<T>) : AbstractResult

}