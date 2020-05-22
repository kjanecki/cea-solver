package sample.generator

import sample.model.Edge
import sample.model.NumericIndividual
import sample.model.NumericNeighbourhood
import kotlin.random.Random

class RandomNeighbourhoodSelector : NeighbourhoodSelector<NumericIndividual> {

    override fun createNeighbourhood(edges: List<Edge>, node: NumericIndividual): NumericNeighbourhood {
        val shuffled = edges.filter { edge -> edge.start.getId().equals(node.getId()) }.shuffled()
        val neighbourhood = NumericNeighbourhood()
        if (shuffled.isNotEmpty()) {
            val random = Random.nextInt(0, shuffled.size)
            neighbourhood.individuals.addAll(shuffled.take(random).map { edge -> edge.end as NumericIndividual })
        }
        return neighbourhood
    }

}