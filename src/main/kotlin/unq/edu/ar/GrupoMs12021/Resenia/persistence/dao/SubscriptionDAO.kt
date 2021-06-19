package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import unq.edu.ar.GrupoMs12021.Resenia.model.client.Subscription

@Repository
interface SubscriptionDAO : JpaRepository<Subscription, Long> {
}