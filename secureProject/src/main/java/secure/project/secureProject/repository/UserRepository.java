package secure.project.secureProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import secure.project.secureProject.domain.User;
import secure.project.secureProject.enums.UserRole;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByLoginId(String loginId);
//
//    @Query(value = "SELECT u.id AS id, u.role AS userRole FROM User u WHERE u.id = :userId")
//    Optional<UserLoginForm> findUserForAuthentication(@Param("userId") Long userId);
//
//    @Query("SELECT u.id AS id, u.role AS userRole FROM User u WHERE u.id = :userId AND u.isLogin = true AND u.refreshToken = :refreshToken")
//    Optional<UserLoginForm> findByIdAndRefreshToken(@Param("userId") Long userId, @Param("refreshToken") String refreshToken);
//
//
//    public interface UserLoginForm {
//        Long getId();
//        UserRole getUserRole();
//    }
    Optional<User> findByNickname(String nickname);
    Optional<User> findByLoginId(String loginId);
}
