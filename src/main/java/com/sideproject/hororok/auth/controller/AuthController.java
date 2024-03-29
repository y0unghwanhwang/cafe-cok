package com.sideproject.hororok.auth.controller;


import com.sideproject.hororok.auth.annotation.LoginUser;
import com.sideproject.hororok.auth.dto.SessionUser;
import com.sideproject.hororok.auth.kakao.dto.KakaoAccount;
import com.sideproject.hororok.auth.kakao.dto.KakaoInfo;
import com.sideproject.hororok.auth.kakao.dto.KakaoToken;
import com.sideproject.hororok.auth.kakao.service.KakaoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final KakaoService kakaoService;
    private final HttpSession httpSession;

    @GetMapping("/auth/kakao/login")
    public ResponseEntity<SessionUser> kakaoLogin(@RequestParam("code") String code) {

        KakaoToken token = kakaoService.getToken(code);
        KakaoInfo info = kakaoService.getInfo(token);

        SessionUser sessionUser = new SessionUser(info.getKakaoAccount(), token.getAccessToken());
        httpSession.setAttribute("user", sessionUser);

        return ResponseEntity.ok(sessionUser);
    }
}
