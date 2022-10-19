package uz.bakhromjon.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 18/10/22, Tue, 16:52
 **/
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
