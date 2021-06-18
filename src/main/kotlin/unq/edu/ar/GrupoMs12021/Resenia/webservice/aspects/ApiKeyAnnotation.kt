package unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ClientDAO
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Aspect
@Component
class ApiKeyAnnotation(@Autowired private val clientDAO: ClientDAO){

    @Autowired
    lateinit var request: HttpServletRequest
    @Autowired
    lateinit var respond: HttpServletResponse

    @Before("@annotation(ApiKey)")
    fun checkApiKey(){
        val apiKey = request.getHeader("ApiKey")
        if(apiKey == null || clientDAO.findByApyKey(apiKey).isEmpty){
            respond.sendError(401, "Please provide a valid Api Key")
        }

    }
}

@Target(AnnotationTarget.FUNCTION)
annotation class ApiKey
