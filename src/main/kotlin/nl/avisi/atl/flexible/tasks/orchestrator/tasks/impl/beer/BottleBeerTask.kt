package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.beer

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.BottledBeer
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.FermentedWort
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class BottleBeerTask : Task(
    taskId = "bottle-beer",
    supportedDrinkTypes = listOf(DrinkType.BEER),
    requires = listOf(FermentedWort::class),
    produces = listOf(BottledBeer::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(BottledBeer(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
