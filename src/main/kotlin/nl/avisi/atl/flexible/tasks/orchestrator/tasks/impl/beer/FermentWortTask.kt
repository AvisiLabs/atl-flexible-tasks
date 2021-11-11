package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.beer

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.BoiledWortAndHops
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.FermentedWort
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class FermentWortTask : Task(
    taskId = "ferment-wort",
    supportedDrinkTypes = listOf(DrinkType.BEER),
    requires = listOf(BoiledWortAndHops::class),
    produces = listOf(FermentedWort::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(FermentedWort(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
