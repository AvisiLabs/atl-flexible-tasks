package nl.avisi.atl.flexible.tasks.orchestrator.models.drinks

data class Beer(
    override val drinkId: String
) : Drink(drinkId = drinkId, type = DrinkType.BEER)
