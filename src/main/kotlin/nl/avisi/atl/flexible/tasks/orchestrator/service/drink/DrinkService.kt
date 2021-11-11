package nl.avisi.atl.flexible.tasks.orchestrator.service.drink

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.Beer
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.Drink
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.Wine
import nl.avisi.atl.flexible.tasks.orchestrator.repositories.DrinkRepository
import org.springframework.stereotype.Service

@Service
class DrinkService(
    private val drinkRepository: DrinkRepository
) {
    fun createNewDrink(type: DrinkType, drinkId: String) {
        if (drinkRepository.existsById(drinkId)) throw IllegalArgumentException("Drink with id [$drinkId] already exists.")

        val newDrink = when (type) {
            DrinkType.BEER -> Beer(drinkId)
            DrinkType.WINE -> Wine(drinkId)
        }
        drinkRepository.save(newDrink)
    }

    fun getDrink(drinkId: String): Drink? =
        drinkRepository.findById(drinkId)
}
