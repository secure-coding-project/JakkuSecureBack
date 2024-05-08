package secure.project.secureProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import secure.project.secureProject.enums.OrderState;

import java.time.LocalDate;
import java.util.List;

@Table(name = "ORDER_TB")
@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer OrderItemTotalAmount;

    @Column(nullable = false)
    private Integer OrderItemTotalPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(nullable = false)
    private LocalDate createAt;

    //----------------------------------------------------

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //-------------------------------------------------------
    @Builder
    public Order(Integer orderItemTotalAmount, Integer orderItemTotalPrice, OrderState orderState,LocalDate createAt, User user) {
        this.OrderItemTotalAmount = orderItemTotalAmount;
        this.OrderItemTotalPrice = orderItemTotalPrice;
        this.orderState = orderState;
        this.createAt = createAt;
        this.user = user;

    }

    //------------------------------------------------------------

    public void updateOrderState(OrderState orderState){
        this.orderState = orderState;
    }
}
