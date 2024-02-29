package com.memopet.memopet.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    @JsonProperty("message")
    private String message;
}
