package com.zengym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zengym.persistence.ZGClass;
import com.zengym.persistence.ZGUser;
import com.zengym.repositories.ZGClassRepositoryI;
import com.zengym.repositories.ZGUserRepositoryI;

/**
 * Zen Gym
 * 
 * Clase principal
 * 
 * @author Raquel DH
 *
 */
@SpringBootApplication
public class ZenGymApplication implements CommandLineRunner {

	/** Inyeccion: repositorio de usuarios */
	@Autowired
	private ZGUserRepositoryI userRepo;

	/** Inyeccion: repositorio de clases */
	@Autowired
	private ZGClassRepositoryI classesRepo;

	public static void main(String[] args) {
		SpringApplication.run(ZenGymApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Usuarios por defecto.
		final ZGUser prof1 = new ZGUser();
		prof1.setUsermail("raqueldh@zengym.com");
		prof1.setPassword("123");
		prof1.setName("Raquel Díaz Hernández");
		prof1.setIsProfessional(Boolean.TRUE);

		final ZGUser prof2 = new ZGUser();
		prof2.setUsermail("ainaracm@zengym.com");
		prof2.setPassword("123");
		prof2.setName("Ainara Cano Morales");
		prof2.setIsProfessional(Boolean.TRUE);

		final ZGUser customer1 = new ZGUser();
		customer1.setUsermail("noelia.cardoso@salesianas.org");
		customer1.setPassword("123");
		customer1.setName("Noelia Cardoso Rubio");
		customer1.setIsProfessional(Boolean.FALSE);

		// Guardado de usuarios.
		userRepo.save(prof1);
		userRepo.save(prof2);
		userRepo.save(customer1);

		// Generacion de clases por defecto.
		final ZGClass cl1 = new ZGClass();
		cl1.setInstructor("María RA");
		cl1.setDayHour("Lunes - 10:00h.");
		cl1.setDescription("Ed. Postural");
		classesRepo.save(cl1);

		final ZGClass cl2 = new ZGClass();
		cl2.setInstructor("Carlos GZ");
		cl2.setDayHour("Lunes - 11:00h.");
		cl2.setDescription("Zumba");
		classesRepo.save(cl2);

		final ZGClass cl3 = new ZGClass();
		cl3.setInstructor("Carlos GZ");
		cl3.setDayHour("Lunes - 12:00h.");
		cl3.setDescription("Circuito");
		classesRepo.save(cl3);

		final ZGClass cl4 = new ZGClass();
		cl4.setInstructor("Laura HS");
		cl4.setDayHour("Lunes - 17:00h.");
		cl4.setDescription("Pump");
		classesRepo.save(cl4);

		final ZGClass cl5 = new ZGClass();
		cl5.setInstructor("Maria RA");
		cl5.setDayHour("Lunes - 18:00h.");
		cl5.setDescription("TRX");
		classesRepo.save(cl5);

		final ZGClass cl6 = new ZGClass();
		cl6.setInstructor("Javier SR");
		cl6.setDayHour("Lunes - 19:00h.");
		cl6.setDescription("Yoga");
		classesRepo.save(cl6);

	}
}
