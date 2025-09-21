package net.accel_tech.databases_api.entity;

import lombok.*;
import net.accel_tech.databases_api.model.DateAudit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;

@Document(collection = "Instances")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Instance extends DateAudit implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field(name = "name")
    private String name;

    @Field(name = "database")
    private String database;

    @Field(name = "version")
    private String Version;

    @Field(name = "sizing")
    private String sizing;

}




