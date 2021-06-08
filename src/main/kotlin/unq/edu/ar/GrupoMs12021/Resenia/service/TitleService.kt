package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.impl.TitleFilterService
import unq.edu.ar.GrupoMs12021.Resenia.service.filter.TitleFilter

@Service
class TitleService(@Autowired private val titleDAO : TitleDAO,
                   private val searchService: TitleFilterService) {

    fun get(titleId: String) : Title {
        return titleDAO.findByTitleId(titleId)
    }

    fun getAll(filter: TitleFilter): List<Title> {
        return searchService.findTitlesFiltered(filter)
    }

}