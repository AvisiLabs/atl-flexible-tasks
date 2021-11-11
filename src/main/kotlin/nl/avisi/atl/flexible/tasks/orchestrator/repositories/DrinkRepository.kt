package nl.avisi.atl.flexible.tasks.orchestrator.repositories

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.Drink
import org.springframework.stereotype.Repository

@Repository
class DrinkRepository {
    private val drinks: MutableMap<String, Drink> = mutableMapOf()

    fun save(drink: Drink) {
        drinks[drink.drinkId] = drink
    }

    fun findById(id: String): Drink? =
        drinks[id]

    fun existsById(id: String): Boolean =
        drinks.containsKey(id)
}
