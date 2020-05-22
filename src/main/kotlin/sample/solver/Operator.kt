package sample.solver

import sample.model.Individual

interface Operator<T : Individual> {

    fun execute(node : T) : T
}