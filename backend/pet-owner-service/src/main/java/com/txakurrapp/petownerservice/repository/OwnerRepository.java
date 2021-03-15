package com.txakurrapp.petownerservice.repository;

import com.txakurrapp.petownerservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findOwnerByUserId(Long id);

    Optional<Owner> findOwnerByPersonalId(String personalId);

}
