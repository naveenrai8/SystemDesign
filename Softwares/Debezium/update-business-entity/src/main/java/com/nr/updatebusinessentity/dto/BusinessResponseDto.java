package com.nr.updatebusinessentity.dto;

import lombok.Builder;

@Builder
public record BusinessResponseDto(
        String businessId,
        String businessName,
        String state,
        String country,
        long latitude,
        long longitude
) {
}
