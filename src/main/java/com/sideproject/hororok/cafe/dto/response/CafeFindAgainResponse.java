package com.sideproject.hororok.cafe.dto.response;

import com.sideproject.hororok.cafe.dto.CafeDto;
import com.sideproject.hororok.keword.dto.CategoryKeywordsDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CafeFindAgainResponse {

    private final boolean isExist;
    private final List<CafeDto> cafes;
    private final CategoryKeywordsDto categoryKeywords;

    public static CafeFindAgainResponse of(List<CafeDto> cafes, CategoryKeywordsDto categoryKeywords) {
        return CafeFindAgainResponse.builder()
                .isExist(!cafes.isEmpty())
                .cafes(cafes)
                .categoryKeywords(categoryKeywords)
                .build();
    }
}
