package nl.avisi.atl.flexible.tasks.orchestrator.service.task

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.Status
import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskStatus
import nl.avisi.atl.flexible.tasks.orchestrator.repositories.TaskStatusRepository
import org.springframework.stereotype.Service

@Service
class TaskStatusService(
    private val repository: TaskStatusRepository
) {
    fun getTaskStatusesForDrink(drinkId: String): List<TaskStatus> =
        repository.findByDrinkId(drinkId) ?: emptyList()

    fun setTaskStatus(drinkId: String, taskId: String, status: Status): TaskStatus {
        return repository.save(
            TaskStatus(
                drinkId = drinkId,
                taskId = taskId,
                status = status
            )
        )
    }
}
