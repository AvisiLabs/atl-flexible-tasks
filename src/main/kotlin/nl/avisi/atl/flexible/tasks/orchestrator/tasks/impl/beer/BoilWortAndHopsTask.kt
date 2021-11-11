package nl.avisi.atl.flexible.tasks.orchestrator.tasks.impl.beer

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.BoiledWortAndHops
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.MashedMalt
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer.PreparedHops
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class BoilWortAndHopsTask : Task(
    taskId = "boil-wort-and-hops",
    supportedDrinkTypes = listOf(DrinkType.BEER),
    requires = listOf(MashedMalt::class, PreparedHops::class),
    produces = listOf(BoiledWortAndHops::class)
) {
    override fun callTask(drinkId: String, inputData: List<TaskArtefact>): List<TaskArtefact> {
        Thread.sleep(DEFAULT_SLEEP_TIME)
        return listOf(BoiledWortAndHops(Random.nextLong(0, Long.MAX_VALUE)))
    }
}
