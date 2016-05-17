package esq.application.repository;

import esq.application.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nsusoev on 05.04.16.
 */

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long>{
}
