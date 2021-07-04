package unq.edu.ar.GrupoMs12021.Resenia.service.job.notifySubs

import org.quartz.JobExecutionContext
import org.springframework.scheduling.quartz.QuartzJobBean

class NotifySubscriptorsJob: QuartzJobBean() {
    override fun executeInternal(context: JobExecutionContext) {
        System.out.println("job notify!")
//        this.service = context.getScheduler().getContext().get("service") as Service;
    }
}