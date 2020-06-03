package sample.config

import sample.generator.NearestNeighbourhoodSelector
import sample.generator.NeighbourhoodSelector
import sample.generator.NetworkGenerator
import sample.generator.ScaleFreeNetworkGenerator
import sample.model.Network
import sample.model.NumericIndividual
import sample.solver.*

class ScaleFreeNearestNeighbourhoodConfig : AppConfig {

    override fun getNeighbourhoodSelector(): NeighbourhoodSelector<NumericIndividual> {
        return NearestNeighbourhoodSelector()
    }

    override fun getNetworkGenerator(neighbourhoodSelector: NeighbourhoodSelector<NumericIndividual>): NetworkGenerator<NumericIndividual> {
        return ScaleFreeNetworkGenerator(neighbourhoodSelector, 10)
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