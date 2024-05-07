package secure.project.secureProject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import secure.project.secureProject.dto.reqeust.BasketAddItemRequestDto;
import secure.project.secureProject.dto.reqeust.BasketRequestDto;
import secure.project.secureProject.dto.reqeust.CustomerOrderItemRequestDto;
import secure.project.secureProject.dto.response.BasketDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.service.CustomerItemService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerItemController {
    private final CustomerItemService customerItemService;

    @GetMapping("/item")
    public ResponseDto<Map<String, Object>> selectCustomerItem(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "3") Integer size,
            @RequestParam(name = "latest", defaultValue = "desc") String latest,
            @RequestParam(name = "price", defaultValue = "desc") String price,
            @RequestParam(name = "searchName", required = false) String searchName
    ){

        return new ResponseDto<>(customerItemService.selectCustomerItem(page, size, latest, price, searchName));
    }

    @PostMapping("/addItem")
    public ResponseDto<Boolean> addItemToBasket(
            @Valid @RequestBody BasketAddItemRequestDto basketAddItem
            ) {
        return new ResponseDto<>(customerItemService.addItemToBasket(basketAddItem));
    }

    @GetMapping("/basket")
    public ResponseDto<Map<String, Object>> selectBasketItem(
           @Valid @RequestBody BasketRequestDto basketRequestDto
            ) {
        return new ResponseDto<>(customerItemService.selectBasketItem(basketRequestDto));
    }

    @PatchMapping("/payment")
    public ResponseDto<Boolean> paymentItem(
            @Valid @RequestBody List<CustomerOrderItemRequestDto> customerOrderItemRequestDto
            ) {
        return new ResponseDto<>(customerItemService.paymentItem(customerOrderItemRequestDto));
    }
}
