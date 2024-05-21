package secure.project.secureProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secure.project.secureProject.domain.Order;
import secure.project.secureProject.domain.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

}
