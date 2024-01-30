package org.alram.lh.rental.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["org.alram.lh.rental.infra.jpa"])
@Configuration
class JpaConfiguration {
}