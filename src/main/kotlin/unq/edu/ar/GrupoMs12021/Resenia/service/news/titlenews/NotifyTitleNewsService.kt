package unq.edu.ar.GrupoMs12021.Resenia.service.news.titlenews

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Subscription
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.SubscriptionDAO
import unq.edu.ar.GrupoMs12021.Resenia.service.client.ServiceRestClient

@Service
class NotifyTitleNewsService(
        @Autowired private val subscriptionDAO: SubscriptionDAO,
        @Autowired private val newsTitleService: NewsTitleService,
        @Autowired private val serviceRestClient: ServiceRestClient
        ) {

    val LOGGER: Logger = org.slf4j.LoggerFactory.getLogger(NotifyTitleNewsService::class.java)

    fun notifySubscribersTitleNews() {
        var titleId = newsTitleService.getLastfromTitleNews()

        while (titleId != null){
            deliverNewsFrom(titleId)
            titleId = newsTitleService.getLastfromTitleNews()
        }
    }

    private fun deliverNewsFrom(titleId: String){
        LOGGER.info("Delivering news of TitleId "+titleId)
        var subs = this.getSubscribersFrom(titleId)
        subs.forEach {
            try {
                this.notifySubscriber(it)
            } catch (e:Exception){
                LOGGER.error(String.format("Failed notify client with Id: %s TitleId:%s",it.clientEmail,titleId ), e)
            }
        }
    }

    private fun getSubscribersFrom(titleId: String): List<Subscription> {
        return subscriptionDAO.findSubscriptionsByTitleId(titleId)
    }

    private fun notifySubscriber(subscriber: Subscription) {
        this.serviceRestClient.getResponsehttp(subscriber.url)
    }

}