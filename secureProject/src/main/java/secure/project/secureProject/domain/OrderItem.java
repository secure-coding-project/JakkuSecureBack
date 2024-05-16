package secure.project.secureProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "ORDERITEM_TB")
@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer orderItemAmount;

    //----------------------------------------------------


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //------------------------------------------------

    @Builder
    public OrderItem(Integer orderItemAmount, Order order, Item item) {
        this.orderItemAmount = orderItemAmount;
        this.order = order;
        this.item = item;
    }

}
