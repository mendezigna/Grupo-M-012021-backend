package unq.edu.ar.grupoMs12021;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import unq.edu.ar.grupoMs12021.GrupoMs12021App;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GrupoMs12021App.class);
	}

}
