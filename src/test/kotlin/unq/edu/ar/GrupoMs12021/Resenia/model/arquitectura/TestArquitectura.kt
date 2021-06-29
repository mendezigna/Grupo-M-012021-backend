package unq.edu.ar.GrupoMs12021.Resenia.model.arquitectura

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods
import org.junit.jupiter.api.Test
import org.springframework.web.bind.annotation.RestController
import unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects.Log


class TestArquitectura {

    /* todos los metodos de las clases en el paquete controller tienen que tener la anotacion de @Log */
    @Test
    fun verificarMetodosController() {
        val importedClasses : JavaClasses = ClassFileImporter().importPackages("unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers")
        val regla = methods().should().beAnnotatedWith(Log::class.java)
        regla.check(importedClasses)
    }

    /* todos las clases en el paquete controller tienen que tener la anotacion de @RestController */
    @Test
    fun verificarControllers() {
        val importedClasses : JavaClasses = ClassFileImporter().importPackages("unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers")
        val regla = classes().should().beAnnotatedWith(RestController::class.java)
        regla.check(importedClasses)
    }
}