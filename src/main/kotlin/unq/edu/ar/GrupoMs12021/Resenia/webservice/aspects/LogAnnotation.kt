package unq.edu.ar.GrupoMs12021.Resenia.webservice.aspects

import org.aspectj.lang.ProceedingJoinPoint

import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Aspect
@Component
class LogExecutionTimeAspectAnnotation {

    var logger: Logger = LoggerFactory.getLogger(LogExecutionTimeAspectAnnotation::class.java)

    /// ANNOTATION POINTCUT////
    @Around("@annotation(Log)")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        logger.info("Method: " + joinPoint.signature.toShortString())
        logger.info("Args: " + joinPoint.args.map { it.toString() })
        val start = System.currentTimeMillis()
        val result = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - start
        logger.info("/////// " + joinPoint.signature + " executed in " + executionTime + "ms")
        return result
    }

}


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Log
