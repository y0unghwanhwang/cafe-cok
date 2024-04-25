package com.sideproject.hororok.member.application;

import com.sideproject.hororok.auth.dto.LoginMember;
import com.sideproject.hororok.bookmark.application.BookmarkFolderService;
import com.sideproject.hororok.bookmark.dto.BookmarkFolderDto;
import com.sideproject.hororok.keword.domain.enums.Category;
import com.sideproject.hororok.member.domain.Member;
import com.sideproject.hororok.member.domain.repository.MemberRepository;
import com.sideproject.hororok.member.dto.response.MyPagePlanResponse;
import com.sideproject.hororok.member.dto.response.MyPageResponse;
import com.sideproject.hororok.plan.domain.Plan;
import com.sideproject.hororok.plan.domain.repository.PlanKeywordRepository;
import com.sideproject.hororok.plan.domain.repository.PlanRepository;
import com.sideproject.hororok.plan.dto.SavedPlanDto;
import com.sideproject.hororok.plan.dto.SharedPlanDto;
import com.sideproject.hororok.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final PlanRepository planRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PlanKeywordRepository planKeywordRepository;

    private final BookmarkFolderService bookmarkFolderService;

    private static final Integer MY_PAGE_PLAN_MAX_CNT = 5;

    public MyPageResponse myPage(LoginMember loginMember) {

        Long memberId = loginMember.getId();

        Member findMember = memberRepository.getById(memberId);
        Long findReviewCount = reviewRepository.countReviewsByMemberId(memberId);
        List<BookmarkFolderDto> findFolders
                = bookmarkFolderService.getBookmarkFolderDtos(memberId);

        return new MyPageResponse(findMember, findReviewCount, findFolders);
    }

    public MyPagePlanResponse plan(LoginMember loginMember) {

        List<Plan> findPlans = planRepository.findByMemberIdOrderByCreatedDateDesc(loginMember.getId());

        List<SavedPlanDto> savedPlanDtos = getSavedPlanDtos(findPlans);
        List<SharedPlanDto> sharedPlanDtos = getSharedPlanDtos(findPlans);

        return MyPagePlanResponse.from(savedPlanDtos, sharedPlanDtos);
    }

    private List<SavedPlanDto> getSavedPlanDtos(List<Plan> findPlans) {
        return findPlans.stream()
                .filter(findPlan -> findPlan.getIsSaved())
                .map(findPlan -> {
                    String findKeyword = planKeywordRepository
                            .getFirstByPlanIdAndKeywordCategory(findPlan.getId(), Category.PURPOSE)
                            .getKeyword().getName();
                    return SavedPlanDto.of(findPlan, findKeyword);
                })
                .limit(MY_PAGE_PLAN_MAX_CNT)
                .collect(Collectors.toList());
    }


    private List<SharedPlanDto> getSharedPlanDtos(List<Plan> findPlans) {
        return findPlans.stream()
                .filter(findPlan -> findPlan.getIsShared())
                .map(findPlan -> {
                    String findKeyword = planKeywordRepository
                            .getFirstByPlanIdAndKeywordCategory(findPlan.getId(), Category.PURPOSE)
                            .getKeyword().getName();
                    return SharedPlanDto.of(findPlan, findKeyword);
                })
                .limit(MY_PAGE_PLAN_MAX_CNT)
                .collect(Collectors.toList());
    }
}
