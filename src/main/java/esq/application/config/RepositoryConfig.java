package esq.application.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by nsusoev on 05.04.16.
 */

@Configuration
@EnableJpaRepositories(basePackages = "esq.application.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = "esq.application.model")
public class RepositoryConfig {
}
