package com.zengym.services;

import java.util.List;
import java.util.Optional;

import com.zengym.persistence.ZGClass;
import com.zengym.persistence.ZGUser;

/**
 * Zen Gym
 * 
 * Servicio de gestión de clases
 * 
 * @author Raquel DH
 *
 */
public interface ZGClassesServiceI {

	/**
	 * Obtencion del listado de clases.
	 * 
	 * @return List<ZGClass>
	 */
	public List<ZGClass> getClasses();

	/**
	 * Obtencion de clase por ID.
	 * 
	 * @param classId
	 * @return Optional<ZGClass>
	 */
	public Optional<ZGClass> getClassById(final Long classId);

	/**
	 * Inscribe a un usuario en una clase.
	 * 
	 * @param user
	 * @param classId
	 * @return String
	 */
	public String inscribeToClass(final ZGUser user, final Long classId);

	/**
	 * Actualiza o crea una clase.
	 * 
	 * @param classToSave
	 */
	public void saveOrUpdateClass(final ZGClass classToSave);

	/**
	 * Elimina una clase.
	 * 
	 * @param classToRemove
	 */
	public void deleteClass(final ZGClass classToRemove);

}
