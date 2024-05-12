package com.sideproject.cafe_cok.common.fixtures;

import com.sideproject.cafe_cok.member.domain.Member;
import com.sideproject.cafe_cok.member.domain.enums.SocialType;
import com.sideproject.cafe_cok.member.dto.response.MyPagePlanDetailResponse;

import static com.sideproject.cafe_cok.common.fixtures.CafeFixtures.카페_DTO_리스트;
import static com.sideproject.cafe_cok.common.fixtures.KeywordFixtures.카테고리_키워드_DTO;
import static com.sideproject.cafe_cok.common.fixtures.PlanFixtures.계획;


public class MemberFixtures {

    /* 맴버 */
    public static final Long 맴버_ID = 1L;
    public static final String 맴버_이메일 = "cafe_cok_@gmail.com";
    public static final String 맴버_닉네임 = "cafe_cok_nickname";
    public static final String 멤버_프로필_이미지_URL = "프로필 이미지 경로";
    public static final String 멤버_기본_이미지 =
            "https://kr.object.ncloudstorage.com/cafe_cok-bucket/member/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-04-20%20%EC%98%A4%ED%9B%84%2011.46.07.png";

    public static Member 사용자() {
        Member member = new Member(맴버_이메일, 맴버_닉네임, SocialType.KAKAO);
        member.changePicture(멤버_프로필_이미지_URL);
        return member;
    }

    public static MyPagePlanDetailResponse 마이페이지_계획_상세_응답() {
        return MyPagePlanDetailResponse.of(계획(), 카테고리_키워드_DTO(), 카페_DTO_리스트());
    }


}