package unq.edu.ar.GrupoMs12021.Resenia.model

import helpers.DataService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TitleUnitTest{

    private val dataService: DataService = DataService()

    @BeforeEach
    fun setUP(){
        dataService.createInitialData()
    }

    @Test
    fun emptyTest(){
        print("Hi")
    }
}