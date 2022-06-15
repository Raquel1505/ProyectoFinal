package com.zengym.persistence;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Zen Gym
 * 
 * Entidaded de usuarios
 * 
 * @author Raquel DH
 *
 */
@Entity
@Table(name = "T_USER")
public class ZGUser implements Serializable {

	/** SERIAL */
	private static final long serialVersionUID = 1L;

	/** ID (PK) */
	private Long userId;

	/** Email de usuario */
	private String usermail;

	/** Contraseña */
	private String password;

	/** Nombre de la persona */
	private String name;

	/** Tipo de usuario (0 - User / 1 - Professional) */
	private boolean isProfessional;

	/** Lista de citas (cliente) */
	private List<ZGAppointment> custAppts;

	/** Lista de citas (profesional) */
	private List<ZGAppointment> profeAppts;

	/** Lista de clases */
	private List<ZGClass> classes;

	/**
	 * @return the userId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "C_USER_ID")
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the usermail
	 */
	@Column(name = "C_USERMAIL", unique = true, nullable = false)
	public String getUsermail() {
		return usermail;
	}

	/**
	 * @param usermail the usermail to set
	 */
	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

	/**
	 * @return the password
	 */
	@Column(name = "C_PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	@Column(name = "C_NAME", nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the isProfessional
	 */
	@Column(name = "C_IS_PROFESSIONAL", nullable = false)
	public boolean getIsProfessional() {
		return isProfessional;
	}

	/**
	 * @param isProfessional the isProfessional to set
	 */
	public void setIsProfessional(boolean isProfessional) {
		this.isProfessional = isProfessional;
	}

	/**
	 * @return the custAppts
	 */
	@OneToMany(mappedBy = "customer")
	public List<ZGAppointment> getCustAppts() {
		return custAppts;
	}

	/**
	 * @param custAppts the custAppts to set
	 */
	public void setCustAppts(List<ZGAppointment> custAppts) {
		this.custAppts = custAppts;
	}

	/**
	 * @return the profeAppts
	 */
	@OneToMany(mappedBy = "professional")
	public List<ZGAppointment> getProfeAppts() {
		return profeAppts;
	}

	/**
	 * @param profeAppts the profeAppts to set
	 */
	public void setProfeAppts(List<ZGAppointment> profeAppts) {
		this.profeAppts = profeAppts;
	}

	/**
	 * @return the classes
	 */
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	public List<ZGClass> getClasses() {
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(List<ZGClass> classes) {
		this.classes = classes;
	}

	/**
	 * Elimina una clase asociada.
	 * 
	 * @param classR
	 */
	public void removeClass(final ZGClass classR) {

		// Elimina la clase del usuario y al usuario de la clase.
		classes.remove(classR);
		classR.getUsers().remove(this);
	}
}
