package uz.bakhromjon.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.bakhromjon.exception.ElementNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 18/10/22, Tue, 16:55
 **/
@Service
public class ItemService {
    public static final String HASH = "Item";
    @Autowired
    private ItemRepository repository;

    public Item create(Item item) {
        return repository.save(item);
    }

    @CachePut(value = HASH, key = "#item.id")
    public Item update(Item item) {
        Item updated = repository.save(item);
        return updated;
    }

    @Cacheable(value = HASH, key = "#id")
    public Item get(Integer id) {
        Optional<Item> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ElementNotFoundException("Item not found", HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }

    public List<Item> list() {
        return repository.findAll();
    }

    @CacheEvict(value = HASH, key = "#id")
    public boolean delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ElementNotFoundException("Item not found", HttpStatus.NOT_FOUND);
        }
        return true;
    }


}
