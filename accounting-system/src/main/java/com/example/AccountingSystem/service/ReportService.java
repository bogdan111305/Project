package com.example.AccountingSystem.service;

import com.example.AccountingSystem.payload.request.PageSetting;
import com.example.AccountingSystem.payload.request.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import java.security.Principal;

public interface ReportService {
    ReportDTO createReport(ReportDTO reportDTO, Principal principal);

    ReportDTO updateReport(Long reportId, ReportDTO reportDTO, Principal principal);

    ReportDTO getReportById(Long reportId, Principal principal);

    Page<ReportDTO> getReportsByCurrentUser(PageSetting pageSetting, Principal principal);

    void deleteReport(Long reportId, Principal principal);
}
