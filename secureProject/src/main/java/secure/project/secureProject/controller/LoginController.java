package secure.project.secureProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secure.project.secureProject.dto.request.LoginSignUpRequestDto;
import secure.project.secureProject.dto.request.SignInRequestDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.security.LoginService;
import secure.project.secureProject.security.jwt.JwtToken;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/sign-in")
    public ResponseDto<JwtToken> signIn(
            @RequestBody SignInRequestDto signInRequestDto
            ) {
        return new ResponseDto<>(loginService.signIn(signInRequestDto));
    }

    @PostMapping("/sign-up")
    public ResponseDto<Boolean> signUp(
            @RequestBody LoginSignUpRequestDto loginSignUpRequestDto
            ) {
        return new ResponseDto<>(loginService.signUp(loginSignUpRequestDto));
    }

}
