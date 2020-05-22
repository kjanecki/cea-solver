package sample.model

interface Network<T : Individual> {

    fun getNode(nodeId: Int) : T?;

    fun getNeighbourhood(nodeId: Int) : Neighbourhood<T>?;

}