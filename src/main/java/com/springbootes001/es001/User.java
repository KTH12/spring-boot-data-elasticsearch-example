package com.springbootes001.es001;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.Date;

@Document(indexName = "human")
@TypeAlias("human")
@Data
public class User implements Persistable<Integer> {

    @Id
    private Integer id;
    private String name;
    private Integer age;

    @Nullable
    public Integer getId() { return id; }

    @Override
    public boolean isNew() {
        return false;
    }
}
