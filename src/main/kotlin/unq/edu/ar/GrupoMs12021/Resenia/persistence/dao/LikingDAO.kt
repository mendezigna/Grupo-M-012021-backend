package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.edu.ar.GrupoMs12021.Resenia.model.review.Liking

//@Repository
interface LikingDAO: CrudRepository<Liking, Long> {
}