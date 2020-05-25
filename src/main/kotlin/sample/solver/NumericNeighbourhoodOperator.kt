package sample.solver

import kotlinx.coroutines.delay
import sample.model.Neighbourhood
import sample.model.NumericIndividual
import java.lang.Math.pow
import kotlin.random.Random

class NumericNeighbourhoodOperator : NeighbourhoodOperator<NumericIndividual> {

    //zamiast sumy zwracana jest średnia, bo inaczej była możliwość
    //że bardzo słabe, ale duże sąsiedztwo uznawane było za lepsze od niewielkiego, ale dobrego
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