package secure.project.secureProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import secure.project.secureProject.domain.Item;


public interface CustomerItemRepository extends JpaRepository<Item, Long> {
        @Query("SELECT i FROM Item i WHERE (:itemName IS NULL OR i.itemName LIKE CONCAT('%', :itemName, '%'))")
        Page<Item> searchItemList(@Param("itemName") String itemName, Pageable pageable);

}
