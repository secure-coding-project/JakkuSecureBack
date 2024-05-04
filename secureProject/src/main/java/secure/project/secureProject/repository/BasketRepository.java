package secure.project.secureProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secure.project.secureProject.domain.Basket;
import secure.project.secureProject.domain.Item;
import secure.project.secureProject.domain.User;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Boolean existsByItemIdAndUserId(Item item, User user);

    Basket findByItemIdAndUserId(Item item, User user);
}
