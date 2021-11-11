package nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.wine

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact

data class AgedWine(
    override val artefactId: Long
) : TaskArtefact
