package sample.config

import sample.generator.NeighbourhoodSelector
import sample.generator.NetworkGenerator
import sample.generator.RandomNeighbourhoodSelector
import sample.generator.SimpleWorldNetworkGenerator
import sample.model.Network
import sample.model.NumericIndividual
import sample.solver.*

class SimpleWorldRandomNeighbourhoodConfig : AppConfig {

    override fun getNeighbourhoodSelector(): NeighbourhoodSelector<NumericIndividual> {
        return RandomNeighbourhoodSelector()
    }

    override fun getNetworkGenerator(neighbourhoodSelector: NeighbourhoodSelector<NumericIndividual>): NetworkGenerator<NumericIndividual> {
        return SimpleWorldNetworkGenerator(neighbourhoodSelector, 0.2, 16)
    }

    override fun getOperator(): Operator<NumericIndividual> {
        return NumericOperator()
    }

    override fun getNeighbourhoodOperator(): NeighbourhoodOperator<NumericIndividual> {
        return NumericNeighbourhoodOperator()
    }

    override fun getSolver(network: Network<NumericIndividual>,
                           neighbourhoodOperator: NeighbourhoodOperator<NumericIndividual>,
                           operator: Operator<NumericIndividual>)
            : CeaSolver<NumericIndividual> {
        return CeaSolverImpl(network, neighbourhoodOperator, operator)
    }
}