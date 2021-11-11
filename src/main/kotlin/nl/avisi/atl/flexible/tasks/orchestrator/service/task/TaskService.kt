package nl.avisi.atl.flexible.tasks.orchestrator.service.task

import mu.KLogging
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.Drink
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.Status
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskStatus
import nl.avisi.atl.flexible.tasks.orchestrator.tasks.Task
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskStatusService: TaskStatusService,
    private val taskArtefactService: TaskArtefactService,
    private val tasks: List<Task>
) {
    companion object : KLogging()

    private val tasksByDrinkType: Map<DrinkType, List<Task>> =
        DrinkType.values().associateWith {
            tasks.filter { task -> it in task.supportedDrinkTypes }
        }

    fun withCurrentStateOfDrink(drinkId: String, function: (state: List<TaskStatus>) -> Unit) {
        val statuses = taskStatusService.getTaskStatusesForDrink(drinkId)
        function(statuses)
    }

    fun getNextTasksWithInputArtefacts(drinkId: String, type: DrinkType, statuses: List<TaskStatus>): List<Pair<Task, List<TaskArtefact>>> {
        val tasksByStatus = tasksByDrinkType[type]!!
            .groupBy { task -> statuses.find { it.taskId == task.taskId }?.status }

        val notExecuted = tasksByStatus[null] ?: emptyList()
        val succeededTasks = tasksByStatus[Status.SUCCEEDED] ?: emptyList()

        val nextTasks = notExecuted.filter { task ->
            task.allRequirementsHaveBeenProducedBySucceededTasks(succeededTasks)
        }

        val artefacts = taskArtefactService.getArtefactsForDrink(drinkId)

        return nextTasks.map { it to getRequiredInputsForTask(it, drinkId, artefacts) }
    }

    private fun Task.allRequirementsHaveBeenProducedBySucceededTasks(succeededTasks: List<Task>) =
        this.requires.all { requires ->
            succeededTasks.any { succeededTask -> requires in succeededTask.produces }
        }

    private fun getRequiredInputsForTask(task: Task, drinkId: String, artefacts: List<TaskArtefact>): List<TaskArtefact> =
        task.requires.map { requiredClass ->
            artefacts.find { artefact -> requiredClass.isInstance(artefact) }
                ?: throw IllegalStateException("Could not find ${requiredClass.simpleName} as input for task [${task.taskId}], drink [$drinkId]")
        }

    fun startTask(drinkId: String, taskId: String) {
        taskStatusService.setTaskStatus(
            drinkId = drinkId,
            taskId = taskId,
            status = Status.STARTED
        )
    }

    fun completeTask(drink: Drink, taskId: String, artefacts: List<TaskArtefact>): TaskStatus {
        artefacts.forEach {
            taskArtefactService.addArtefact(
                drinkId = drink.drinkId,
                artefact = it
            )
        }

        return taskStatusService.setTaskStatus(
            drinkId = drink.drinkId,
            taskId = taskId,
            status = Status.SUCCEEDED
        )
    }

    fun failTask(drinkId: String, taskId: String): TaskStatus =
        taskStatusService.setTaskStatus(
            drinkId = drinkId,
            taskId = taskId,
            status = Status.FAILED
        )
}
