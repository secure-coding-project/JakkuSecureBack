package secure.project.secureProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginSignUpRequestDto {
    private String userNickname;
    private String loginId;
    private String password;
}
