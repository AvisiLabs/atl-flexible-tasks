package nl.avisi.atl.flexible.tasks.orchestrator.controller

import nl.avisi.atl.flexible.tasks.orchestrator.models.drinks.DrinkType
import nl.avisi.atl.flexible.tasks.orchestrator.service.OrchestratorService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrchestratorController(private val orchestratorService: OrchestratorService) {
    @PostMapping
    fun startProcessing(@RequestBody message: StartProcessingMessage) {
        orchestratorService.startProcessing(message.drinkId, message.type)
    }
}

data class StartProcessingMessage(
    val drinkId: String,
    val type: DrinkType
)
