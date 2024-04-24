package com.sideproject.hororok.keword.dto;

import com.sideproject.hororok.keword.domain.Keyword;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class KeywordDto {

    private final Long id;
    private final String name;


    public static KeywordDto from(final Keyword keyword) {

        return KeywordDto.builder()
                .id(keyword.getId())
                .name(keyword.getName())
                .build();
    }

    public static List<KeywordDto> fromList(List<Keyword> keywords) {
        return keywords.stream()
                .map(KeywordDto::from)
                .collect(Collectors.toList());
    }


}
