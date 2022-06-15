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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Zen Gym
 * 
 * Entidad de clases
 * 
 * @author Raquel DH
 *
 */
@Entity
@Table(name = "T_CLASS")
public class ZGClass implements Serializable {

	/** SERIAL */
	private static final long serialVersionUID = 1L;

	/** ID (PK) */
	private Long classId;

	/** Nombre de monitor/a */
	private String instructor;

	/** Día / hora */
	private String dayHour;

	/** Descripción de la clase */
	private String description;

	/** Usuarios cliente */
	private List<ZGUser> users;

	/**
	 * @return the classId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "C_CLASS_ID")
	public Long getClassId() {
		return classId;
	}

	/**
	 * @param classId the classId to set
	 */
	public void setClassId(Long classId) {
		this.classId = classId;
	}

	/**
	 * @return the instructor
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	/**
	 * @return the dayHour
	 */
	@Column(name = "C_DAY_HOUR")
	public String getDayHour() {
		return dayHour;
	}

	/**
	 * @param dayHour the dayHour to set
	 */
	public void setDayHour(String dayHour) {
		this.dayHour = dayHour;
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
	 * @return the users
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "T_CUSTOMER_CLASS", joinColumns = @JoinColumn(name = "C_CLASS_ID"), inverseJoinColumns = @JoinColumn(name = "C_CUSTOMER_ID"))
	public List<ZGUser> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<ZGUser> users) {
		this.users = users;
	}

}
