package nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact

data class BottledWine(
    override val artefactId: Long
) : TaskArtefact
