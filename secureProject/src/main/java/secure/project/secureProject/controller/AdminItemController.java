package secure.project.secureProject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import secure.project.secureProject.dto.reqeust.ItemAddAmountRequestDto;
import secure.project.secureProject.dto.reqeust.ItemReqeustDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.service.AdminItemService;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminItemController {
    private final AdminItemService adminItemService;

    @GetMapping("/item")
    public ResponseDto<Map<String, Object>> selectAdminItem(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "3") Integer size,
            @RequestParam(name = "lastest", defaultValue = "desc") String latest,
            @RequestParam(name = "price", defaultValue = "desc") String price,
            @RequestParam(name = "searchName", required = false) String searchName
    ){

        return new ResponseDto<>(adminItemService.selectAdminItem(page, size, latest, price, searchName));
    }

    @PatchMapping("/addItemAmount")
    public ResponseDto<Boolean> addItemAmount(
            @Valid @RequestBody ItemAddAmountRequestDto itemRequestDto
            ) {
        return new ResponseDto<>(adminItemService.addItemAmount(itemRequestDto));
    }

    @PostMapping("/addItem")
    public ResponseDto<Boolean> addItem(
            @Valid @RequestBody ItemReqeustDto itemReqeustDto
            ) {
        return new ResponseDto<>(adminItemService.addItem(itemReqeustDto));
    }

}
