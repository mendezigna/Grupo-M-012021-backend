package unq.edu.ar.GrupoMs12021.Resenia.service.job.cache

import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component
import unq.edu.ar.GrupoMs12021.Resenia.service.CacheService

@Component
public class UpdateCacheJob : QuartzJobBean() {

    val LOGGER: Logger = org.slf4j.LoggerFactory.getLogger(UpdateCacheJob::class.java)
    var cacheService: CacheService? = null

    override fun executeInternal(context: JobExecutionContext) {
        LOGGER.info("Updating Cache TitlesLite")
        this.cacheService = context.getScheduler().getContext().get("cacheService") as CacheService?;
        var titlesUpdated = cacheService!!.updateLatestHour()
    }
}