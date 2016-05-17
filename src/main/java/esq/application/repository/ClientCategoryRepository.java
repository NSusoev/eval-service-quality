package esq.application.repository;

import esq.application.model.ClientCategory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nsusoev on 07.05.16.
 */
public interface ClientCategoryRepository extends CrudRepository<ClientCategory, Long>{
}
