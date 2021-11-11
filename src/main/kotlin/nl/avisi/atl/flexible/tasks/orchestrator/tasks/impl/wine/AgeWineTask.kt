package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.wine

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.AgedWine
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.PressedWine
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class AgeWineTask : Task(
    taskId = "age-wine",
    supportedDrinkTypes = listOf(DrinkType.WINE),
    requires = listOf(PressedWine::class),
    produces = listOf(AgedWine::class)
) {
    companion object {
        private const val LONG_SLEEP_TIME = 3000L
    }

    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(LONG_SLEEP_TIME)
        return listOf(AgedWine(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
