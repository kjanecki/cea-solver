package sample.generator

import sample.model.Individual
import sample.model.Neighbourhood
import sample.model.NumericIndividual

interface NeighbourhoodSelector<T : Individual> {

    fun createNeighbourhood(edgeMatrix : Array<Array<Int>>, nodes: Map<Int, NumericIndividual>, node : T) : Neighbourhood<T>

}