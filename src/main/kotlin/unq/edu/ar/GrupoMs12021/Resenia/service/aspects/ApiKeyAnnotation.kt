package unq.edu.ar.GrupoMs12021.Resenia.service.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import unq.edu.ar.GrupoMs12021.Resenia.persistence.dao.ClientDAO
import java.lang.RuntimeException
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
        val apiKey = request.getHeader("ApiKey")?.replace("Token ", "")
        if(apiKey == null || clientDAO.findByApyKey(apiKey!!).isEmpty){
            respondError("Please provide a valid Api Key")
        }

    }

    private fun respondError(error : String){
        respond.sendError(401, error)
    }
}

@Target(AnnotationTarget.FUNCTION)
annotation class ApiKey
