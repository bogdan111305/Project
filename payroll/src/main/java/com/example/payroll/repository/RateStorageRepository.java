package com.example.payroll.repository;

import com.example.payroll.entity.RateStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface RateStorageRepository extends JpaRepository<RateStorage, Long> {
    Optional<RateStorage> findRateStorageByProject(String project);
}