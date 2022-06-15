package com.zengym.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zengym.persistence.ZGAppointment;
import com.zengym.persistence.ZGUser;
import com.zengym.repositories.ZGApptRepositoryI;

/**
 * Zen Gym
 * 
 * Servicio de gestión de usuarios
 * 
 * @author Raquel DH
 *
 */
@Service
public class ZGApptsServiceImpl implements ZGApptsServiceI {

	/** Inyeccion: repositorio citas */
	@Autowired
	private ZGApptRepositoryI apptRepo;

	@Override
	public String checkIfNewApptIsPosible(final ZGUser cus, final ZGUser prof, final Calendar date) {

		// Resultado
		String msgResult = "¡Cita generada!";

		// Numero de citas en fecha para profesional
		final int numApptsProfessional = apptRepo.countByDateAndProfessional(date, prof);

		// Verifica si no existen.
		if (numApptsProfessional == 0) {

			// Numero de citas en fecha para cliente.
			final int numApptsCustomer = apptRepo.countByDateAndCustomer(date, cus);

			// Verifica si no existen.
			if (numApptsCustomer > 0) {

				// Mensaje de error por duplicidad de citas para profesional.
				msgResult = "Usted ya posee otra cita en la fecha indicada.";
			}

		} else {

			// Mensaje de error por duplicidad de citas para profesional.
			msgResult = "Ya existen citas para dicho profesional en la fecha indicada.";
		}

		return msgResult;
	}

	@Override
	public ZGAppointment newAppt(final ZGAppointment newAppt) {
		return apptRepo.save(newAppt);
	}

	@Override
	public List<ZGAppointment> getApptsByUser(final ZGUser user) {
		return apptRepo.findByCustomerOrProfessional(user, user);
	}

	@Override
	public void deleteById(final Long apptId) {

		// Borra la cita.
		apptRepo.deleteById(apptId);

	}
}
