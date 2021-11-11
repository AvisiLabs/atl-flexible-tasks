package nl.avisi.atl.flexible.tasks.orchestrator.models.tasks

class TaskStatus(
    val drinkId: String,
    val taskId: String,
    var status: Status
)

enum class Status {
    STARTED,
    FAILED,
    SUCCEEDED
}
