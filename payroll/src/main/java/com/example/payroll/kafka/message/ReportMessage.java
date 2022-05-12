package com.example.payroll.kafka.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportMessage {
    private Long id;
    @NotEmpty(message = "Project not can empty")
    private String project;
    @NotNull
    private LocalDateTime dateReport;
    @NotNull
    private Byte numberOfHours;
    @NotEmpty(message = "Task not can empty")
    private String task;
    @NotEmpty(message = "Username not can empty")
    private String username;
    @NotEmpty(message = "Username not can empty")
    private String serviceTask;
}
