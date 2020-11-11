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
public class Person implements Persistable<Long> {

    @Id
    Long id;

    @Nullable
    String lastName;

    String firstName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date date;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @Field(type = FieldType.Date,  store = true,format = DateFormat.basic_date_time, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
    private Instant created;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    @Nullable @Field(type = FieldType.Date,  store = true,format = DateFormat.basic_date_time, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ")
    private Instant lastModified;

    @Nullable @LastModifiedBy
    private String lastModifiedBy;

    @Nullable
    public Long getId() { return id; }

    @Override
    public boolean isNew() { return id == null || (createdBy == null && created == null); }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
