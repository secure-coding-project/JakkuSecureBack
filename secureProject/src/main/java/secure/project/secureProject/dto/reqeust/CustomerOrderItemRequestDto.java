package secure.project.secureProject.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerOrderItemRequestDto {
    private Long itemId;
    private Integer buyItemAmount;
}
