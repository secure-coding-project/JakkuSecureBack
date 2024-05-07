package secure.project.secureProject.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BasketDto {
    private Long basketId; //

    private Long itemId;

    private String itemName;

    private Integer itemPrice;

    private Integer basketItemAmount; //

    private Integer itemAmount;

    private String imageUrl;
}
