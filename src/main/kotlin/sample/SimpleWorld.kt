package sample

import sample.config.SimpleWorldRandomNeighbourhoodConfig
import sample.solver.CeaRunnerImpl

fun main() {

    val config = SimpleWorldRandomNeighbourhoodConfig()
    val ceaRunner = CeaRunnerImpl(config)
    ceaRunner.run(100)

}