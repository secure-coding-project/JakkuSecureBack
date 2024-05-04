package secure.project.secureProject.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BasketDto {
    private Long itemId;

    private String itemName;

    private Integer itemPrice;

    private Integer basketItemAmount;

    private String imageUrl;
}
