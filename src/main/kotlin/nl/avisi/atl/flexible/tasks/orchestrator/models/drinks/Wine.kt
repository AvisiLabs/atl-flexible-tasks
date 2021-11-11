package nl.avisi.atl.flexible.tasks.orchestrator.models.drinks

data class Wine(
    override val drinkId: String
) : Drink(drinkId = drinkId, type = DrinkType.WINE)
