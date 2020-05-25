package sample.solver

import sample.model.Individual

interface Operator<T : Individual> {

    fun mutation(node : T) : T

    fun fitness(node : T) : Double
}