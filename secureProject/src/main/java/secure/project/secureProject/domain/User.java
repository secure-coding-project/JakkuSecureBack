package secure.project.secureProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import secure.project.secureProject.domain.enums.LoginProvider;
import secure.project.secureProject.domain.enums.UserRole;

import java.time.LocalDate;
import java.util.List;

@Table(name = "USER_TB")
@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private Long point;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDate createAt;

    private LocalDate updateAt;

    @Column
    private String socialName;

    @Column
    private String socialId;

    @Column
    private LoginProvider loginProvider;

    @Column
    private Boolean isLogin;

    @Column
    private String refreshToken;

    //---------------------------------------------------------

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Basket> basketList;

    //----------------------------------------------------

    @Builder
    public User(String loginId, String password, String nickname, LocalDate updateAt, UserRole userRole,Long point) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.point = point;
        this.userRole = userRole;
        this.createAt = LocalDate.now();
        this.updateAt = updateAt;
    }

    public User(String socialName, String socialId, LoginProvider loginProvider) {
        this.socialName = socialName;
        this.socialId = socialId;
        this.loginProvider = loginProvider;
    }

    public void setLogin(String refreshToken) {
        this.refreshToken = refreshToken;
        this.isLogin = true;
    }

    public void setLogout() {
        this.refreshToken = null;
        this.isLogin = false;
    }


    //-----------------------------------------------------------

    public void updatePoint(Long point){this.point = point;}
}
