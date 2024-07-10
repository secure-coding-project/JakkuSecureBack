package secure.project.secureProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import secure.project.secureProject.domain.enums.LoginProvider;
import secure.project.secureProject.dto.response.LoginDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.domain.enums.UserRole;
import secure.project.secureProject.service.AuthService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    // 소셜 로그인 - 소비자
    @PostMapping("/customers/kakao")
    public ResponseDto<LoginDto> byKakao(@RequestParam("code") String code) {
        System.err.println("asdf"+ code);
        return new ResponseDto<>(authService.socialLogin(code, LoginProvider.KAKAO));
    }

    @PostMapping("/customers/naver")
    public ResponseDto<LoginDto> byNaver(@RequestParam("code") String code) {
        return new ResponseDto<>(authService.socialLogin(code, LoginProvider.NAVER));
    }

    @GetMapping("/customers/google")
    public ResponseDto<LoginDto> byGoogle(@RequestParam("code") String code) {
        return new ResponseDto<>(authService.socialLogin(code, LoginProvider.GOOGLE));
    }

    @PostMapping("/customers/reissue")
    public ResponseDto<Map<String, String>> reissueCustomer(HttpServletRequest request) {
        return new ResponseDto<>(authService.reissueToken(request, UserRole.USER));
    }

}


