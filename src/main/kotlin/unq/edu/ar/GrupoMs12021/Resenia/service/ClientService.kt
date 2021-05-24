package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ClientDAO

@Service
class ClientService(@Autowired private val clientDAO : ClientDAO) {

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
}