package nl.avisi.atl.flexible.tasks.orchestrator.repositories

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact
import org.springframework.stereotype.Repository

@Repository
class ArtefactRepository {
    private val artefactsByDrinkId: MutableMap<String, List<TaskArtefact>> = mutableMapOf()

    fun save(drinkId: String, artefact: TaskArtefact) {
        val drinksOfType = artefactsByDrinkId.getOrDefault(drinkId, emptyList())
        artefactsByDrinkId[drinkId] = drinksOfType + artefact
    }

    fun getAllArtefactsForDrinkId(drinkId: String): List<TaskArtefact>? =
        artefactsByDrinkId[drinkId]
}
