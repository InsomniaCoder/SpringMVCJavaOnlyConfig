package repositories;

import entities.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tanat on 6/16/2016.
 */
@Repository
public interface TestEntityRepository  extends CrudRepository<TestEntity,Integer> {
}
