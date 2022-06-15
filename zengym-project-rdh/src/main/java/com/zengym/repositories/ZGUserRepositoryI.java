package com.zengym.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zengym.persistence.ZGUser;

/**
 * Zen Gym
 * 
 * Repositorio de usuarios
 * 
 * @author Raquel DH
 *
 */
@Repository
public interface ZGUserRepositoryI extends JpaRepository<ZGUser, Long> {

	/**
	 * Verificación de login.
	 * 
	 * @param mail
	 * @param password
	 * @return ZGUser logueado
	 */
	public ZGUser findByUsermailAndPassword(final String mail, final String password);

	/**
	 * Obtención de la lista de profesionales.
	 * 
	 * @param isProfessional
	 * @return List<ZGUser>
	 */
	public List<ZGUser> findByIsProfessional(final Boolean isProfessional);

	/**
	 * Verifica la existencia de otro usuario con mismo email.
	 * 
	 * @param email
	 * @return int
	 */
	public int countByUsermail(final String email);

}
