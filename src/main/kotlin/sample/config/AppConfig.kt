package sample.config

import sample.generator.NeighbourhoodSelector
import sample.generator.NetworkGenerator
import sample.model.Network
import sample.model.NumericIndividual
import sample.solver.CeaSolver
import sample.solver.NeighbourhoodOperator
import sample.solver.Operator

interface AppConfig {

    fun getNeighbourhoodSelector() : NeighbourhoodSelector<NumericIndividual>

    fun getNetworkGenerator(neighbourhoodSelector: NeighbourhoodSelector<NumericIndividual>)
            : NetworkGenerator<NumericIndividual>

    fun getOperator() : Operator<NumericIndividual>

    fun getNeighbourhoodOperator() : NeighbourhoodOperator<NumericIndividual>

    fun getSolver(network: Network<NumericIndividual>,
                      neighbourhoodOperator: NeighbourhoodOperator<NumericIndividual>,
                      operator: Operator<NumericIndividual>)
            : CeaSolver<NumericIndividual>
}