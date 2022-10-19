package uz.bakhromjon.item;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RedisTemplate<String, Item> redisTemplate;

    public Item create(Item item) {
        return repository.save(item);
    }

    public Item update(Item item) {
        Item updated = repository.save(item);
        // update from redis
        redisTemplate.opsForHash().put(HASH, item.getId(), item);
        return updated;
    }

    public Item get(Integer id) {
        // find from cache
        Object obj = redisTemplate.opsForHash().get(HASH, id);
        if (obj != null) {
            return (Item) obj;
        }

        Optional<Item> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ElementNotFoundException("Item not found", HttpStatus.NOT_FOUND);
        }
        // cacheable
        Item item = optional.get();
        redisTemplate.opsForHash().put(HASH, id, item);
        return item;
    }

    public List<Item> list() {
        return repository.findAll();
    }

    public boolean delete(Integer id) {
        // delete from redis
        redisTemplate.opsForHash().delete(HASH, id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ElementNotFoundException("Item not found", HttpStatus.NOT_FOUND);
        }
        return true;
    }


}
