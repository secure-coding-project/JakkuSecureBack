package secure.project.secureProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secure.project.secureProject.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {


}
