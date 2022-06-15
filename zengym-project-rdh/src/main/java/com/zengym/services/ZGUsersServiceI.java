package com.zengym.services;

import java.util.List;
import java.util.Optional;

import com.zengym.persistence.ZGUser;

/**
 * Zen Gym
 * 
 * Servicio de gestión de usuarios
 * 
 * @author Raquel DH
 *
 */
public interface ZGUsersServiceI {

	/**
	 * Genera nuevos clientes en caso de que no existan.
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return String
	 */
	public String createNewCustomer(final String name, final String email, final String password);

	/**
	 * Método para verificacion de login.
	 * 
	 * @param mail
	 * @param password
	 * @return ZGUser
	 */
	public ZGUser verifyLogin(final String mail, final String password);

	/**
	 * Obtencion del listado de profesionales.
	 * 
	 * @return List<ZGUser>
	 */
	public List<ZGUser> getProfessionals();

	/**
	 * Obtencion de usuario por ID.
	 * 
	 * @param userId
	 * @return Optional<ZGUser>
	 */
	public Optional<ZGUser> getUserById(final Long userId);

	/**
	 * Actualiza el usuario.
	 * 
	 * @param user
	 */
	public void updateUser(final ZGUser user);

}
