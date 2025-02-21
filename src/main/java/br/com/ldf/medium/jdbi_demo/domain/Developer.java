package br.com.ldf.medium.jdbi_demo.domain;

import lombok.Getter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Getter
public class Developer {

    private final Long id;
    private final String name;

    public Developer(@ColumnName("id_developer") Long id,
                     String name) {
        this.id = id;
        this.name = name;
    }
}