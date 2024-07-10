package secure.project.secureProject.security.oauth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import secure.project.secureProject.domain.enums.UserRole;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;
import secure.project.secureProject.repository.UserRepository;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider implements InitializingBean {

    private final UserRepository customerRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.accessExpiredMs}")
    private Long accessExpiredMs;

    @Value("${jwt.refreshExpiredMs}")
    private Long refreshExpiredMs;
    private Key key;

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String createToken(Long id, UserRole role, boolean isAccess) {
        Claims claims = Jwts.claims().setSubject(id.toString());
        claims.put("id", id);
        if (isAccess) {
            claims.put("role", role);
        }

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (isAccess ? accessExpiredMs : refreshExpiredMs)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public JwtToken createTotalToken(Long id, UserRole userRole) {
        return new JwtToken(
                createToken(id, userRole, true),
                createToken(id, userRole, false)
        );
    }

    public String reissueToken(HttpServletRequest request, UserRole role) {
        String refreshToken = refineToken(request);
        System.err.println("refresh      "+refreshToken+"   흠...");
        Long userId = getUserId(refreshToken);
        UserLoginForm user;
        switch (role) {
            case USER:
                user = customerRepository.findByIdAndRefreshToken(userId, refreshToken)
                        .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));
                break;
            default:
                throw new ApiException(ErrorDefine.USER_NOT_FOUND);
        }

        if (user == null) {
            throw new ApiException(ErrorDefine.USER_NOT_FOUND);
        }

        return createToken(user.getId(), user.getRole(), true);
    }

    public Long getUserId(String token) throws JwtException {
        return Long.parseLong(Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString());
    }

    public Claims validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String refineToken(HttpServletRequest request) throws JwtException {
        String beforeToken = request.getHeader("Authorization");
        if (StringUtils.hasText(beforeToken) && beforeToken.startsWith("Bearer ")) {
            return beforeToken.substring(7);
        } else {
            throw new ApiException(ErrorDefine.TOKEN_INVALID);
        }
    }
}

