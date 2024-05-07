package secure.project.secureProject.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemReqeustDto {
    private String itemName;
    private Integer itemAmount;
    private Integer itemPrice;
}
