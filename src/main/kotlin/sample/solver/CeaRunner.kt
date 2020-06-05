package sample.solver

import sample.config.AppConfig

abstract class CeaRunner(protected val config: AppConfig) {

    abstract fun run(generations : Int)

    protected abstract fun writeResultsToCsv(dataArg: MutableList<String>, dataValue: MutableList<String>)
}