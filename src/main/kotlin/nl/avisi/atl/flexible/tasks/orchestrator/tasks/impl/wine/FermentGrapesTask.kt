package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.wine

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.FermentedGrapes
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.HarvestedGrapes
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class FermentGrapesTask : Task(
    taskId = "ferment-grapes",
    supportedDrinkTypes = listOf(DrinkType.WINE),
    requires = listOf(HarvestedGrapes::class),
    produces = listOf(FermentedGrapes::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(FermentedGrapes(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
