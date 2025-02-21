package br.com.ldf.medium.jdbi_demo.domain;

import lombok.*;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

@Getter
@AllArgsConstructor(onConstructor_ = @JdbiConstructor)
public class Developer {

    private Long developerId;
    @NonNull
    private String name;

}