package helpers

import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleType
import unq.edu.ar.GrupoMs12021.Resenia.persistence.HibernateTransactionRunner
import unq.edu.ar.GrupoMs12021.Resenia.persistence.HibernateTransactionRunner.runTrx
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService

class DataService {

    private val titleDAO : TitleDAO = TitleDAO()
    private val titleService: TitleService = TitleService(titleDAO)
    val title1: Title = Title("tt0000001", TitleType.SHORT,"Carmencita", 1894, null, 1, listOf(Genres.DOCUMENTARY, Genres.SHORT))
    val title2: Title = Title("tt0372140", TitleType.MOVIE,"American Beer", 2004, null, 105, listOf(Genres.DOCUMENTARY))
    val title3: Title = Title("tt0367275", TitleType.TVSERIES,"All About You", 1874, null, 15, listOf(Genres.DOCUMENTARY, Genres.FAMILY))
    val title4: Title = Title("tt0367279", TitleType.TVSERIES,"Arrested Development", 2003, 2019, 22, listOf(Genres.COMEDY))

    fun createInitialData(){
        val titles = listOf(title1, title2, title3, title4)
        runTrx {
            titles.forEach { this.titleDAO.create(it) }
        }

    }

    fun deleteAll() {
        runTrx {
            val session = HibernateTransactionRunner.currentSession
            val nombreDeTablas = session.createNativeQuery("show tables").resultList
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate()
            nombreDeTablas.forEach { result ->
                var tabla = ""
                when (result) {
                    is String -> tabla = result
                    is Array<*> -> tabla = result[0].toString()
                }
                session.createNativeQuery("truncate table $tabla").executeUpdate()
            }
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate()
        }
    }
}