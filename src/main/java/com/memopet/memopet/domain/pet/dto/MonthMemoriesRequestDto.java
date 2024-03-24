package com.memopet.memopet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthMemoriesRequestDto {
    @JsonProperty("pet_id")
    private String petId;
    @JsonProperty("current_page")
    private int currentPage;
    @JsonProperty("data_counts")
    private int dataCounts;
    @JsonProperty("year_month")
    private String yearMonth;
}
