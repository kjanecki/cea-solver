package sample.solver

import sample.model.Individual
import sample.model.Neighbourhood

interface NeighbourhoodOperator<T : Individual> {

    //zmiana podobna do Operator, żeby obsługiwał klasy Numeric, String itp. zamiast jednej konkretnej operacji

    //na danej klasie zawsze powinna być możliwość policzenia funkcji dopasowania sąsiedztwa
    //dodany parametr operator, bo funkcja dopasowania została do niego przeniesiona
    //podobnie jak w zwykłym fintess, fitness na sąsiedztwie też powinno zwracać wynik, czyli Double
    suspend fun neighbourhoodFitness(neighbourhood: Neighbourhood<T>, operator: Operator<T>) : Double

    //oraz policzenia średniej wartości, żeby osobnik mógł ewoulować jeżeli jego sąsiedztwo dobrze sobie radzi
    suspend fun neighbourhoodMean(neighbourhood: Neighbourhood<T>) : AbstractResult
}