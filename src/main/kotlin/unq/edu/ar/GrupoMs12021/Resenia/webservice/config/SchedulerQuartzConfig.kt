package unq.edu.ar.GrupoMs12021.Resenia.webservice.config

import org.quartz.Scheduler
import org.quartz.impl.StdSchedulerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SchedulerQuartzConfig {

    @Bean
    fun createScheduler(): Scheduler {
        val factory = StdSchedulerFactory();
        factory.initialize()
        return factory.scheduler
    }

}