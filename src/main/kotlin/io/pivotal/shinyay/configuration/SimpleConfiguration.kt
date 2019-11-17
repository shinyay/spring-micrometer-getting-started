package io.pivotal.shinyay.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import javax.annotation.PreDestroy

@Configuration
class SimpleConfiguration {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun taskExecutor(): TaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 2
        taskExecutor.maxPoolSize = 2
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true)
        taskExecutor.setAwaitTerminationSeconds(30)
        taskExecutor.initialize()

        logger.info(taskExecutor.toString())

        return taskExecutor
    }

    @PreDestroy
    fun destroy() {
        logger.info("Shutdown Initiated")
    }
}