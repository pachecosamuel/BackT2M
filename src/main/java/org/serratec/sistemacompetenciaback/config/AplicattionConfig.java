package org.serratec.sistemacompetenciaback.config;

import org.serratec.sistemacompetenciaback.services.EmailService;
import org.serratec.sistemacompetenciaback.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AplicattionConfig {

	
	@Bean
	public EmailService emailservice() {
		return new SmtpEmailService();
	}
}
