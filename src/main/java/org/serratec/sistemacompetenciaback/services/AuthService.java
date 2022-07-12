package org.serratec.sistemacompetenciaback.services;

import java.util.Random;

import org.serratec.sistemacompetenciaback.entity.Usuario;
import org.serratec.sistemacompetenciaback.repositories.UsuarioRepository;
import org.serratec.sistemacompetenciaback.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Autowired
	private UsuarioRepository usariorepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailservice;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Usuario usuario = usariorepository.findByEmail(email);
		
		System.out.println(usuario);
		
		if(usuario == null) {
			throw new ObjectNotFoundException("email não encontrado");
		}
		
		String novaSenha = novaSenha();
		usuario.setSenha(pe.encode(novaSenha));
		usariorepository.save(usuario);
		emailservice.sendNewPasswordEmail(usuario,novaSenha);
		
	}

	private String novaSenha() {
		char[]vet = new char [10];
		for(int i=0;i<10;i++) {
			vet[i]=ramdomChar();
		}
		return new String(vet);
	}

	private char ramdomChar() {
		int opt = rand.nextInt(3);
		
		if(opt == 0) {
			return(char)(rand.nextInt(10)+48);
		}
		if(opt == 1) {
			return(char)(rand.nextInt(26)+65);
		}
		else {
			return(char)(rand.nextInt(26)+97);
		}
	}

}
