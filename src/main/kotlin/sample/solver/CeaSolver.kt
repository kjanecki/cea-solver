package sample.solver

import sample.model.Individual
import sample.model.Network

interface CeaSolver<T : Individual> {

    suspend fun nextGeneration()

    fun getCurrentState() : Network<T>

    fun getBestMatchedNode() : T

    fun getBestMatchedNodes(count : Int) : List<T>
}