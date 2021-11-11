package nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.beer

import nl.avisi.atl.flexible.tasks.orchestrator.models.tasks.TaskArtefact

data class BoiledWortAndHops(
    override val artefactId: Long
) : TaskArtefact
