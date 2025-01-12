package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.security.domain.PerfilTipo;
import com.example.security.service.UsuarioService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
	private static final String MEDICO = PerfilTipo.MEDICO.getDesc();
	private static final String PACIENTE = PerfilTipo.PACIENTE.getDesc();
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				// liberando acesso as seguintes urls
				.antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/u/novo/cadastro", "/u/cadastro/realizado", "/u/cadastro/paciente/salvar").permitAll()
				.antMatchers("/u/confirmacao/cadastro").permitAll()
				.antMatchers("/u/p/**").permitAll()
				
				// acessos privados admin
				.antMatchers("/u/editar/senha","/u/confirmar/senha").hasAnyAuthority(MEDICO, PACIENTE)
				.antMatchers("/u/**").hasAuthority(ADMIN)
				
				// acessos privados medicos
				.antMatchers("/medicos/especialidade/titulo/*").hasAnyAuthority(MEDICO, PACIENTE)
				.antMatchers("/medicos/dados", "/medicos/salvar", "/medicos/editar").hasAnyAuthority(MEDICO, ADMIN)
				.antMatchers("/medicos/**").hasAuthority(MEDICO)
				
				// acessos privados pacientes
				.antMatchers("/pacientes/**").hasAuthority(PACIENTE)
				
				// acessos privados especialidades
				.antMatchers("/especialidades/datatables/server/medico/*").hasAnyAuthority(ADMIN, MEDICO)
				.antMatchers("/especialidades/titulo").hasAnyAuthority(ADMIN, MEDICO, PACIENTE)
				.antMatchers("/especialidades/**").hasAuthority(ADMIN)
				
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.failureUrl("/login-error")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/acesso-negado")
			.and()
				.rememberMe();
		
		
		http.sessionManagement()
			// quantidade de dispositivos que sera mantido o login 
			.maximumSessions(1)
			// so manterar o login em um unico dispositivo
			.maxSessionsPreventsLogin(true)
			.sessionRegistry(sessionRegistry());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public SessionRegistryImpl sessionRegistry() {
		
		return new SessionRegistryImpl();
	}
	
	@Bean
	public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {
		
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}
	
}




