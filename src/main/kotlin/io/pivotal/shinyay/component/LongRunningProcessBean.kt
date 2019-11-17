package io.pivotal.shinyay.component

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.task.TaskExecutor
import org.springframework.stereotype.Component
import java.lang.Exception
import javax.annotation.PostConstruct

@Component
class LongRunningProcessBean(@Qualifier("taskExecutor") val taskExecutor: TaskExecutor) {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostConstruct
    fun runTaskOnStartup() {
        logger.info("runTaskOnStartup entering...")
        for (procNumber in 0..2) {
            taskExecutor.execute {
                try {
                    logger.info("Long running process $procNumber using thread pool started")
                    Thread.sleep(30000)
                    logger.info("Long running process $procNumber using thread pool completed")
                } catch (e: Exception) {
                    logger.error("Error while executing task", e)
                }
            }
        }
        logger.info("runTaskOnStartup exiting...")
    }
}