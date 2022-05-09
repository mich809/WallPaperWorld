package com.CaridadMichael.WallPaperWorld.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.CaridadMichael.WallPaperWorld.model.AppUser;
import com.CaridadMichael.WallPaperWorld.model.Role;
import com.CaridadMichael.WallPaperWorld.repository.RoleRepo;
import com.CaridadMichael.WallPaperWorld.repository.UserRepository;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	@Autowired
	UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		roleRepo.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("User");
		roleRepo.save(userRole);

		if (userRepo.findByUsername("admin") == null) {
			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(adminRole);
			AppUser admin = new AppUser();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("password"));
			admin.setRole(adminRoles);

			userRepo.save(admin);

		}

	}
}