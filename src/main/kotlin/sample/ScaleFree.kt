package sample

import sample.config.ScaleFreeNearestNeighbourhoodConfig
import sample.solver.CeaRunnerImpl

fun main() {

    val config = ScaleFreeNearestNeighbourhoodConfig()
    val ceaRunner = CeaRunnerImpl(config)
    ceaRunner.run(100)

}