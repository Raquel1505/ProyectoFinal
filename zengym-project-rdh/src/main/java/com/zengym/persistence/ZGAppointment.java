package com.zengym.persistence;

import java.io.Serializable;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Zen Gym
 * 
 * Entidad de citas
 * 
 * @author Raquel DH
 *
 */
@Entity
@Table(name = "T_APPOINTMENT")
public class ZGAppointment implements Serializable {

	/** SERIAL */
	private static final long serialVersionUID = 1L;

	/** ID (PK) */
	private Long appointmentId;

	/** Fecha de la cita */
	private Calendar date;

	/** Descripción de la cita */
	private String description;

	/** Usuario cliente */
	private ZGUser customer;

	/** Usuario professional */
	private ZGUser professional;

	/**
	 * @return the appointmentId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "C_APPOINTMENT_ID")
	public Long getAppointmentId() {
		return appointmentId;
	}

	/**
	 * @param appointmentId the appointmentId to set
	 */
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	/**
	 * @return the date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "C_DATE")
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the description
	 */
	@Column(name = "C_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the customer
	 */
	@ManyToOne
	@JoinColumn(name = "C_U_CUSTOMER_ID", nullable = false)
	public ZGUser getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(ZGUser customer) {
		this.customer = customer;
	}

	/**
	 * @return the professional
	 */
	@ManyToOne
	@JoinColumn(name = "C_U_PROFESSIONAL_ID", nullable = false)
	public ZGUser getProfessional() {
		return professional;
	}

	/**
	 * @param professional the professional to set
	 */
	public void setProfessional(ZGUser professional) {
		this.professional = professional;
	}

}
