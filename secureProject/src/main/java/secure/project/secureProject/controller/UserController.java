package secure.project.secureProject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secure.project.secureProject.domain.User;
import secure.project.secureProject.dto.reqeust.UserReqeustDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
/*
    @PostMapping("/signup")
    public ResponseDto<Boolean> loginSignup(@Valid @RequestBody UserReqeustDto userReqeustDto) {
      return new ResponseDto<>(userService.loginTest(userReqeustDto));
    }
    */


}
