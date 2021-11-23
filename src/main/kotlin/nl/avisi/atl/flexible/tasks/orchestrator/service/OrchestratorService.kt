package nl.avisi.atl.flexible.tasks.orchestrator.service

import mu.KLogging
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.Status
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskStatus
import nl.avisi.atl.flexible.tasks.orchestrator.service.drink.DrinkService
import nl.avisi.atl.flexible.tasks.orchestrator.service.task.TaskService
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Service

@Service
class OrchestratorService(
    private val drinkService: DrinkService,
    private val taskService: TaskService
) {
    companion object : KLogging()

    fun startProcessing(drinkId: String, type: DrinkType) {
        drinkService.createNewDrink(type, drinkId)
        taskService.withCurrentStateOfDrink(drinkId) { statuses ->
            executeNextTasks(drinkId = drinkId, type = type, statuses = statuses)
        }
    }

    fun processNewArtefacts(
        taskId: String,
        drinkId: String,
        type: DrinkType,
        artefacts: List<TaskArtefact>
    ) {
        logger.info { "Completed task [$taskId] for drink [$drinkId], artefacts: [${artefacts.joinToString(", ") { it::class.simpleName ?: "" }}]" }

        taskService.withCurrentStateOfDrink(drinkId) { statuses ->
            if (statuses.none { it.taskId == taskId && it.drinkId == drinkId && it.status == Status.STARTED }) {
                throw IllegalStateException("Cannot complete task that has not been started.")
            }

            val drink = drinkService.getDrink(drinkId) ?: throw IllegalStateException("Cannot get drink info")
            val newStatus = taskService.completeTask(drink, taskId, artefacts)
            val updatedStatuses = statuses.filter { it.taskId != taskId } + newStatus

            executeNextTasks(drinkId = drinkId, type = type, statuses = updatedStatuses)
        }
    }

    fun processFailedTask(
        taskId: String,
        type: DrinkType,
        drinkId: String,
    ) {
        logger.warn { "Failed task [$taskId] for drink [$drinkId]" }

        taskService.withCurrentStateOfDrink(drinkId) { statuses ->
            if (statuses.none { it.taskId == taskId && it.drinkId == drinkId && it.status == Status.STARTED }) {
                throw IllegalStateException("Cannot fail task that has not been started.")
            }
            val newStatus = taskService.failTask(drinkId = drinkId, taskId = taskId)
            val updatedStatuses = statuses.filter { it.taskId != taskId } + newStatus

            executeNextTasks(drinkId = drinkId, type = type, statuses = updatedStatuses)
        }
    }

    private fun executeNextTasks(
        drinkId: String,
        type: DrinkType,
        statuses: List<TaskStatus>
    ) {
        val nextTasksWithInputs = taskService.getNextTasksWithInputArtefacts(drinkId, type, statuses)

        nextTasksWithInputs.forEach { (task, _) -> taskService.startTask(drinkId, task.taskId) }

        nextTasksWithInputs.forEach { (task, inputs) ->
            executeTask(task, drinkId, type, inputs)
        }
    }

    private fun executeTask(
        task: Task,
        drinkId: String,
        type: DrinkType,
        inputs: List<TaskArtefact>
    ) = try {
        val outputArtefacts = task.callTask(
            drinkId = drinkId,
            inputData = inputs
        )
        processNewArtefacts(task.taskId, drinkId, type, outputArtefacts)
    } catch (e: Exception) {
        processFailedTask(task.taskId, type, drinkId)
    }
}
