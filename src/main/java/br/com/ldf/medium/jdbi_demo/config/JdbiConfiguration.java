package br.com.ldf.medium.jdbi_demo.config;

import br.com.ldf.medium.jdbi_demo.domain.Developer;
import br.com.ldf.medium.jdbi_demo.persistence.DeveloperRepository;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Configuration
public class JdbiConfiguration {
    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);

        // Register all available plugins
        jdbiPlugins.forEach(jdbi::installPlugin);

        // Register all available rowMappers
        jdbi.registerRowMapper(Developer.class, ConstructorMapper.of(Developer.class));
        return jdbi;
    }

    @Bean
    public DeveloperRepository developerRepository(Jdbi jdbi) {
        return jdbi.onDemand(DeveloperRepository.class);
    }

    @Bean
    public List<JdbiPlugin> jdbiPlugins() {
        return List.of(
                new SqlObjectPlugin(),
                new PostgresPlugin()
        );
    }
}
