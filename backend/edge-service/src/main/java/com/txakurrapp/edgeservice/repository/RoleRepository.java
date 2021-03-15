package com.txakurrapp.edgeservice.repository;

import com.txakurrapp.edgeservice.enums.ERole;
import com.txakurrapp.edgeservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
