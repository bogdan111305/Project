package com.example.payroll.repository;

import com.example.payroll.entity.Accrual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccrualRepository extends JpaRepository<Accrual, Long> {
    Optional<Accrual>  findAccrualByIdAndUsername(Long id, String username);

    List<Accrual> findAccrualByUsernameAndAndDateReportAfter(String username, LocalDateTime dateTime);
}
