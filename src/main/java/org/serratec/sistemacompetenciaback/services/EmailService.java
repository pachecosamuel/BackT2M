package org.serratec.sistemacompetenciaback.services;

import org.serratec.sistemacompetenciaback.dto.UsuarioDTO;
import org.serratec.sistemacompetenciaback.entity.Usuario;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Usuario usuario,String senha);
	void sendDados(UsuarioDTO usuario);

}
