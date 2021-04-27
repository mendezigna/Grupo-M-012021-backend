package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Client

@Repository
interface ClientDAO : CrudRepository<Client, Long> {
}