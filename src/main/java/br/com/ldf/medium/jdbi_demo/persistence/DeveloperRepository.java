package br.com.ldf.medium.jdbi_demo.persistence;

import br.com.ldf.medium.jdbi_demo.domain.Developer;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface DeveloperRepository {
    @SqlUpdate("INSERT INTO developer " +
            "(      name" +
            ") VALUES " +
            "(      :name)")
    @GetGeneratedKeys
    Long saveNewDeveloper(@BindBean Developer developer);

    @SqlQuery("SELECT * FROM developer WHERE developer_id = :developerId")
    Optional<Developer> findDeveloperById(@Bind("developerId") Long developerId);

    @SqlUpdate("UPDATE developer SET" +
            "      name = :name" +
            " WHERE developer_id = :developerId")
    void updateDeveloper(@BindBean Developer developer);

    @SqlUpdate("DELETE from developer WHERE developer_id = :developerId")
    void removeDeveloperById(@Bind("developerId") Long developerId);
}