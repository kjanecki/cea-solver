package sample.solver

import sample.model.Individual

interface Operator<T : Individual> {

    //zakładając że operatory to np. klasa NumericOperator, StringOperator itp.
    //dla dowolnego zawsze musi istnieć możliwość mutacji
    fun mutation(node : T) : T

    //i zawsze można policzyć funkcję dopasowania konkretnego osobnika
    //wynik punktowo określa jak dobrze osobnik sobie radzi, więc bez względu na jego typ zawsze wynikiem jest double
    fun fitness(node : T) : Double
}