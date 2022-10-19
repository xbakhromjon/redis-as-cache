package uz.bakhromjon.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 18/10/22, Tue, 16:57
 **/
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService service;

    @PostMapping
    public Item create(@RequestBody Item item) {
        return service.create(item);
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping()
    public List<Item> list() {
        return service.list();
    }

    @PutMapping()
    public Item update(@RequestBody Item item) {
        return service.update(item);
    }

    @DeleteMapping()
    public boolean delete(@RequestParam Integer id) {
        return service.delete(id);
    }
}
