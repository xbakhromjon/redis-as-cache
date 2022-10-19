package uz.bakhromjon.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author : Bakhromjon Khasanboyev
 * @username: @xbakhromjon
 * @since : 18/10/22, Tue, 16:51
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item implements Serializable {
    @Id
    private Integer id;
    private String name;
}
