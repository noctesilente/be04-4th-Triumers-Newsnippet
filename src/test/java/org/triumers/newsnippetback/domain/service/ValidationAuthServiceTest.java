package org.triumers.newsnippetback.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.triumers.newsnippetback.common.exception.WrongInputTypeException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationAuthServiceTest {

    @Autowired
    private ValidationAuthService validation;

    @DisplayName("닉네임 정상값 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"테스트닉네임", "짧다", "일이삼사오육칠팔구십", "alpha123한글", "ㅁㄴㅇㅁㄴㅇ"})
    void rightNickname(String nickname) throws WrongInputTypeException {

        assertDoesNotThrow(() -> validation.nickname(nickname));
    }

    @DisplayName("닉네임 길이 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "짧", "일이삼사오육칠팔구십넘었다"})
    void wrongLengthNickname(String nickname) {
        assertThrows(WrongInputTypeException.class, () -> validation.nickname(nickname));
    }

    @DisplayName("닉네임 타입 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"!@#", "테스트$%^", "&*()/\\"})
    void wrongTypeNickname(String nickname) {
        assertThrows(WrongInputTypeException.class, () -> validation.nickname(nickname));
    }
}