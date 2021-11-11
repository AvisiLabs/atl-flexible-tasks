package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.wine

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.AgedWine
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine.BottledWine
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class BottleWineTask : Task(
    taskId = "bottle-wine",
    supportedDrinkTypes = listOf(DrinkType.WINE),
    requires = listOf(AgedWine::class),
    produces = listOf(BottledWine::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(BottledWine(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
