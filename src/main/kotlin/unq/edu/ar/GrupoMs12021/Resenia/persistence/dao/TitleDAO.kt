package unq.edu.ar.GrupoMs12021.Resenia.persistence.dao

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.persistence.HibernateTransactionRunner

class TitleDAO {

    fun create(title: Title) : Title {
        val session = HibernateTransactionRunner.currentSession
        session.save(title)
        return title
    }

    fun get(id: String): Title{
        val session = HibernateTransactionRunner.currentSession
        return session.get(Title::class.java, id)
    }
}