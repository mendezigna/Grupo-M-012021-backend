package unq.edu.ar.GrupoMs12021.Resenia.model.title

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast

class TitleUnitTest{


    @BeforeEach
    fun setUP(){
        //DataService().createInitialData()
    }

    @Test
    fun getGenresOfTitleTest(){
        val genres = listOf(Genres.COMEDY, Genres.SHORT)
        val title = Title.createTitleWith(titleBasicInformation = TitleBasicInformation(genres = genres))

        assertEquals(genres, title.getGeneros())
    }

    @Test
    fun setGenresOfTitleTest(){
        val genres = listOf(Genres.COMEDY, Genres.SHORT)
        val title = Title.createAnyTitle()
        title.setGeneross(genres)

        assertEquals(genres, title.getGeneros())
    }

    @Test
    fun createTitleTest(){
        val titleBasicInformation = TitleBasicInformation(titleType = TitleType.MOVIE, name = "Title", startYear = 2000, endYear = 2001, runtimeMinutes = 60,
            listOf(Genres.ACTION))
        val cast = Cast()
        val title = Title.createTitleWith(titleId = "tt000002", titleBasicInformation = titleBasicInformation, reviews = listOf(), cast = cast)

        assertEquals(title.titleId, "tt000002")
        assertEquals(title.titleType, TitleType.MOVIE.name)
        assertEquals(title.name, "Title")
        assertEquals(title.startYear, 2000)
        assertEquals(title.runtimeMinutes, 60)

        assertEquals(title.cast, cast)
        assertTrue(title.reviews.isEmpty())
    }
}