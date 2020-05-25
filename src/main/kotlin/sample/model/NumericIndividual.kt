package sample.model

class NumericIndividual(private val id: Int, private var value: Double) : Individual {

    override fun getId(): Int {
        return id
    }

    fun getNumericValue(): Double {
        return value
    }
    //dodany setter i zmiana 'value' na prywatną
    fun setNumericValue(newValue: Double){
        value = newValue
    }

    override fun toString(): String {
        return "NumericIndividual(id=$id, value=$value)"
    }

}