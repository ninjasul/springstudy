package inflearn.jpa.web.jpashop.service;

import inflearn.jpa.web.jpashop.domain.item.Book;
import inflearn.jpa.web.jpashop.domain.item.Item;
import inflearn.jpa.web.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Item item) {
        Book foundItem = (Book)findOne(item.getId());
        foundItem.update((Book)item);
        return foundItem;
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }
}