package secure.project.secureProject.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
