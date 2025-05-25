package com.example.rbacsystem.repository;

import com.example.rbacsystem.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByModuleAndAction(String module, String action);
    boolean existsByModuleAndAction(String module, String action);
}