package secure.project.secureProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secure.project.secureProject.domain.Item;
import secure.project.secureProject.dto.response.ItemDto;
import secure.project.secureProject.repository.ItemRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerItemService {

    private final ItemRepository itemRepository;

    public List<ItemDto> selectCustomerItem(Integer page, Integer size, String latest, String price, String searchName){

        Page<Item> items = itemRepository.findAll(PageRequest.of(page, size, Sort.by("").descending()));
        List<ItemDto> result = new ArrayList<>();

        return result;
    }
}
