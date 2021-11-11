package nl.avisi.atl.flexible.tasks.orchestrator.models.drinks

sealed class Drink(
    open val drinkId: String,
    val type: DrinkType
)
