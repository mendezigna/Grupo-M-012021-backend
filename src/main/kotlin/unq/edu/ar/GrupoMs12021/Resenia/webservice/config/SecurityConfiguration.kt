package unq.edu.ar.GrupoMs12021.Resenia.webservice.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST,  "/client/login", "/client/register", "/review/**", "/title/**").permitAll()
            .antMatchers(HttpMethod.GET, "/review/**", "/title/**").permitAll()
            .anyRequest()
            .authenticated()
    }

}