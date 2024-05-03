package secure.project.secureProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Table(name = "ITEM_TB")
@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Integer itemPrice;

    @Column(nullable = false)
    private Integer itemAmount;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDate updateAt;

    //--------------------------------------------------------------

    @OneToMany(mappedBy = "itemId", fetch = FetchType.LAZY)
    private List<Basket> basketList;

    //-----------------------------------------------------------

    @Builder
    public Item(String itemName, Integer itemPrice, Integer itemAmount, String imageUrl, LocalDate updateAt){
        this.itemName = itemName;
        this. itemPrice = itemPrice;
        this.itemAmount = itemAmount;
        this.imageUrl = imageUrl;
        this.updateAt = updateAt;
    }

    //-----------------------------------------------------------

    public void updateAdminItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }

}
