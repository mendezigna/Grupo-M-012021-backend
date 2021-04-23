package unq.edu.ar.GrupoMs12021.Resenia.model.title

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Cast

class CastTest {


    @Test
    fun setTitleTest(){
        val cast = Cast()
        val title = Title.createAnyTitle()
        cast.setTitle(title)

        assertEquals(title, cast.getTitle())
    }

    @Test
    fun createCastTest(){
        val cast = Cast("Director", listOf(), listOf())

        assertEquals(cast.director, "Director")
        assertTrue(cast.actors.isEmpty())
        assertTrue(cast.employees.isEmpty())
    }
}