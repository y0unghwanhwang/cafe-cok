package com.sideproject.hororok.plan.dto;

import com.sideproject.hororok.keword.dto.KeywordDto;
import com.sideproject.hororok.plan.domain.Plan;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Builder
public class PlanDto {

    @NotNull
    private final Long id;
    @NotNull
    private final KeywordDto keyword;
    private final String location;
    private final LocalDate visitDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public static PlanDto of(final Plan plan, final KeywordDto keyword) {
        return PlanDto.builder()
                .id(plan.getId())
                .keyword(keyword)
                .location(plan.getLocationName())
                .visitDate(plan.getVisitDate())
                .startTime(plan.getVisitStartTime())
                .endTime(plan.getVisitEndTime())
                .build();
    }

}
