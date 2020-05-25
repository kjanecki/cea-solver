package sample.solver

import kotlinx.coroutines.delay
import sample.model.Neighbourhood
import sample.model.NumericIndividual
import kotlin.random.Random

class NumericNeighbourhoodOperator : NeighbourhoodOperator<NumericIndividual> {

    override suspend fun neighbourhoodFitness(neighbourhood: Neighbourhood<NumericIndividual>,
                                      operator: Operator<NumericIndividual>): Double {
        delay(Random.nextLong(1000, 2000))
        return neighbourhood.getNodes()
            .map { node -> operator.fitness(node)}
            .average()
    }

    override suspend fun neighbourhoodMean(neighbourhood: Neighbourhood<NumericIndividual>): NumericResult{
        delay(Random.nextLong(1000, 2000))
        return NumericResult(neighbourhood.getNodes()
            .map { node -> node.getNumericValue()}
            .average())
    }
}