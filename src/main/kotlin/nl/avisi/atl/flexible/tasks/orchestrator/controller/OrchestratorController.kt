package nl.avisi.atl.flexible.tasks.orchestrator.controller

import mu.KLogging
import nl.avisi.atl.flexible.tasks.orchestrator.models.StartProcessingMessage
import nl.avisi.atl.flexible.tasks.orchestrator.service.OrchestratorService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrchestratorController(private val orchestratorService: OrchestratorService) {
    companion object : KLogging()

    @PostMapping
    fun startProcessing(@RequestBody message: StartProcessingMessage) {
        logger.info { "Received request for making drink [${message.type}] with id [${message.drinkId}]" }
        orchestratorService.startProcessing(message.drinkId, message.type)
    }
}
