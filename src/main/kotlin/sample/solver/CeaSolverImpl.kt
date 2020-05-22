package sample.solver

import sample.model.Network
import sample.model.NumericIndividual

class CeaSolverImpl(val network: Network<NumericIndividual>) : CeaSolver<NumericIndividual> {

    override fun nextGeneration() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentState(): Network<NumericIndividual> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return network
    }

    override fun getBestMatchedNode(): NumericIndividual {
        return network.getNode(1)!!
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBestMatchedNodes(count: Int): List<NumericIndividual> {
        return listOf(network.getNode(1)!!)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}