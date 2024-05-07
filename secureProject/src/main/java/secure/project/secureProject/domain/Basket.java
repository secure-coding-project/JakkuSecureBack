package secure.project.secureProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Table(name = "BASKET_TB")
@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer basketItemAmount;

    //-----------------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item itemId;

    @Builder
    public Basket(Integer basketItemAmount, User userId, Item itemId) {
        this.basketItemAmount = basketItemAmount;
        this.userId = userId;
        this.itemId = itemId;
    }


    public void updateAdminItemAmount(Integer basketItemAmount) {
        this.basketItemAmount = basketItemAmount;
    }

}
