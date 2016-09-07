package esq.application.repository;

import esq.application.model.LinguisticTerm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nsusoev on 07.05.16.
 */
public interface LinguisticTermRepository extends CrudRepository<LinguisticTerm, Long>{

    @Query(value = "SELECT t.id, t.name" +
            " FROM service_quality_surveys s, service_quality_survey_results r, linguistic_terms t" +
            " WHERE s.id = r.service_quality_survey_id " +
                "and r.quality_mark_id = t.id " +
                "and s.client_category_id = ?1 " +
                "and s.client_group_id = ?2 " +
                "and s.service_id = ?3 " +
                "and r.importance_mark_id = ?4", nativeQuery = true)
    List<LinguisticTerm> findQualityMarksForGroup(Long clientCategoryId,
                                                  Long clientGroupId,
                                                  Long serviceId,
                                                  Long importanceMarkId);
}
