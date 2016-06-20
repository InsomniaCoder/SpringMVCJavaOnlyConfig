package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Tanat on 6/16/2016.
 */
@Entity
public class TestEntity {

    @Id
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
