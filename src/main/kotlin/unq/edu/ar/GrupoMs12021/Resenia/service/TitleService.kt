package unq.edu.ar.GrupoMs12021.Resenia.service

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.HibernateTransactionRunner.runTrx
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO

class TitleService(private val titleDAO : TitleDAO) {

    fun create(title: Title): Title{
        return runTrx {
            titleDAO.create(title)
        }

    }

    fun get(id: String): Title{
        return titleDAO.get(id)
    }
}