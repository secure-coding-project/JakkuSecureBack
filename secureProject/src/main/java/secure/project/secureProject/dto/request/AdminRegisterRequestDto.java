package secure.project.secureProject.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import secure.project.secureProject.enums.OrderState;

@Getter
@Setter
@NoArgsConstructor
public class AdminRegisterRequestDto {
    private OrderState orderState;
}
