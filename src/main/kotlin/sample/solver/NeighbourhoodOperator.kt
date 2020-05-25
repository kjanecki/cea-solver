package sample.solver

import sample.model.Individual
import sample.model.Neighbourhood

interface NeighbourhoodOperator<T : Individual> {

    suspend fun neighbourhoodFitness(neighbourhood: Neighbourhood<T>, operator: Operator<T>) : Double

    suspend fun neighbourhoodMean(neighbourhood: Neighbourhood<T>) : AbstractResult
}