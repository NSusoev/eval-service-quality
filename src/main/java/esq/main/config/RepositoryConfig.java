package esq.main.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by nsusoev on 05.04.16.
 */

@Configuration
@EnableJpaRepositories(basePackages = "esq.main.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = "esq.main.model")
public class RepositoryConfig {
}
