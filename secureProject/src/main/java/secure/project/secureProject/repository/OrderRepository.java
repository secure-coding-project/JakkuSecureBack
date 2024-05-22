package secure.project.secureProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import secure.project.secureProject.domain.Order;
import secure.project.secureProject.domain.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT i FROM Order i WHERE (:user IS NULL OR i.user = :user)")
    Page<Order> searchOrderListByUserId(@Param("user") User user, Pageable pageable);

    @Query("SELECT i FROM Order i")
    Page<Order> searchOrderList(Pageable pageable);

}
