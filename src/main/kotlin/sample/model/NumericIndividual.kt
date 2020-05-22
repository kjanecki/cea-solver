package sample.model

class NumericIndividual(private val id: Int, val value: Double) : Individual {

    override fun getId(): Int {
        return id
    }

    fun getNumericValue(): Double {
        return value
    }

    override fun toString(): String {
        return "NumericIndividual(id=$id, value=$value)"
    }

}