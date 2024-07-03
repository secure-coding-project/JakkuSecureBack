package secure.project.secureProject.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import secure.project.secureProject.domain.User;
import secure.project.secureProject.domain.enums.LoginProvider;
import secure.project.secureProject.domain.enums.UserRole;
import secure.project.secureProject.dto.response.LoginDto;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;
import secure.project.secureProject.repository.UserRepository;
import secure.project.secureProject.security.oauth.JwtProvider;
import secure.project.secureProject.security.oauth.JwtToken;
import secure.project.secureProject.util.Oauth2Info;
import secure.project.secureProject.util.Oauth2Util;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository customerRepository;
    private final Oauth2Util oauth2Util;
    private final JwtProvider jwtProvider;

    public LoginDto socialLogin(String authCode, LoginProvider loginProvider) {
        String socialId = null;
        String socialName = null;

        switch (loginProvider) {
            case KAKAO:
                String kakaoAccessToken = oauth2Util.getKakaoAccessToken(authCode);
                Oauth2Info kakaoInfo = oauth2Util.getKakaoUserInfo(kakaoAccessToken);
                socialId = kakaoInfo.getSocialId();
                socialName = kakaoInfo.getSocialName();
                break;
            case NAVER:
                String naverAccessToken = oauth2Util.getNaverAccessToken(authCode);
                Oauth2Info naverInfo = oauth2Util.getNaverUserInfo(naverAccessToken);
                socialId = naverInfo.getSocialId();
                socialName = naverInfo.getSocialName();
                break;
            case GOOGLE:
                String googleAccessToken = oauth2Util.getGoogleAccessToken(authCode);
                Oauth2Info googleInfo = oauth2Util.getGoogleUserInfo(googleAccessToken);
                socialId = googleInfo.getSocialId();
                socialName = googleInfo.getSocialName();
                break;
        }

        User loginCustomer = customerRepository.findBySocialIdAndLoginProvider(socialId, loginProvider);
        if (loginCustomer == null) {
            loginCustomer = customerRepository.save(new User(socialName, socialId, loginProvider));
        }

        JwtToken jwtToken = jwtProvider.createTotalToken(loginCustomer.getId(), loginCustomer.getUserRole());
        loginCustomer.setLogin(jwtToken.getRefreshToken());

        LoginDto loginDto = LoginDto.builder()
                .username(loginCustomer.getNickname())
                .access_token(jwtToken.getAccessToken())
                .refresh_token(jwtToken.getRefreshToken())
                .build();

        return loginDto;


                //(loginCustomer.getNickname(), jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }

    public boolean logout(Long id, UserRole role) {
        switch (role) {
            case USER:
                User customer = customerRepository.findByIdAndRefreshTokenIsNotNullAndIsLoginIsTrue(id);
                if (customer == null) {
                    throw new ApiException(ErrorDefine.USER_NOT_FOUND);
                }
                customer.setLogout();
                break;
        }
        return true;
    }

    public Map<String, String> reissueToken(HttpServletRequest request, UserRole role) {
        return Map.of("access_token", jwtProvider.reissueToken(request, role));
    }

}

