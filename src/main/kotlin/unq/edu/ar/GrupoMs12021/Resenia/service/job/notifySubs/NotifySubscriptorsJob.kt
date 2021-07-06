package unq.edu.ar.GrupoMs12021.Resenia.service.job.notifySubs

import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.springframework.scheduling.quartz.QuartzJobBean
import unq.edu.ar.GrupoMs12021.Resenia.service.news.titlenews.NotifyTitleNewsService

class NotifySubscriptorsJob: QuartzJobBean() {

    var notifyTitleNewsService: NotifyTitleNewsService? = null
    val LOGGER: Logger = org.slf4j.LoggerFactory.getLogger(NotifySubscriptorsJob::class.java)

    override fun executeInternal(context: JobExecutionContext) {
        LOGGER.info("Starting notifyTitleNewsService")
        this.notifyTitleNewsService = context.getScheduler().getContext().get("notifyTitleNewsService") as NotifyTitleNewsService;

        this.notifyTitleNewsService!!.notifySubscribersTitleNews()
    }
}