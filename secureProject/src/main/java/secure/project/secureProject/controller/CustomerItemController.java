package secure.project.secureProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import secure.project.secureProject.dto.response.ItemDto;
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
}
