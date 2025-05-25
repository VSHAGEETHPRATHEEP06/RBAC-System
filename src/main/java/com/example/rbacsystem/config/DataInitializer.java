package com.example.rbacsystem.config;

import com.example.rbacsystem.entity.Permission;
import com.example.rbacsystem.entity.Role;
import com.example.rbacsystem.entity.User;
import com.example.rbacsystem.repository.PermissionRepository;
import com.example.rbacsystem.repository.RoleRepository;
import com.example.rbacsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize permissions if they don't exist
        if (permissionRepository.count() == 0) {
            createPermissions();
        }

        // Initialize roles if they don't exist
        if (roleRepository.count() == 0) {
            createRoles();
        }

        // Initialize admin user if no users exist
        if (userRepository.count() == 0) {
            createAdminUser();
        }
    }

    private void createPermissions() {
        // Lab Analytics permissions
        permissionRepository.save(new Permission("LAB_ANALYTICS", "VIEW"));
        permissionRepository.save(new Permission("LAB_ANALYTICS", "CREATE"));
        permissionRepository.save(new Permission("LAB_ANALYTICS", "UPDATE"));
        permissionRepository.save(new Permission("LAB_ANALYTICS", "DELETE"));

        // Test Request permissions
        permissionRepository.save(new Permission("TEST_REQUEST", "VIEW"));
        permissionRepository.save(new Permission("TEST_REQUEST", "CREATE"));
        permissionRepository.save(new Permission("TEST_REQUEST", "UPDATE"));
        permissionRepository.save(new Permission("TEST_REQUEST", "DELETE"));

        // Test Report permissions
        permissionRepository.save(new Permission("TEST_REPORT", "VIEW"));
        permissionRepository.save(new Permission("TEST_REPORT", "CREATE"));
        permissionRepository.save(new Permission("TEST_REPORT", "UPDATE"));
        permissionRepository.save(new Permission("TEST_REPORT", "DELETE"));

        // User Management permissions
        permissionRepository.save(new Permission("USER_MANAGEMENT", "VIEW"));
        permissionRepository.save(new Permission("USER_MANAGEMENT", "CREATE"));
        permissionRepository.save(new Permission("USER_MANAGEMENT", "UPDATE"));
        permissionRepository.save(new Permission("USER_MANAGEMENT", "DELETE"));

        System.out.println("Permissions created successfully!");
    }

    private void createRoles() {
        // Create ADMIN role with all permissions
        Role adminRole = new Role("ADMIN");
        permissionRepository.findAll().forEach(adminRole::addPermission);
        roleRepository.save(adminRole);

        // Create LAB_TECHNICIAN role with limited permissions
        Role labTechRole = new Role("LAB_TECHNICIAN");
        labTechRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REQUEST", "VIEW").orElse(null));
        labTechRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REQUEST", "CREATE").orElse(null));
        labTechRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REPORT", "VIEW").orElse(null));
        labTechRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REPORT", "CREATE").orElse(null));
        roleRepository.save(labTechRole);

        // Create LAB_MANAGER role
        Role labManagerRole = new Role("LAB_MANAGER");
        labManagerRole.addPermission(permissionRepository.findByModuleAndAction("LAB_ANALYTICS", "VIEW").orElse(null));
        labManagerRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REQUEST", "VIEW").orElse(null));
        labManagerRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REQUEST", "UPDATE").orElse(null));
        labManagerRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REPORT", "VIEW").orElse(null));
        labManagerRole.addPermission(permissionRepository.findByModuleAndAction("TEST_REPORT", "UPDATE").orElse(null));
        roleRepository.save(labManagerRole);

        System.out.println("Roles created successfully!");
    }

    private void createAdminUser() {
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        if (adminRole != null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@example.com");
            admin.setName("System Administrator");
            admin.setRole(adminRole);
            admin.setFirstLogin(false);

            userRepository.save(admin);
            System.out.println("Admin user created successfully!");
            System.out.println("Username: admin");
            System.out.println("Password: admin123");
        }
    }
}