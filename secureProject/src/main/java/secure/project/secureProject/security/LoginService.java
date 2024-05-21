package secure.project.secureProject.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secure.project.secureProject.domain.User;
import secure.project.secureProject.dto.request.LoginSignUpRequestDto;
import secure.project.secureProject.dto.request.SignInRequestDto;
import secure.project.secureProject.enums.UserRole;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;
import secure.project.secureProject.repository.UserRepository;
import secure.project.secureProject.security.jwt.JwtToken;
import secure.project.secureProject.security.jwt.JwtTokenProvider;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public JwtToken signIn(SignInRequestDto signInReqeustDto) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        User userNickname = userRepository.findByLoginId(signInReqeustDto.getLoginId())
                        .orElseThrow(()-> new ApiException(ErrorDefine.USER_NOT_FOUND));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userNickname.getNickname(), signInReqeustDto.getPassword());

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        User user = userRepository.findByNickname(userNickname.getNickname())
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication, user.getUserRole());

        return jwtToken;
    }

    public Boolean signUp(LoginSignUpRequestDto loginSignUpRequestDto) {
        if(userRepository.findByNickname(loginSignUpRequestDto.getUserNickname()).isPresent())
            throw new ApiException(ErrorDefine.USER_EXIST);
        if(userRepository.findByLoginId(loginSignUpRequestDto.getLoginId()).isPresent())
            throw new ApiException(ErrorDefine.USERID_EXIST);

        User user = User.builder()
                .loginId(loginSignUpRequestDto.getLoginId())
                .password(loginSignUpRequestDto.getPassword())
                .point(0L)
                .nickname(loginSignUpRequestDto.getUserNickname())
                .userRole(UserRole.USER)
                .updateAt(LocalDate.now())
                .build();
        userRepository.save(user);
        return true;
    }

}

