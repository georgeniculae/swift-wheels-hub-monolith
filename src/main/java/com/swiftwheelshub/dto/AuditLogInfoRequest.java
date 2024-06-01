package com.swiftwheelshub.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogInfoRequest {

    @NotEmpty(message = "Method name cannot be empty")
    private String methodName;

    private String username;

    @NotNull(message = "Timestamp cannot be null")
    private LocalDateTime timestamp;

    private List<String> parametersValues;

    @Override
    public String toString() {
        return "AuditLogInfoRequest{" + "\n" +
                "methodName=" + methodName + "\n" +
                "username=" + username + "\n" +
                "timestamp=" + timestamp + "\n" +
                "parametersValues=" + parametersValues + "\n" +
                "}";
    }

}
