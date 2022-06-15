package com.zengym.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengym.persistence.ZGClass;
import com.zengym.persistence.ZGUser;
import com.zengym.services.ZGClassesServiceI;
import com.zengym.services.ZGUsersServiceI;

import jakarta.servlet.http.HttpSession;

/**
 * Zen Gym
 * 
 * Controlador de clases
 * 
 * @author Raquel DH
 *
 */
@Controller
@RequestMapping
public class ZGClassesController {

	/** Inyeccion: servicio de gestion de clases */
	@Autowired
	private ZGClassesServiceI classesService;

	/** Inyeccion: servicio de gestion de usuarios */
	@Autowired
	private ZGUsersServiceI usersService;

	/**
	 * Muestra las clases disponibles.
	 * 
	 * @param model
	 */
	@GetMapping("/clases")
	public String showClasses(Model model) {

		// Obtencion de clases.
		final List<ZGClass> classes = classesService.getClasses();

		// Guardado de clases.
		model.addAttribute("classList", classes);
		model.addAttribute("classInsMsgB", Boolean.TRUE);

		// Clase vacía.
		final ZGClass emptyClass = new ZGClass();
		emptyClass.setDayHour("");
		emptyClass.setDescription("");
		emptyClass.setInstructor("");

		model.addAttribute("classToLoad", emptyClass);

		return "classes.html";
	}

	/**
	 * Carga los campos para actualizar una clase.
	 * 
	 * @param model
	 * @param classId
	 */
	@PostMapping("/editarClase")
	public String loadClassForEdit(Model model, final String classId) {

		// Obtencion de clase a editar..
		final Optional<ZGClass> classToLoad = classesService.getClassById(Long.valueOf(classId));

		// Obtencion de clases.
		final List<ZGClass> classes = classesService.getClasses();

		// Guardado de clases.
		model.addAttribute("classList", classes);
		model.addAttribute("classInsMsgB", Boolean.TRUE);
		model.addAttribute("classToLoad", classToLoad.get());

		return "classes.html";
	}

	/**
	 * Inscripción de usuario a cita.
	 * 
	 * @param session
	 * @param model
	 * @param classId
	 */
	@PostMapping("/inscripcionClase")
	public String inscriptionToClass(HttpSession session, Model model, final Long classId) {

		// Obtencion de usuario logueado.
		final ZGUser loggedUser = (ZGUser) session.getAttribute("userLogged");

		// Inscripcion.
		final String msgResult = classesService.inscribeToClass(loggedUser, classId);

		// Mensaje resultado.
		model.addAttribute("classInsMsgB", Boolean.FALSE);
		model.addAttribute("classInsMsg", msgResult);

		// Obtencion de clases.
		final List<ZGClass> classes = classesService.getClasses();

		// Guardado de clases.
		model.addAttribute("classList", classes);

		// Clase vacía.
		final ZGClass emptyClass = new ZGClass();
		emptyClass.setDayHour("");
		emptyClass.setDescription("");
		emptyClass.setInstructor("");

		model.addAttribute("classToLoad", emptyClass);

		return "classes.html";
	}

	/**
	 * Elimina una clase.
	 * 
	 * @param model
	 * @param classId
	 * @return String
	 */
	@PostMapping("/eliminarClase")
	public String deleteClass(Model model, final String classId) {

		// Obtencion de clase a eliminar.
		final Optional<ZGClass> classToRemove = classesService.getClassById(Long.valueOf(classId));

		// Eliminacion de la clase.
		if (classToRemove.isPresent()) {

			// Actualizacion del usuario en BD.
			classesService.deleteClass(classToRemove.get());
		}

		// Obtencion de clases.
		final List<ZGClass> classes = classesService.getClasses();

		// Guardado de clases.
		model.addAttribute("classList", classes);
		model.addAttribute("classInsMsgB", Boolean.TRUE);

		// Clase vacía.
		final ZGClass emptyClass = new ZGClass();
		emptyClass.setDayHour("");
		emptyClass.setDescription("");
		emptyClass.setInstructor("");

		model.addAttribute("classToLoad", emptyClass);

		return "classes.html";
	}

	/**
	 * Elimina una clase para el usuario.
	 * 
	 * @param session
	 * @param classId
	 * @return String
	 */
	@PostMapping("/actDropUserClass")
	public String deleteCustomer(HttpSession session, final String classId) {

		// Obtencion de usuario logueado.
		final ZGUser loggedUser = (ZGUser) session.getAttribute("userLogged");

		// Obtencion de usuario refrescado.
		final Optional<ZGUser> logUser = usersService.getUserById(loggedUser.getUserId());

		// Obtencion de clase a eliminar del usuario.
		final Optional<ZGClass> classToRemove = classesService.getClassById(Long.valueOf(classId));

		// Eliminacion de la clase de la lista de clases del usuario.
		if (logUser.isPresent() && classToRemove.isPresent()) {

			logUser.get().removeClass(classToRemove.get());

			// Actualizacion del usuario en BD.
			usersService.updateUser(logUser.get());
		}

		return "redirect:/miscitas";
	}

	/**
	 * Crea una nueva clase o actualiza la existente.
	 * 
	 * @param model
	 * @param classId
	 * @param instructor
	 * @param description
	 * @param dayHour
	 */
	@PostMapping("/guardarOActualizarClase")
	public String saveClass(Model model, final String classId, final String instructor, final String description,
			final String dayHour) {

		// Resultado.
		ZGClass classToSave = new ZGClass();

		if (StringUtils.hasText(classId)) {

			// Obtencion de clase a editar..
			final Optional<ZGClass> classToSaveF = classesService.getClassById(Long.valueOf(classId));
			if (classToSaveF.isPresent()) {
				classToSave = classToSaveF.get();
			}
		}

		classToSave.setDayHour(dayHour);
		classToSave.setDescription(description);
		classToSave.setInstructor(instructor);
		classesService.saveOrUpdateClass(classToSave);

		// Obtencion de clases.
		final List<ZGClass> classes = classesService.getClasses();

		// Guardado de clases.
		model.addAttribute("classList", classes);
		model.addAttribute("classInsMsgB", Boolean.TRUE);

		// Clase vacía.
		final ZGClass emptyClass = new ZGClass();
		emptyClass.setDayHour("");
		emptyClass.setDescription("");
		emptyClass.setInstructor("");

		model.addAttribute("classToLoad", emptyClass);

		return "classes.html";
	}

}
