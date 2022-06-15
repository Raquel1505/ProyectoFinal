package com.zengym.repositories;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zengym.persistence.ZGAppointment;
import com.zengym.persistence.ZGUser;

/**
 * Zen Gym
 * 
 * Repositorio de citas
 * 
 * @author Raquel DH
 *
 */
@Repository
public interface ZGApptRepositoryI extends JpaRepository<ZGAppointment, Long> {

	/**
	 * Obtencion del numero de citas para un cliente en una fecha.
	 * 
	 * @param date
	 * @param customer
	 * @return int
	 */
	public int countByDateAndCustomer(final Calendar date, final ZGUser customer);

	/**
	 * Obtencion del numero de citas para un cliente en una fecha.
	 * 
	 * @param date
	 * @param professional
	 * @return int
	 */
	public int countByDateAndProfessional(final Calendar date, final ZGUser professional);

	/**
	 * Obtencion del numero de citas para un cliente / profesional en una fecha.
	 * 
	 * @param date
	 * @param cust
	 * @param prof
	 * @return int
	 */
	public int countByDateAndCustomerOrProfessional(final Calendar date, final ZGUser cust, final ZGUser prof);

	/**
	 * Obtencion de citas asociadas asociadas a un usuario.
	 * 
	 * @param cust
	 * @param prof
	 * @return List<ZGAppointment>
	 */
	public List<ZGAppointment> findByCustomerOrProfessional(final ZGUser cust, final ZGUser prof);
}
