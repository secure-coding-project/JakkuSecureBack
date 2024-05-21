package secure.project.secureProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secure.project.secureProject.dto.reqeust.SignInReqeustDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.dto.response.TokenDto;
import secure.project.secureProject.security.LoginService;
import secure.project.secureProject.security.jwt.JwtToken;
import secure.project.secureProject.util.SecurityUtil;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/sign-in")
    public ResponseDto<JwtToken> signIn(
            @RequestBody SignInReqeustDto signInReqeustDto
            ) {
        System.err.println("controller까지는 들어옴");
        return new ResponseDto<>(loginService.signIn(signInReqeustDto));
    }

    @PostMapping("/test")
    public ResponseDto<String> test() {
        return new ResponseDto<>(SecurityUtil.getCurrentUsername());
    }
}
