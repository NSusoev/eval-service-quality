package esq.core.repository;

import esq.application.repository.*;
import esq.core.model.ESQSurveyResultGroupMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nsusoev on 18.07.16.
 */

@Repository
public class ESQSurveyResultGroupMetaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClientCategoryRepository clientCategoryRepository;
    @Autowired
    private ClientGroupRepository clientGroupRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    public List<ESQSurveyResultGroupMeta> findAllMetaGroups() {
        List<ESQSurveyResultGroupMeta> groupsMeta = this.jdbcTemplate.query(
                "SELECT s.client_category_id, s.client_group_id, s.service_id\n" +
                "FROM service_quality_surveys s, service_quality_survey_results r\n" +
                "WHERE s.id = r.service_quality_survey_id\n" +
                "GROUP BY s.client_category_id, s.client_group_id, s.service_id",
                (rs, rowNum) -> new ESQSurveyResultGroupMeta(
                        clientCategoryRepository.findOne(rs.getLong("client_category_id")),
                        clientGroupRepository.findOne(rs.getLong("client_group_id")),
                        serviceRepository.findOne(rs.getLong("service_id"))));

        return groupsMeta;
    }

    public float getFrequencyOfMarkForSubCriteria(Long clientCategoryId, Long clientGroupId, Long serviceId,
                                                  Long qualityMarkId) {
        float frequency = this.jdbcTemplate.queryForObject(
                "SELECT count::float / vse as frequency\n" +
                        "FROM\n" +
                        "(SELECT count(*) as count\n" +
                        "FROM service_quality_surveys s, service_quality_survey_results r\n" +
                        "WHERE s.id = r.service_quality_survey_id\n" +
                        "and s.client_category_id = ?\n" +
                        "and s.client_group_id = ?\n" +
                        "and s.service_id = ?\n" +
                        "and r.quality_mark_id = ?) as cnt,\n" +
                        "(SELECT count(*) as vse\n" +
                        "FROM service_quality_surveys s, service_quality_survey_results r\n" +
                        "WHERE s.id = r.service_quality_survey_id\n" +
                        "and s.client_category_id = ?\n" +
                        "and s.client_group_id = ?\n" +
                        "and s.service_id = ?\n",
                new Object[] { clientCategoryId, clientGroupId, serviceId, qualityMarkId,
                                clientCategoryId, clientGroupId, serviceId },
                Float.class);

        return frequency;
    }

}
