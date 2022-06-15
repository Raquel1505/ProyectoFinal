package com.zengym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zengym.persistence.ZGClass;
import com.zengym.persistence.ZGUser;

/**
 * Zen Gym
 * 
 * Repositorio de clases
 * 
 * @author Raquel DH
 *
 */
@Repository
public interface ZGClassRepositoryI extends JpaRepository<ZGClass, Long> {

	/**
	 * Obtencion del numero de clases para un cliente en una fecha.
	 * 
	 * @param dayHour
	 * @param cust
	 * @return int
	 */
	public int countByDayHourAndUsers(final String dayHour, final ZGUser cust);

}
