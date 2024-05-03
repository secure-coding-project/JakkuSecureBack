package secure.project.secureProject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import secure.project.secureProject.domain.Item;

@Getter
@Builder
public class ItemDto {
    private Long itemId;

    private String itemName;

    private Integer itemPrice;

    private Integer itemAmount;

    private String imageUrl;

    //--------------------------------------------------

}
