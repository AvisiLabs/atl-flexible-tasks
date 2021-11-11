package nl.avisi.atl.flexible.tasks.orchestrator.repositories

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskStatus
import org.springframework.stereotype.Repository

@Repository
class TaskStatusRepository {
    private val taskStatusesForDrinkId: MutableMap<String, List<TaskStatus>> = mutableMapOf()

    fun save(taskStatus: TaskStatus): TaskStatus {
        val statusesForDrinkId = taskStatusesForDrinkId.getOrDefault(taskStatus.drinkId, emptyList())
            .filter { it.taskId != taskStatus.taskId }
        taskStatusesForDrinkId[taskStatus.drinkId] = statusesForDrinkId + taskStatus
        return taskStatus
    }

    fun findByDrinkId(drinkId: String): List<TaskStatus>? =
        taskStatusesForDrinkId[drinkId]
}
