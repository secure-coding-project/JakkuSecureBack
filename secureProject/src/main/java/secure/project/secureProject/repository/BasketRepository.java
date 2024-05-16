package secure.project.secureProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import secure.project.secureProject.domain.Basket;
import secure.project.secureProject.domain.Item;
import secure.project.secureProject.domain.User;
import secure.project.secureProject.dto.response.BasketDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Boolean existsByItemIdAndUserId(Item item, User user);

    Optional<Basket> findByUserId(User user, Sort sort);

    Optional<Basket> findByItemIdAndUserId(Item item, User user);

    @Query(value = "SELECT b, i FROM Basket b JOIN FETCH b.itemId i where b.userId = :user")
    List<Basket> findBasketsByUserIdWithItem(User user);

    List<Basket> findByItemId(Item item);
}
