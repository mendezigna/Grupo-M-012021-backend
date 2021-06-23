package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Metrics
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Subscription
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ClientDAO
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.SubscriptionDAO
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers.SubscriptionDTO
import javax.transaction.Transactional

@Service
class ClientService(@Autowired private val clientDAO : ClientDAO, @Autowired private val titleDAO: TitleDAO, @Autowired private val subscriptionDAO : SubscriptionDAO) {

    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val STRINGLENGTH = 10

    fun login(username : String, password: String) : Client{
        return clientDAO.findByEmailAndPassword(username, password)
    }

    fun register(user : Client) : Client{
        user.apyKey = getRandomString()
        return clientDAO.save(user)
    }

    private fun getRandomString() : String{
        val randomString = (1..STRINGLENGTH)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
        return randomString
    }

    fun subscribe(subscription : SubscriptionDTO){
        clientDAO.findByEmail(subscription.email)
        titleDAO.findByTitleId(subscription.titleId)
        subscriptionDAO.save(Subscription(subscription.url, subscription.titleId, subscription.email))
    }

    @Transactional
    fun getMetrics(email: String): Metrics {
        val client = clientDAO.findByEmail(email)
        return client.metrics
    }
}