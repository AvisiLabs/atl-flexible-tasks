package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.wine

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.FermentedGrapes
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.PressedWine
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class PressWineTask : Task(
    taskId = "press-wine",
    supportedDrinkTypes = listOf(DrinkType.WINE),
    requires = listOf(FermentedGrapes::class),
    produces = listOf(PressedWine::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(PressedWine(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
