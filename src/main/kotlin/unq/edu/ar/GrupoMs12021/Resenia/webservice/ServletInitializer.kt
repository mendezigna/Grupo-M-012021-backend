package unq.edu.ar.GrupoMs12021.Resenia.webservice

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(ReseniaApplication::class.java)
	}

}
