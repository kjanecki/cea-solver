package sample.generator

import sample.model.Edge
import sample.model.Individual
import sample.model.Neighbourhood

interface NeighbourhoodSelector<T : Individual> {

    fun createNeighbourhood(edges : List<Edge>, node : T) : Neighbourhood<T>

}