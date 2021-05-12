package unq.edu.ar.GrupoMs12021.Resenia.webservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun apiDocket(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("unq.edu.ar.GrupoMs12021.Resenia.webservice.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
    }

    private fun getApiInfo(): ApiInfo? {
        return ApiInfo(
                "Reviews Service API",
                "Re-seña!",
                "1.0",
                "",
                Contact("Desapp-GrupoMs12021", "https://github.com/mendezigna/Grupo-M-012021-backend", ""),
                "No License",
                "",
                Collections.emptyList()
        )
    }
}