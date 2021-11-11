package nl.avisi.atl.flexible.tasks.orchestrator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan("nl.avisi.atl.flexible.tasks.orchestrator")
class Orchestrator {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Orchestrator::class.java, *args)
        }
    }
}
