package unq.edu.ar.GrupoMs12021.Resenia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ReseniaApplication

fun main(args: Array<String>) {
	runApplication<ReseniaApplication>(*args)
}
