package unq.edu.ar.GrupoMs12021.Resenia.service.job.notifySubs

import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import unq.edu.ar.GrupoMs12021.Resenia.service.news.titlenews.NotifyTitleNewsService

@Component
class ScheduleNotifySubsJob(
        @Autowired var notifyTitleNewsService: NotifyTitleNewsService,
        @Qualifier("createScheduler") @Autowired var scheduler: Scheduler) {

    init {
        setUpScheduler()
    }

    fun setUpScheduler() {
        try {
            var job: JobDetail = this.getJobDetail()
            var trigger = this.getTriggerFor(job)

            // variable necesaria para el contexto que utilizara el Job
            scheduler.getContext().put("notifyTitleNewsService", notifyTitleNewsService );

            scheduler.start()
            scheduler.scheduleJob(job, trigger)

        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun getTriggerFor(job: JobDetail): Trigger {
        val schedule: String = "0 0/6 * * * ?" //
        val cron: CronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedule)
        return TriggerBuilder.newTrigger()
                .withIdentity("NotifySubs", "Notify")
                .forJob(job)
                .withSchedule(cron)
                .build()
    }

    private fun getJobDetail(): JobDetail {
        return JobBuilder.newJob(NotifySubscriptorsJob::class.java)
                .withIdentity("NotifySubs", "Notify").build()
    }
}