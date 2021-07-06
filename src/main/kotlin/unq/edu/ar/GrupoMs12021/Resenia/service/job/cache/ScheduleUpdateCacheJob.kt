package unq.edu.ar.GrupoMs12021.Resenia.service.job.cache

import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import unq.edu.ar.GrupoMs12021.Resenia.service.CacheService

@Component
class ScheduleUpdateCacheJob {

    val cacheService: CacheService
    var scheduler: Scheduler

    constructor(@Autowired cacheService: CacheService,
                @Qualifier("createScheduler") @Autowired scheduler: Scheduler
    ) {
        this.scheduler = scheduler
        this.cacheService = cacheService
        setUpScheduler()
    }

    fun setUpScheduler() {
        try{
            // Cache updateCache
            var job: JobDetail = this.getJobDetail()
            var trigger = this.getTriggerFor(job)
            // variable necesaria para el contexto que utilizara el Job
            scheduler.getContext().put("cacheService", this.cacheService );

            scheduler.start()
            scheduler.scheduleJob(job, trigger)

        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun getTriggerFor(job: JobDetail): Trigger {
        val schedule: String = "0 0/3 * * * ?" // cada 3 min
        val cron: CronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedule)
        return TriggerBuilder.newTrigger()
                .withIdentity("UpdateCache", "TitlesLite")
                .forJob(job)
                .withSchedule(cron)
                .build()

    }

    private fun getJobDetail(): JobDetail {
        return JobBuilder.newJob(UpdateCacheJob::class.java)
                .withIdentity("UpdateCache", "TitlesLite").build()
    }

}