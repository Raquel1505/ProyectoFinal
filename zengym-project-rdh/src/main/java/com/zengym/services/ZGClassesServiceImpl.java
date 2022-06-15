package com.zengym.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zengym.persistence.ZGClass;
import com.zengym.persistence.ZGUser;
import com.zengym.repositories.ZGClassRepositoryI;

/**
 * Zen Gym
 * 
 * Servicio de gestión de clases
 * 
 * @author Raquel DH
 *
 */
@Service
public class ZGClassesServiceImpl implements ZGClassesServiceI {

	/** Inyeccion: repositorio clases */
	@Autowired
	private ZGClassRepositoryI classesRepo;

	@Override
	public List<ZGClass> getClasses() {

		// Recuperacion y retorno de clases.
		return classesRepo.findAll();
	}

	@Override
	public Optional<ZGClass> getClassById(final Long classId) {

		// Recuperacion y retorno de clase por ID.
		return classesRepo.findById(classId);
	}

	@Override
	public String inscribeToClass(final ZGUser user, final Long classId) {

		// Resultado.
		String msgResult = "¡Inscripción hecha!";

		// Obtencion de clase por ID.
		final Optional<ZGClass> selectedClass = getClassById(classId);

		// Verificacion de integridad.
		if (!selectedClass.isEmpty()) {

			// Obtencion de numero de clases en la misma fecha que la clase.
			final int numClasses = classesRepo.countByDayHourAndUsers(selectedClass.get().getDayHour(), user);

			if (numClasses == 0) {

				selectedClass.get().getUsers().add(user);
				classesRepo.save(selectedClass.get());

			} else {
				msgResult = "Ya tienes una clase en esa fecha.";
			}

		}

		return msgResult;
	}

	@Override
	public void saveOrUpdateClass(final ZGClass classToSave) {
		// Actualizacion o guardado.
		classesRepo.save(classToSave);
	}

	@Override
	public void deleteClass(final ZGClass classToRemove) {
		// Eliminacion de clase.
		classesRepo.delete(classToRemove);
	}

}
