package secure.project.secureProject.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secure.project.secureProject.domain.User;
import secure.project.secureProject.dto.reqeust.SignInReqeustDto;
import secure.project.secureProject.enums.UserRole;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;
import secure.project.secureProject.repository.UserRepository;
import secure.project.secureProject.security.jwt.JwtToken;
import secure.project.secureProject.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public JwtToken signIn(SignInReqeustDto signInReqeustDto) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        System.err.println("service 1");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInReqeustDto.getUsername(), signInReqeustDto.getPassword());
        System.err.println("service 211");
        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.err.println("service 3"+ authentication);

        User user = userRepository.findByNickname(signInReqeustDto.getUsername())
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication, user.getUserRole());
        System.err.println(jwtToken.getAccessToken());
        return jwtToken;
    }



}

