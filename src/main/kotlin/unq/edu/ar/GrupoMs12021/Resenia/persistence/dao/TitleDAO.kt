package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title

@Repository
interface TitleDAO : CrudRepository<Title, Long>{
    fun findByTitleId(titleId: String) : Title;
}