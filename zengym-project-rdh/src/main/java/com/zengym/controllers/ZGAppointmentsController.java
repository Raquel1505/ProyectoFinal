package com.zengym.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import com.zengym.persistence.ZGAppointment;
import com.zengym.persistence.ZGUser;
import com.zengym.services.ZGApptsServiceI;
import com.zengym.services.ZGUsersServiceI;

import jakarta.servlet.http.HttpSession;

/**
 * Zen Gym
 * 
 * Controlador de citas alimenticias
 * 
 * @author Raquel DH
 *
 */
@Controller
@RequestMapping
public class ZGAppointmentsController {

	/** Inyeccion: servicio de gestion de citas */
	@Autowired
	private ZGApptsServiceI apptsService;

	/** Inyeccion: servicio de gestion de usuarios */
	@Autowired
	private ZGUsersServiceI usersService;

	/**
	 * Genera, de ser posible, nueva cita.
	 * 
	 * @param session
	 * @param model
	 * @param profeId
	 * @param newDate
	 * @param newTime
	 * @param newDesc
	 * @return String
	 * @throws ParseException
	 */
	@PostMapping("/cita")
	public String newAppointment(HttpSession session, Model model, final Long profeId, final String newDate,
			final String newTime, final String newDesc) throws ParseException {

		// Obtencion de usuario logueado.
		final ZGUser loggedUser = (ZGUser) session.getAttribute("userLogged");

		// Mensaje resultante.
		String checkApptResultMsg = null;

		if (loggedUser != null) {

			// Conversion de fecha obtenida de formulario.
			final Calendar cal = Calendar.getInstance();
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(newDate));
			cal.set(Calendar.HOUR, Integer.valueOf(StringUtils.substringBefore(newTime, ":")));
			cal.set(Calendar.MINUTE, Integer.valueOf(StringUtils.substringAfter(newTime, ":")));
			cal.clear(Calendar.SECOND);
			cal.clear(Calendar.MILLISECOND);

			// Profesional seleccionado.
			final ZGUser professional = new ZGUser();
			professional.setUserId(profeId);

			// Verificacion de existencia de citas en misma fecha.
			checkApptResultMsg = apptsService.checkIfNewApptIsPosible(loggedUser, professional, cal);

			if ("¡Cita generada!".equals(checkApptResultMsg)) {

				// Nueva cita.
				final ZGAppointment newAppt = new ZGAppointment();
				newAppt.setDate(cal);
				newAppt.setDescription(newDesc);
				newAppt.setCustomer(loggedUser);
				newAppt.setProfessional(professional);

				// Generacion de nueva cita.
				apptsService.newAppt(newAppt);
			}
		}

		// Mensaje resultante al coger cita.
		model.addAttribute("apptResultMsgB", Boolean.FALSE);
		model.addAttribute("apptResultMsg", checkApptResultMsg);

		// Obtencion de profesionales.
		final List<ZGUser> professionals = usersService.getProfessionals();

		// Guardado de profesionales.
		model.addAttribute("professionals", professionals);

		return "newAppt.html";
	}
	
	/**
	 * Elimina una cita por ID.
	 * 
	 * @param apptId
	 * @return String
	 */
	@PostMapping("/actDropAppt")
	public String deleteCustomer(final String apptId) {

		// Se elimina el cliente por ID.
		apptsService.deleteById(Long.valueOf(apptId));

		return "redirect:/miscitas";
	}

}
