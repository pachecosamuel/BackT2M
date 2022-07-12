package org.serratec.sistemacompetenciaback.services;

import java.util.Date;

import org.serratec.sistemacompetenciaback.dto.UsuarioDTO;
import org.serratec.sistemacompetenciaback.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEMailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario,String senha) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario,senha);
		sendEmail(sm);
	}
	@Override
	public void sendDados(UsuarioDTO usuario) {
		SimpleMailMessage sm = prepareDados(usuario);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String senha) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + senha);
		return sm;
	}
	
	protected SimpleMailMessage prepareDados(UsuarioDTO usuario) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Dados de cadastro");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Olá " +usuario.getNome()+
				", Seu acesso ao sistema de competências já esta disponível, seguem abaixo os seus dados "+"\n" 
				+"login: " + usuario.getLogin()+"\n"
				+"senha: "+usuario.getSenha());
		return sm;
	}

}
