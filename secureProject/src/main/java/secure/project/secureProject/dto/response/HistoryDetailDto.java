package secure.project.secureProject.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HistoryDetailDto {
    private Long itemId;
    private String itemName;
    private String imageUrl;
    private Integer itemPrice;
    private Integer itemAmount;
}
