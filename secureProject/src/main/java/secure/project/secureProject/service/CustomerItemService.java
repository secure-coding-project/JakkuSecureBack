package secure.project.secureProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secure.project.secureProject.domain.Item;
import secure.project.secureProject.dto.response.ItemDto;
import secure.project.secureProject.dto.response.PageInfo;
import secure.project.secureProject.repository.CustomerItemRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerItemService {

    private final CustomerItemRepository itemRepository;

    public Map<String, Object> selectCustomerItem(Integer page, Integer size, String latest, String price,String searchName){
        Sort sort = Sort.by(

                new Sort.Order(Sort.Direction.fromString(latest), "updateAt"),
                new Sort.Order(Sort.Direction.fromString(price), "itemPrice")
        );

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Item> selectItem = itemRepository.searchItemList(searchName, pageable);

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
}
