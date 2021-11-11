package nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact

data class BottledBeer(
    override val artefactId: Long
) : TaskArtefact
