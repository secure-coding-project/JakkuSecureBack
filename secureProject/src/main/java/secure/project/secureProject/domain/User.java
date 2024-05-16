package secure.project.secureProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

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

    @Column(nullable = false, length = 30, updatable = false)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Long point;

    @Column(nullable = false, length = 10)
    private String nickname;

    @Column(nullable = false)
    private LocalDate createAt;

    @Column(nullable = false)
    private LocalDate updateAt;

    //---------------------------------------------------------

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Basket> basketList;

    //----------------------------------------------------

    @Builder
    public User(String loginId, String password, String nickname, LocalDate updateAt, Long point) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.point = point;
        this.createAt = LocalDate.now();
        this.updateAt = updateAt;
    }


    //-----------------------------------------------------------

    public void updatePoint(Long point){this.point = point;}
}
