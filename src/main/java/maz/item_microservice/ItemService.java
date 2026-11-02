package maz.item_microservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item insertNewItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item updatedItem) {
        //record to be updated has to exist first
        Item item = itemRepository.findById(updatedItem.getItemId()).orElseThrow(
                () -> new RuntimeException("Item not found"));
        BeanUtils.copyProperties(updatedItem, item);
        return itemRepository.save(item);
    }


    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);

    }
}
