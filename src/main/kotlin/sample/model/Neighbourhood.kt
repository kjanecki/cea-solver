package sample.model

interface Neighbourhood<T : Individual> {

    fun getNodes() : List<T>

}