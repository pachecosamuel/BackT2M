package org.serratec.sistemacompetenciaback.services;

import java.util.Optional;

import org.serratec.sistemacompetenciaback.entity.Usuario;
import org.serratec.sistemacompetenciaback.repositories.UsuarioRepository;
import org.serratec.sistemacompetenciaback.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = repo.findByLogin(username);
		
		if(user.get() == null) {
			throw new UsernameNotFoundException(null);
		}
		
		return new UserSS(user.get().getId(),user.get().getLogin(),user.get().getSenha(),user.get().getPerfil());
	}

}
