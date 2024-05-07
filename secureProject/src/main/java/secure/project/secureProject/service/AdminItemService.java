package secure.project.secureProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import secure.project.secureProject.domain.Item;
import secure.project.secureProject.dto.reqeust.ItemAddAmountRequestDto;
import secure.project.secureProject.dto.reqeust.ItemReqeustDto;
import secure.project.secureProject.dto.response.ItemDto;
import secure.project.secureProject.dto.response.PageInfo;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;
import secure.project.secureProject.repository.AdminItemRepository;
import secure.project.secureProject.util.S3UploadUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminItemService {

    private final AdminItemRepository adminItemRepository;
    private final S3UploadUtil s3UploadUtil;

    public Map<String, Object> selectAdminItem(Integer page, Integer size, String latest, String price, String searchName){

        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.fromString(latest), "updateAt"),
                new Sort.Order(Sort.Direction.fromString(price), "itemPrice")
        );

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Item> selectItem = adminItemRepository.searchItemList(searchName, pageable);

        PageInfo pageInfo = PageInfo.builder()
                .currentPage(selectItem.getNumber() + 1)
                .totalPages(selectItem.getTotalPages())
                .pageSize(selectItem.getSize())
                .currentItems(selectItem.getNumberOfElements())
                .totalItems(selectItem.getTotalElements())
                .build();

        Map<String, Object> result = new HashMap<>();

        result.put("selectItem",selectItem.getContent().stream()
                .map(items-> ItemDto.builder()
                        .itemId(items.getId())
                        .itemName(items.getItemName())
                        .itemPrice(items.getItemPrice())
                        .itemAmount(items.getItemAmount())
                        .imageUrl(items.getImageUrl())
                        .build())
                .collect(Collectors.toList()));

        result.put("pageInfo",pageInfo);

        return result;
    }

    public boolean addItemAmount(ItemAddAmountRequestDto itemRequestDto) {
        Item item = adminItemRepository.findById(itemRequestDto.getItemId())
                .orElseThrow(() -> new ApiException(ErrorDefine.ITEM_NOT_FOUND));

        item.updateAdminItemAmount(item.getItemAmount() + itemRequestDto.getItemAmount());

        return true;
    }
    public String testimage(MultipartFile multipartFile){
        String imageUrl = s3UploadUtil.upload(multipartFile);
        return imageUrl;
    }
    public boolean addItem(ItemReqeustDto itemReqeustDto, MultipartFile multipartFile) {
         if(adminItemRepository.existsByItemName(itemReqeustDto.getItemName())){
             throw new ApiException(ErrorDefine.ITEM_EXIST);
         }else {
             String imageUrl = s3UploadUtil.upload(multipartFile);
             Item newItem = Item.builder()
                     .itemName(itemReqeustDto.getItemName())
                     .itemPrice(itemReqeustDto.getItemPrice())
                     .itemAmount(itemReqeustDto.getItemAmount())
                     .imageUrl(imageUrl)
                     .updateAt(LocalDate.now())
                     .build();
             adminItemRepository.save(newItem);
         }

        return true;
    }
}
