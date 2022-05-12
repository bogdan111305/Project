package com.example.AccountingSystem.web;

import com.example.AccountingSystem.kafka.service.ReportProducerService;
import com.example.AccountingSystem.payload.request.PageSetting;
import com.example.AccountingSystem.payload.request.dto.ReportDTO;
import com.example.AccountingSystem.service.ReportService;
import com.example.AccountingSystem.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/report")
public class RepostController {

    private final ReportService reportService;

    @Autowired
    public RepostController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping("/user/reports")
    public Page<ReportDTO> getAllReportByUser(@Valid @RequestBody PageSetting pageSetting, Principal principal){
        return reportService.getReportsByCurrentUser(pageSetting, principal);
    }

    @GetMapping("/{reportId}")
    public ReportDTO getReportById(@PathVariable("reportId") Long reportId, Principal principal){
        return reportService.getReportById(reportId,principal);
    }

    @PostMapping("/create")
    public ReportDTO creatReport(@Valid @RequestBody ReportDTO reportDTO, Principal principal){
        return reportService.createReport(reportDTO, principal);
    }

    @PostMapping("/update/{reportId}")
    public ReportDTO updateReport(@PathVariable("reportId") Long reportId, @Valid @RequestBody ReportDTO reportDTO, Principal principal){
        return reportService.updateReport(reportId, reportDTO, principal);
    }

    @PostMapping("/delete/{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@PathVariable("reportId") Long reportId, Principal principal){
        reportService.deleteReport(reportId, principal);
    }
}
