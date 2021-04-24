package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO

@Service
class TitleService(private val titleDAO : TitleDAO) {

    fun get(titleId: String) : Title {
        return titleDAO.findByTitleId(titleId)
    }

}