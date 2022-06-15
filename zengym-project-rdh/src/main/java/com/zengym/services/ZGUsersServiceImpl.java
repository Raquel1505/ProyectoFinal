package com.zengym.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zengym.persistence.ZGUser;
import com.zengym.repositories.ZGUserRepositoryI;

/**
 * Zen Gym
 * 
 * Servicio de gestión de usuarios
 * 
 * @author Raquel DH
 *
 */
@Service
public class ZGUsersServiceImpl implements ZGUsersServiceI {

	/** Inyeccion: repositorio usuarios */
	@Autowired
	private ZGUserRepositoryI usersRepo;

	@Override
	public ZGUser verifyLogin(final String mail, final String password) {

		// Usuario logueado.
		ZGUser loggedUser = null;

		// Verificacion de integridad.
		if (StringUtils.hasText(mail) && StringUtils.hasText(password)) {

			// Verificacion de login (obtencion de usuario logueado).
			loggedUser = usersRepo.findByUsermailAndPassword(mail, password);

		}

		return loggedUser;
	}

	@Override
	public List<ZGUser> getProfessionals() {

		// Obtencion y retorno de profesionales.
		return usersRepo.findByIsProfessional(Boolean.TRUE);
	}

	@Override
	public Optional<ZGUser> getUserById(final Long userId) {

		// Obtencion y retorno de usuario por ID.
		return usersRepo.findById(userId);
	}

	@Override
	public String createNewCustomer(final String name, final String email, final String password) {

		// Resultado.
		String msgResult = "¡Registro satisfactorio!";

		// Verifica número de usuarios con mismo email.
		final int numUsers = usersRepo.countByUsermail(email);
		if (numUsers == 0) {

			// Generación de nuevo cliente.
			final ZGUser newCust = new ZGUser();
			newCust.setName(name);
			newCust.setUsermail(email);
			newCust.setPassword(password);

			// Guardado del nuevo cliente.
			usersRepo.save(newCust);
		} else {
			msgResult = "[ERROR] El email ya está en uso por otro usuario.";
		}

		return msgResult;
	}
	
	@Override
	public void updateUser(final ZGUser user) {
		
		// Actualización del usuario.
		usersRepo.save(user);
	}

}
