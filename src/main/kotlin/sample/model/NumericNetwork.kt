package sample.model

class NumericNetwork(
    private val nodes: Map<Int, NumericIndividual>,
    private val edgeMatrix: Array<Array<Int>>,
    private val neighbourhoods: Map<Int, Neighbourhood<NumericIndividual>>
) : Network<NumericIndividual> {

    override fun getNode(nodeId: Int): NumericIndividual? {
        return nodes[nodeId]
    }

    override fun getNodes(): Collection<NumericIndividual> {
        return nodes.values//To change body of created functions use File | Settings | File Templates.
    }

    override fun getNeighbourhood(nodeId: Int): Neighbourhood<NumericIndividual>? {
        return neighbourhoods[nodeId]
    }


}