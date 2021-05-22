package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client
import java.util.*

@Repository
interface ClientDAO : CrudRepository<Client, Long> {

    fun findByEmailAndPassword(email: String, password: String) : Client

    fun findByApyKey(apyKey: String) : Optional<Client>
}