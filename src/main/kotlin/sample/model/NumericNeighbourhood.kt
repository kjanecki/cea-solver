package sample.model

class NumericNeighbourhood : Neighbourhood<NumericIndividual> {

    val individuals : MutableList<NumericIndividual> = emptyList<NumericIndividual>().toMutableList()

    override fun getNodes(): List<NumericIndividual> {
        return individuals.toList()
    }
}