package com.txakurrapp.businessservice.repository;

import com.txakurrapp.businessservice.model.BusinessOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner, Long> {
    Optional<BusinessOwner> findByPersonalId(String personalId);
    Optional<BusinessOwner> findByUserId(Long id);
}
