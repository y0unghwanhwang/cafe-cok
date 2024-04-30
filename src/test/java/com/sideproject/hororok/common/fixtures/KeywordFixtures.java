package com.sideproject.hororok.common.fixtures;

import com.sideproject.hororok.keword.domain.Keyword;
import com.sideproject.hororok.keword.domain.enums.Category;
import com.sideproject.hororok.keword.dto.CategoryKeywordsDto;
import com.sideproject.hororok.keword.dto.KeywordDto;
import com.sideproject.hororok.plan.domain.Plan;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class KeywordFixtures {

    public static final Long 키워드_ID = 1L;
    public static final String 키워드_이름 = "키워드 이름";
    public static final List<String> 키워드_이름_리스트 = Arrays.asList(키워드_이름);
    public static final List<Keyword> 키워드_리스트 = Arrays.asList(키워드());

    public static CategoryKeywordsDto 카테고리_키워드_DTO() {
        return new CategoryKeywordsDto(키워드_리스트);
    }

    public static Keyword 키워드() {
        Keyword keyword = new Keyword(키워드_이름, Category.PURPOSE);
        setId(keyword, 키워드_ID);
        return keyword;
    }

    public static KeywordDto 키워드_DTO() {
        return KeywordDto.from(키워드());
    }

    public static List<KeywordDto> 키워드_DTO_리스트() {
        return Arrays.asList(키워드_DTO());
    }

    public static Keyword setId(Keyword keyword, final Long id) {

        try {
            Field idField = Keyword.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(keyword, id);
            return keyword;
        } catch (final NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}