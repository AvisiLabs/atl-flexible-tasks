package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.wine

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.HarvestedGrapes
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class HarvestGrapesTask : Task(
    taskId = "harvest-grapes",
    supportedDrinkTypes = listOf(DrinkType.WINE),
    requires = emptyList(),
    produces = listOf(HarvestedGrapes::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(HarvestedGrapes(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
