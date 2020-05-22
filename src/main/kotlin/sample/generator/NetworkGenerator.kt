package sample.generator

import sample.model.Individual
import sample.model.Network

interface NetworkGenerator<T : Individual> {

    fun createNetwork() : Network<T>

}