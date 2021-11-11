package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.beer

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.MaltedBarley
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class MaltBarleyTask : Task(
    taskId = "malt-barley",
    supportedDrinkTypes = listOf(DrinkType.BEER),
    requires = emptyList(),
    produces = listOf(MaltedBarley::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(MaltedBarley(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
