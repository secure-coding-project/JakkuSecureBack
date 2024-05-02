package secure.project.secureProject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import secure.project.secureProject.domain.Item;

@Getter
@NoArgsConstructor
public class ItemDto {
    private Long itemId;

    private String itemName;

    private Integer itemPrice;

    private Integer itemAmount;

    private String imageUrl;

    //--------------------------------------------------

    @Builder
    public ItemDto(Item item) {
        this.itemId = item.getId();
        this.itemName = item.getItemName();
        this.itemAmount = item.getItemAmount();
        this.itemPrice = item.getItemPrice();
        this.imageUrl = item.getImageUrl();
    }
}
