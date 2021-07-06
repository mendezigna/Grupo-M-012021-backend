package unq.edu.ar.GrupoMs12021.Resenia.webservice.config

import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter : OncePerRequestFilter() {
    private val HEADER = "Authorization"
    private val PREFIX = "Bearer "
    private val SECRET = "secretKeyMuySecreta"

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            if (existeJWTToken(request, response)) {
                val claims = validateToken(request)
                if (claims["authorities"] != null) {
                    setUpSpringAuthentication(claims)
                } else {
                    SecurityContextHolder.clearContext()
                }
            } else {
                SecurityContextHolder.clearContext()
            }
            chain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
            return
        } catch (e: UnsupportedJwtException) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
            return
        } catch (e: MalformedJwtException) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
            return
        }
    }

    private fun validateToken(request: HttpServletRequest): Claims {
        val jwtToken = request.getHeader(HEADER).replace(PREFIX, "")
        return Jwts.parser().setSigningKey(SECRET.toByteArray()).parseClaimsJws(jwtToken).body
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     *
     * @param claims
     */
    private fun setUpSpringAuthentication(claims: Claims) {
        val authorities: List<String>? = claims["authorities"] as List<String>?
        val auth = UsernamePasswordAuthenticationToken(claims.subject, null,
            authorities!!.stream().map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }.collect(Collectors.toList())
        )
        SecurityContextHolder.getContext().authentication = auth
    }

    private fun existeJWTToken(request: HttpServletRequest, res: HttpServletResponse): Boolean {
        val authenticationHeader = request.getHeader(HEADER)
        return !(authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
    }
    companion object{
        fun getJWTToken(username: String): String {
            val secretKey = "secretKeyMuySecreta"
            val grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER")
            val token: String = Jwts
                .builder()
                .setId("JWT")
                .setSubject(username)
                .claim("authorities",
                    grantedAuthorities.stream()
                        .map { obj: GrantedAuthority -> obj.authority }
                        .collect(Collectors.toList()))
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 600000))
                .signWith(
                    SignatureAlgorithm.HS512,
                    secretKey.toByteArray()
                ).compact()
            return "Bearer $token"
        }
    }

}