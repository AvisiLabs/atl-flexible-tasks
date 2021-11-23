package nl.avisi.atl.flexible.tasks.orchestrator.models

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType

data class StartProcessingMessage(
    val drinkId: String,
    val type: DrinkType
)
