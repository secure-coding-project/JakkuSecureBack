package secure.project.secureProject.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasketAddItemRequestDto {
    private Long userId;
    private Long itemId;
    private Integer itemAmount;
}
