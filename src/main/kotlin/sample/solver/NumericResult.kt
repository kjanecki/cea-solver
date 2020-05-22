package sample.solver

class NumericResult(val result: Double) : AbstractResult {

    override fun compareTo(other: AbstractResult): Int {
        return result.compareTo((other as NumericResult).result)
    }
}