package com.zengym.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengym.persistence.ZGUser;
import com.zengym.services.ZGUsersServiceI;

import jakarta.servlet.http.HttpSession;

/**
 * Zen Gym
 * 
 * Controlador principal
 * 
 * @author Raquel DH
 *
 */
@Controller
@RequestMapping
public class ZGHomeController {

	/** Inyeccion: servicio de gestion de usuarios */
	@Autowired
	private ZGUsersServiceI usersService;

	/**
	 * Capta cualquier solicitud y muestra el login.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/*")
	public String showLogin(Model model) {

		// Ocultación de mensaje de error en login.
		model.addAttribute("loginErrorMsgB", Boolean.TRUE);

		return "login.html";
	}

	/**
	 * Genera un nuevo registro de usuario.
	 * 
	 * @param model
	 * @param name
	 * @param surname
	 * @param email
	 * @param password
	 * @return String
	 */
	@PostMapping("/nuevoUsuario")
	public String newUser(Model model, final String name, final String surname, final String email,
			final String password) {

		// Concatenación nombres y apellidos.
		final String fullName = name + " " + surname;

		// Creación de usuario.
		final String msgResult = usersService.createNewCustomer(fullName, email, password);

		// Mensaje de registro.
		model.addAttribute("loginErrorMsgB", Boolean.FALSE);
		model.addAttribute("loginErrorMsg", msgResult);

		return "login.html";
	}

	/**
	 * Verificacion de login.
	 * 
	 * @param request
	 * @param model
	 * @param usermail
	 * @param password
	 * @return String
	 */
	@PostMapping("/home")
	public String checkLogin(HttpSession session, Model model, final String usermail, final String password) {

		// Resultado.
		String resultSite = "login.html";

		// Obtencion de usuario logueado.
		final ZGUser loggedUser = usersService.verifyLogin(usermail, password);

		// Verificacion de loggin correcto.
		if (loggedUser != null) {

			// Guardado de usuario logueado en el modelo.
			session.setAttribute("userLogged", loggedUser);

			// Navegacion hacia home.
			resultSite = "home.html";

		} else {

			// Mensaje de error en login.
			model.addAttribute("loginErrorMsgB", Boolean.FALSE);
			model.addAttribute("loginErrorMsg", "[ERROR] Introduzca datos correctos.");
		}

		return resultSite;
	}

	/**
	 * Muestra las citas y clases de un usuario.
	 * 
	 * @param session
	 * @param model
	 */
	@GetMapping("/miscitas")
	public String showProfile(HttpSession session, Model model) {

		// Obtencion de usuario logueado.
		final ZGUser loggedUser = (ZGUser) session.getAttribute("userLogged");

		if (loggedUser != null) {

			// Obtencion de usuario actualizado.
			final Optional<ZGUser> updUser = usersService.getUserById(loggedUser.getUserId());

			// Guardado de citas y clases.
			if (updUser.isPresent()) {
				if (updUser.get().getIsProfessional()) {
					model.addAttribute("userAppointments", updUser.get().getProfeAppts());
				} else {
					model.addAttribute("userAppointments", updUser.get().getCustAppts());
				}
				model.addAttribute("userClasses", updUser.get().getClasses());
			}
		}

		return "profile.html";
	}

	/**
	 * Navegacion hacia la pantalla de nueva cita alimentición.
	 * 
	 * @param session
	 * @param model
	 * @return String
	 */
	@GetMapping("/nuevaCita")
	public String showNewAppointmentView(HttpSession session, Model model) {

		// Obtencion de profesionales.
		final List<ZGUser> professionals = usersService.getProfessionals();

		// Guardado de profesionales.
		model.addAttribute("professionals", professionals);

		// Mensaje resultante al coger cita.
		model.addAttribute("apptResultMsgB", Boolean.TRUE);

		return "newAppt.html";
	}

	/**
	 * Navegacion hacia la pantalla de galeria.
	 * 
	 * @return String
	 */
	@GetMapping("/galeria")
	public String showGalleryView() {
		return "gallery.html";
	}

	/**
	 * Navegacion hacia la pantalla de productos.
	 * 
	 * @return String
	 */
	@GetMapping("/productos")
	public String showProductView() {
		return "product.html";
	}
}
