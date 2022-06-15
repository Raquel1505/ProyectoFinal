package com.zengym.services;

import java.util.Calendar;
import java.util.List;

import com.zengym.persistence.ZGAppointment;
import com.zengym.persistence.ZGUser;

/**
 * Zen Gym
 * 
 * Servicio de gestión de citas
 * 
 * @author Raquel DH
 *
 */
public interface ZGApptsServiceI {

	/**
	 * Verifica si es posible una cita en fecha concreta.
	 * 
	 * @param cus
	 * @param prof
	 * @param date
	 * @return String
	 */
	public String checkIfNewApptIsPosible(final ZGUser cus, final ZGUser prof, final Calendar date);

	/**
	 * Genera nueva cita.
	 * 
	 * @param newAppt
	 * @return ZGAppointment
	 */
	public ZGAppointment newAppt(final ZGAppointment newAppt);

	/**
	 * Obtiene las citas nutricionales de un usuario.
	 * 
	 * @param user
	 * @return List<ZGAppointment>
	 */
	public List<ZGAppointment> getApptsByUser(final ZGUser user);

	/**
	 * Elimina citas a través del ID.
	 * 
	 * @param apptId
	 */
	public void deleteById(final Long apptId);
}
