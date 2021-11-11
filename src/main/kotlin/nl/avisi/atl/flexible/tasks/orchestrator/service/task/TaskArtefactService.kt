package nl.avisi.atl.flexible.tasks.orchestrator.service.task

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import nl.avisi.atl.flexible.tasks.orchestrator.repositories.ArtefactRepository
import org.springframework.stereotype.Service

@Service
class TaskArtefactService(
    private val artefactRepository: ArtefactRepository,
) {

    fun getArtefactsForDrink(drinkId: String): List<TaskArtefact> =
        artefactRepository.getAllArtefactsForDrinkId(drinkId) ?: emptyList()

    fun addArtefact(drinkId: String, artefact: TaskArtefact) {
        artefactRepository.save(drinkId, artefact)
    }
}
