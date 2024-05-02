package secure.project.secureProject.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserReqeustDto {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private Long point;
}
