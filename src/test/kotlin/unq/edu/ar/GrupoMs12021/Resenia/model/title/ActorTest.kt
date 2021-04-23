package unq.edu.ar.GrupoMs12021.Resenia.model.title

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Actor

class ActorTest {

    @Test
    fun getCharacterTest(){
        val characters = listOf("Peppa Pig", "Maria Juana", "Tu vieja")
        val actor = Actor(characters = characters)

        assertEquals(characters, actor.getCharacters())
    }

    @Test
    fun setCharacterTest(){
        val characters = listOf("Peppa Pig", "Maria Juana", "Tu vieja")
        val actor = Actor()
        actor.setCharacters(characters)
        assertEquals(characters, actor.getCharacters())
    }

    @Test
    fun createActorTest(){
        val characters = listOf("Peppa Pig")

        val actor = Actor(name = "Peppa", characters = characters)

        assertEquals(actor.name, "Peppa")
        assertEquals(actor.getCharacters(), characters)
    }
}