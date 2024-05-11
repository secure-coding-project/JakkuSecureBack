package secure.project.secureProject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import secure.project.secureProject.dto.reqeust.AdminRegisterRequestDto;
import secure.project.secureProject.dto.reqeust.ItemAddAmountRequestDto;
import secure.project.secureProject.dto.reqeust.ItemReqeustDto;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.service.AdminItemService;
import secure.project.secureProject.util.S3UploadUtil;

import java.util.List;
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

    @DeleteMapping("/deleteItem/{itemId}")
    public ResponseDto<Boolean> deleteItem(
            @PathVariable Long itemId
    ) {
        return new ResponseDto<>(adminItemService.deleteItem(itemId));
    }

    @PostMapping(value = "/addItem" ,consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<Boolean> addItem(
            @Valid @RequestPart(value = "request") ItemReqeustDto itemReqeustDto,
            @RequestPart(value = "image") MultipartFile multipartFile
            ) {
        return new ResponseDto<>(adminItemService.addItem(itemReqeustDto,multipartFile));
    }

    @PostMapping("/imagetest")
    public ResponseDto<String> testimage(
            @RequestPart(value = "image") MultipartFile multipartFile
    ) {
        return new ResponseDto<>(adminItemService.testimage(multipartFile));
    }

    @GetMapping("/pickup")
    public ResponseDto<Map<String,Object>> selectPickup(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "3") Integer size,
            @RequestParam(name = "latest", defaultValue = "desc") String latest,
            @RequestParam(name = "status", defaultValue = "desc") String status
    ) {
        return new ResponseDto<>(adminItemService.selectPickup(page, size, latest, status));
    }

    @GetMapping("/pickup/detail/{orderId}")
    public ResponseDto<Map<String, Object>> selectPickupDetailItem(
            @PathVariable Long orderId
    ) {
        return new ResponseDto<>(adminItemService.selectPickupDetailItem(orderId));
    }

    @PatchMapping("/pickup/register/{orderId}")
    public ResponseDto<Boolean> adminPickupRegister(
            @PathVariable Long orderId,
            @RequestBody AdminRegisterRequestDto adminRegisterRequestDto
            ) {
        return new ResponseDto<>(adminItemService.adminPickupRegister(orderId, adminRegisterRequestDto));
    }
}
