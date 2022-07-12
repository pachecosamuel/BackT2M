package org.serratec.sistemacompetenciaback.config;

import java.util.Arrays;

import org.serratec.sistemacompetenciaback.security.JWTAuthenticationFilter;
import org.serratec.sistemacompetenciaback.security.JWTAuthorizationFilter;
import org.serratec.sistemacompetenciaback.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/* Classe que configra qual rota sera bloqueada pelo spring security*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	private static final String[] PUBLIC_ROUTERS = {
			/* AQUI FICAM AS ROTAS PUBLICAS */
			"/usuarios",
			"/auth/forgot/**"};

	@Override
	public void configure(WebSecurity web) throws Exception {
	 web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
	"/swagger-ui.html", "/webjars/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf()
				.disable(); /* aplica as confiurações de cors configuration se a mesma existir na classe */
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_ROUTERS).permitAll() /*
																							 * permite todas as rotas
																							 * que estão em
																							 * PUBLIC_ROUTERS
																							 */
				.anyRequest().authenticated(); /* para as outras que não estão em PUBLIC_ROUTERS exige autenticação */
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}/* configura que pode buscar user por login */

	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() { /* algoritimo de enconder de senha */
		return new BCryptPasswordEncoder();

	}

}