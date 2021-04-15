package unq.edu.ar.GrupoMs12021.Resenia.webservice.Configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.TitleDAO
import unq.edu.ar.GrupoMs12021.Resenia.service.TitleService

@Configuration
class AppConfiguration {

    @Bean
    fun titleDAO() : TitleDAO {
        return TitleDAO()
    }

    @Bean
    fun titleService(titleDAO: TitleDAO) : TitleService {
        return TitleService(titleDAO)
    }
}