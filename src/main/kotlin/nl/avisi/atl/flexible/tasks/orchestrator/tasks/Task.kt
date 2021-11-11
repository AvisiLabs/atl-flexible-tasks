package nl.avisi.atl.flexible.tasks.orchestrator.tasks

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import kotlin.reflect.KClass

abstract class Task(
    val taskId: String,
    val supportedDrinkTypes: List<DrinkType>,
    val requires: List<KClass<out TaskArtefact>> = emptyList(),
    val produces: List<KClass<out TaskArtefact>>
) {
    companion object {
        const val DEFAULT_SLEEP_TIME = 1000L
    }

    abstract fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact>
}
