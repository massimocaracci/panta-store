package org.pantasoft.pantastore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.pantasoft.pantastore.repository")
public class JpaConfig {
}
