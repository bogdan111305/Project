package com.example.payroll.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accrual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Project not can empty")
    private String project;
    @NotNull
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime dateReport;
    @NotNull
    private Byte numberOfHours;
    @NotEmpty(message = "Task not can empty")
    private String task;
    @NotEmpty(message = "Username not can empty")
    private String username;
    @NotNull(message = "Payment not can empty")
    private Integer payment;
}
