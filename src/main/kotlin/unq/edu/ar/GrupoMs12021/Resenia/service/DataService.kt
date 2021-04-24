package unq.edu.ar.GrupoMs12021.Resenia.service

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Genres
import unq.edu.ar.GrupoMs12021.Resenia.model.title.Title
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleBasicInformation
import unq.edu.ar.GrupoMs12021.Resenia.model.title.TitleType
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Actor
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Category
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Employee
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
@Service
class DataService(private val titleDAO : TitleDAO) : ApplicationRunner{

    val cast1 = Cast("Da Vinky", listOf(Actor("Carmencita", listOf("Carmencita"))), listOf(Employee("Jose", Category.CINEMATOGRAPHER)))

    val title1: Title = Title.createTitleWith("tt0000001", TitleBasicInformation(TitleType.SHORT, "Carmecita", 1894, null, 1, listOf(Genres.DOCUMENTARY, Genres.SHORT)), listOf(), cast1)
    val title2: Title = Title.createTitleWith("tt0372140", TitleBasicInformation(TitleType.MOVIE, "American Beer", 2004, null, 105, listOf(Genres.DOCUMENTARY)), listOf(), Cast())
    val title3: Title = Title.createTitleWith("tt0367275",TitleBasicInformation(TitleType.TVSERIES, "All About You", 1874, null, 15, listOf(Genres.DOCUMENTARY, Genres.FAMILY)), listOf(), Cast())
    val title4: Title = Title.createTitleWith("tt0367279",TitleBasicInformation(TitleType.TVSERIES, "Arrested Development", 2003, 2019, 22, listOf(Genres.COMEDY)), listOf(), Cast())


    override fun run(args: ApplicationArguments?) {
        val titles = listOf(title1,title2, title3,title4)
        titleDAO.saveAll(titles)
    }
}