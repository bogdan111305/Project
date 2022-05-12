package com.example.AccountingSystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter @Getter
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String project;
    @NotNull
    private LocalDateTime dateReport;
    @NotNull
    private Byte numberOfHours;
    @NotEmpty
    private String task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Report() {

    }

    public Report(Long id, String project, LocalDateTime dateReport, Byte numberOfHours, String task){
        this.id = id;
        this.project = project;
        this.dateReport = dateReport;
        this.numberOfHours = numberOfHours;
        this.task = task;
    }
}
