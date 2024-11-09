package tn.esprit.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.spring.enities.Admin;
import tn.esprit.spring.enities.ERole;
import tn.esprit.spring.enities.Role;
import tn.esprit.spring.enities.User;
import tn.esprit.spring.repositories.IAdminRepository;
import tn.esprit.spring.repositories.IRoleRepository;
import tn.esprit.spring.repositories.IUserRepository;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class MyFirstProjectApplication implements CommandLineRunner {

	private final IUserRepository userRepository;
	private final IAdminRepository adminRepository;
	@Autowired
	IRoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

	public void run(String... args) {
		User adminAccount = userRepository.findByRolesName(ERole.ADMIN);
		if (adminAccount == null) {
			// Find the "ADMIN" role
			Role adminRole = roleRepository.findByName(ERole.ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			//INSERT INTO roles(name) VALUES ('SIMPLE_USER');
			// Create a new admin
			Admin admin = new Admin();
			admin.setEmail("admin@gmail.com");
			admin.setNom("admin");
			admin.setPrenom("admin");
			admin.setRoles(Set.of(adminRole)); // Set the admin role
			admin.setImage("ffffffff");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));

			// Save the admin
			adminRepository.save(admin);
		}
	}
	}

