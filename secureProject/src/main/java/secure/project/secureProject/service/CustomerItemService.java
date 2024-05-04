package secure.project.secureProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secure.project.secureProject.domain.Basket;
import secure.project.secureProject.domain.Item;
import secure.project.secureProject.domain.User;
import secure.project.secureProject.dto.reqeust.BasketAddItemRequestDto;
import secure.project.secureProject.dto.response.ItemDto;
import secure.project.secureProject.dto.response.PageInfo;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;
import secure.project.secureProject.repository.BasketRepository;
import secure.project.secureProject.repository.CustomerItemRepository;
import secure.project.secureProject.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerItemService {

    private final CustomerItemRepository customerItemRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    public Map<String, Object> selectCustomerItem(Integer page, Integer size, String latest, String price,String searchName){
        Sort sort = Sort.by(

                new Sort.Order(Sort.Direction.fromString(latest), "updateAt"),
                new Sort.Order(Sort.Direction.fromString(price), "itemPrice")
        );

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Item> selectItem = customerItemRepository.searchItemList(searchName, pageable);

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

    public Boolean addItemToBasket(BasketAddItemRequestDto basketAddItemRequestDto) {
        User user = userRepository.findById(basketAddItemRequestDto.getUserId())
                .orElseThrow( () -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        Item item = customerItemRepository.findById(basketAddItemRequestDto.getItemId())
                .orElseThrow(() -> new ApiException(ErrorDefine.ITEM_NOT_FOUND));

        if(basketRepository.existsByItemIdAndUserId(item, user)){
            Basket basket = basketRepository.findByItemIdAndUserId(item, user);
            basket.updateAdminItemAmount(basketAddItemRequestDto.getItemAmount());
        } else {
            Basket basket = Basket.builder()
                    .basketItemAmount(basketAddItemRequestDto.getItemAmount())
                    .userId(user)
                    .itemId(item)
                    .build();
            basketRepository.save(basket);
        }
        return true;
    }

    public Map<String,Object> selectBasketItem(String amount, String price) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.fromString(amount), "basketAmount"),
                new Sort.Order(Sort.Direction.fromString(price), "itemPrice")
        );
        Map<String , Object> test = new HashMap<>();

        return test;
    }
}
