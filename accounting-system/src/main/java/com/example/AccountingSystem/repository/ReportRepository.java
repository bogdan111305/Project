package com.example.AccountingSystem.repository;

import com.example.AccountingSystem.entity.Report;
import com.example.AccountingSystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {
    Page<Report> findAllByUser(User user, Pageable pageable);

    Page<Report> findAll(Pageable pageable);

    Optional<Report>  findReportByIdAndUser(Long id, User user);

    Optional<Report>  findReportById(Long id);
}
