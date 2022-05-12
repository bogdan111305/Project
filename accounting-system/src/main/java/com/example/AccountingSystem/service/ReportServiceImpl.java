package com.example.AccountingSystem.service;

import com.example.AccountingSystem.exceptions.ReportNotFoundException;
import com.example.AccountingSystem.kafka.message.ReportMessage;
import com.example.AccountingSystem.kafka.service.ProducerService;
import com.example.AccountingSystem.specifications.ReportSpecification;
import com.example.AccountingSystem.specifications.SearchCriteria;
import com.example.AccountingSystem.entity.Report;
import com.example.AccountingSystem.entity.User;
import com.example.AccountingSystem.payload.request.PageSetting;
import com.example.AccountingSystem.payload.request.dto.ReportDTO;
import com.example.AccountingSystem.repository.ReportRepository;
import com.example.AccountingSystem.repository.UserRepository;
import com.example.AccountingSystem.specifications.SortCriteria;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    public static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ProducerService producerService;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository,
                             UserRepository userRepository,
                             ModelMapper modelMapper,
                             ProducerService producerService){
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.producerService = producerService;
    }

    public ReportDTO createReport(ReportDTO reportDTO, Principal principal) {
        User user = getUserByPrincipal(principal);

        Report report = modelMapper.map(reportDTO, Report.class);
        report.setUser(user);

        LOG.info("Report id(" + report.getId() + ") successful save.");
        reportRepository.save(report);

        ReportDTO reportCreated = modelMapper.map(report, ReportDTO.class);

        ReportMessage reportMessage = modelMapper.map(reportCreated, ReportMessage.class);
        reportMessage.setUsername(report.getUser().getUsername());
        reportMessage.setServiceTask("create");

        producerService.send(reportMessage);

        return reportCreated;
    }

    public ReportDTO updateReport(Long reportId, ReportDTO reportDTO, Principal principal) {
        Report report = getReportByIdAndRoleUser(reportId, principal);

        report.setDateReport(reportDTO.getDateReport());
        report.setTask(reportDTO.getTask());
        report.setProject(reportDTO.getProject());
        report.setNumberOfHours(reportDTO.getNumberOfHours());

        LOG.info("Report id(" + report.getId() + ") successful update.");
        reportRepository.save(report);

        ReportDTO reportUpdated = modelMapper.map(report, ReportDTO.class);

        ReportMessage reportMessage = modelMapper.map(reportUpdated, ReportMessage.class);
        reportMessage.setUsername(report.getUser().getUsername());
        reportMessage.setServiceTask("update");

        producerService.send(reportMessage);

        return reportUpdated;
    }

    public void deleteReport(Long reportId, Principal principal){
        Report report = getReportByIdAndRoleUser(reportId, principal);

        LOG.info("Report id(" + report.getId() + ") successful delete.");
        reportRepository.delete(report);

        ReportDTO reportDeleted = modelMapper.map(report, ReportDTO.class);

        ReportMessage reportMessage = modelMapper.map(reportDeleted, ReportMessage.class);
        reportMessage.setUsername(report.getUser().getUsername());
        reportMessage.setServiceTask("delete");

        producerService.send(reportMessage);
    }

    public ReportDTO getReportById(Long reportId, Principal principal){
        Report report = getReportByIdAndRoleUser(reportId, principal);

        return modelMapper.map(report, ReportDTO.class);
    }

    public Page<ReportDTO> getReportsByCurrentUser(PageSetting pageSetting, Principal principal){
        Pageable pageable = getPageable(
            pageSetting.getPage(),
            pageSetting.getSize(),
            pageSetting.getSortCriteria());

        Page<Report> reports;

        if(pageSetting.getSearchCriteria() == null){
            if(hasRole("ROLE_ADMIN")){
                reports = reportRepository.findAll(pageable);
            } else {
                User user = getUserByPrincipal(principal);

                reports = reportRepository.findAllByUser(user, pageable);
            }
        } else {
            List<SearchCriteria> searchCriteriaList = pageSetting.getSearchCriteria();
            ReportSpecification reportSpecifications = new ReportSpecification();
            reportSpecifications.setList(searchCriteriaList);
            reports = reportRepository.findAll(reportSpecifications, pageable);
        }

        return reports.map((report) -> modelMapper.map(report, ReportDTO.class));
    }

    public Report getReportByIdAndRoleUser(Long reportId, Principal principal){
        if(hasRole("ROLE_ADMIN")){
            return reportRepository.findReportById(reportId)
                    .orElseThrow(() -> new ReportNotFoundException("Report not found"));
        } else {
            User user = getUserByPrincipal(principal);

            return reportRepository.findReportByIdAndUser(reportId, user)
                    .orElseThrow(() -> new ReportNotFoundException("Report not found"));
        }
    }

    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
    }

    private Pageable getPageable(int page, int size, List<SortCriteria> prop){
        Sort sort = null;

        for (SortCriteria sortCriteria: prop) {
            Sort newSort;

            if (sortCriteria.getDirection().equals("ASC")){
                newSort = Sort.by(sortCriteria.getSortProperty()).ascending();
            } else {
                newSort = Sort.by(sortCriteria.getSortProperty()).descending();
            }

            if(sort == null){
                sort = newSort;
            } else {
                sort.and(newSort);
            }
        }

        if(sort == null){
            return PageRequest.of(page, size);
        } else{
            return PageRequest.of(page, size, sort);
        }
    }

    private boolean hasRole(String roleName) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
    }
}
