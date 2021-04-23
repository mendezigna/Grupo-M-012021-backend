package unq.edu.ar.GrupoMs12021.Resenia.model.title

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Category
import unq.edu.ar.GrupoMs12021.Resenia.model.title.cast.Employee

class EmployeeTest {

    @Test
    fun createEmployeeTest(){
        val employee = Employee(name = "Empleado", category = Category.PRODUCER)

        assertEquals(employee.name, "Empleado")
        assertEquals(employee.category, Category.PRODUCER.name)
    }

}